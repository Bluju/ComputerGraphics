/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.awt.Color;

/**
   This program shrinks a PPM image to half its original dimensions.
   Every square block of four pixels is averaged to create a new pixel.
   This is a big improvement over version 1.
*/
public class ShrinkPPM_ver2
{
   public static void main(String[] args)
   {
      // Check for a file name on the command-line.
      if ( 0 == args.length )
      {
         System.err.println("Usage: java ShrinkPPM_ver2 <PPM-file-name>");
         System.exit(-1);
      }
      // The input image.
      final FrameBuffer fb1 = new FrameBuffer( args[0] );
      // The output image.
      final FrameBuffer fb2 = new FrameBuffer(fb1.width/2, fb1.height/2);

      for (int y = 0; y < fb1.height/2; ++y)
      {
         for (int x = 0; x < fb1.width/2; ++x)
         {
            final Color c1 = fb1.getPixelFB(2*x+0, 2*y+0);
            final Color c2 = fb1.getPixelFB(2*x+0, 2*y+1);
            final Color c3 = fb1.getPixelFB(2*x+1, 2*y+0);
            final Color c4 = fb1.getPixelFB(2*x+1, 2*y+1);
            final int r = (int)Math.round((c1.getRed()   + c2.getRed()   + c3.getRed()   + c4.getRed())  /4.0);
            final int g = (int)Math.round((c1.getGreen() + c2.getGreen() + c3.getGreen() + c4.getGreen())/4.0);
            final int b = (int)Math.round((c1.getBlue()  + c2.getBlue()  + c3.getBlue()  + c4.getBlue()) /4.0);
            fb2.setPixelFB(x, y, new Color(r, g, b));
         }
      }

      final String baseName = args[0].substring(0, args[0].indexOf("."));
      final String savedFileName = baseName + "_shrunk_ver2" + ".ppm";
      fb2.dumpFB2File( savedFileName );
   }
}
