/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.awt.Color;

/**
   This program takes the (square) image given on
   the command-line and creates a new image that
   is tiled by eight flipped and rotated copies
   of the original image.
*/
public class FlippedViewports
{
   public static void main(String[] args)
   {
      // Check for a file name on the command line.
      // The source image needs to be a square image.
      if ( 0 == args.length )
      {
         System.err.println("Usage: java FlippedViewports <square-PPM-file>");
         System.exit(-1);
      }
      final FrameBuffer fb1 = new FrameBuffer( args[0] );

      // The output image.
      final int height = fb1.getHeightFB();
      final int width  = fb1.getWidthFB();
      final FrameBuffer fb2 = new FrameBuffer(4*width, 2*height);

      final FrameBuffer.Viewport vp1 = fb2.new Viewport(0*width, 0*height, fb1);
      final FrameBuffer.Viewport vp2 = fb2.new Viewport(1*width, 0*height, width, height);
      final FrameBuffer.Viewport vp3 = fb2.new Viewport(2*width, 0*height, width, height);
      final FrameBuffer.Viewport vp4 = fb2.new Viewport(3*width, 0*height, width, height);
      final FrameBuffer.Viewport vp5 = fb2.new Viewport(0*width, 1*height, width, height);
      final FrameBuffer.Viewport vp6 = fb2.new Viewport(1*width, 1*height, width, height);
      final FrameBuffer.Viewport vp7 = fb2.new Viewport(2*width, 1*height, width, height);
      final FrameBuffer.Viewport vp8 = fb2.new Viewport(3*width, 1*height, width, height);

      rotateR(vp1, vp2);
      rotateR(vp2, vp3);
      rotateR(vp3, vp4);

        flipV(vp1, vp5);
      rotateR(vp5, vp6);
      rotateR(vp6, vp7);
      rotateR(vp7, vp8);

      final String baseName = args[0].substring(0, args[0].lastIndexOf("."));
      final String savedFileName = baseName + "_FlippedViewports" + ".ppm";
      fb2.dumpFB2File( savedFileName );
   }


   // Rotate right by 90 degrees (clockwise) the first viewport into the second.
   // Assume that the two viewports are square and have the same dimensions.
   public static void rotateR(final FrameBuffer.Viewport vp1,
                              final FrameBuffer.Viewport vp2)
   {
      final int width = vp1.getWidthVP();
      final int height = vp1.getHeightVP();
      for (int y = 0; y < height; ++y)
      {
         for(int x = 0; x < width; ++x)
         {
            final Color c = vp1.getPixelVP(x, y);
            vp2.setPixelVP(width - 1 - y, x, c);
         }
      }
   }

   // Flip the first viewport vertically into the second viewport.
   // Assume that the two viewports have the same dimensions.
   public static void flipV(final FrameBuffer.Viewport vp1,
                            final FrameBuffer.Viewport vp2)
   {
      final int width = vp1.getWidthVP();
      final int height = vp1.getHeightVP();
      for (int y = 0; y < height; ++y)
      {
         for(int x = 0; x < width; ++x)
         {
            final Color c = vp1.getPixelVP(x, y);
            vp2.setPixelVP(x, height - 1 - y, c);
         }
      }
   }
}
