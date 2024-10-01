/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.awt.Color;

/**
   This program uses an approximation of the
   gradient vector to detect edges in a picture.
*/
public class EdgeDetection
{
   public static final double THRESHOLD = 50.0;
   public static final Color edgeColor = Color.white;
   public static final Color interiorColor = Color.black;

   public static void main(String[] args)
   {
      // Check for a file name on the command-line.
      if ( 0 == args.length )
      {
         System.err.println("Usage: java Edges <PPM-file-name>");
         System.exit(-1);
      }
      // The input image.
      final FrameBuffer fb1 = new FrameBuffer( args[0] );
      // The output image.
      final FrameBuffer fb2 = new FrameBuffer(fb1.width, fb1.height, Color.black);

      for (int y = 1; y < fb1.height - 1; ++y)
      {
         for (int x = 1; x < fb1.width - 1; ++x)
         {
            final Color c0     = fb1.getPixelFB(x,     y);
            final Color cLeft  = fb1.getPixelFB(x - 1, y);
            final Color cRight = fb1.getPixelFB(x + 1, y);
            final Color cAbove = fb1.getPixelFB(x,     y - 1);
            final Color cBelow = fb1.getPixelFB(x,     y + 1);

            final double deltaHr = cRight.getRed()   - cLeft.getRed();
            final double deltaHg = cRight.getGreen() - cLeft.getGreen();
            final double deltaHb = cRight.getBlue()  - cLeft.getBlue();
            final double deltaH  = Math.max(deltaHr, Math.max(deltaHg, deltaHb));

            final double deltaVr = cAbove.getRed()   - cBelow.getRed();
            final double deltaVg = cAbove.getGreen() - cBelow.getGreen();
            final double deltaVb = cAbove.getBlue()  - cBelow.getBlue();
            final double deltaV  = Math.max(deltaVr, Math.max(deltaVg, deltaVb));

            final double gradiant = Math.pow( deltaH*deltaH + deltaV*deltaV, 0.5 );

            final Color cNew;
            if ( gradiant > THRESHOLD ) // found an edge
            {
               cNew = edgeColor;
            }
            else
            {
               cNew = interiorColor;
            }
            fb2.setPixelFB( x, y, cNew );
         }
      }

      final String baseName = args[0].substring(0, args[0].lastIndexOf("."));
      final String savedFileName = baseName + "_edges" + ".ppm";
      fb2.dumpFB2File( savedFileName );
   }
}
