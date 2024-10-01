/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

/**
   This program magnifies a PPM image to twice its original dimensions.
   For every pixel in the original image, a square block of four
   pixels is written to the magnified image.
   Version 2 improves on this simple algorithm.
*/
public class MagnifyPPM_ver1
{
   public static void main(String[] args)
   {
      // Check for a file name on the command-line.
      if ( 0 == args.length )
      {
         System.err.println("Usage: java MagnifyPPM_ver1 <PPM-file-name>");
         System.exit(-1);
      }
      // The input image.
      final FrameBuffer fb1 = new FrameBuffer( args[0] );
      // The output image.
      final FrameBuffer fb2 = new FrameBuffer(2*fb1.width, 2*fb1.height);

      for (int y = 0; y < fb1.height; ++y)
      {
         for (int x = 0; x < fb1.width; ++x)
         {
            fb2.setPixelFB( 2*x+0, 2*y+0, fb1.getPixelFB(x, y) );
            fb2.setPixelFB( 2*x+0, 2*y+1, fb1.getPixelFB(x, y) );
            fb2.setPixelFB( 2*x+1, 2*y+0, fb1.getPixelFB(x, y) );
            fb2.setPixelFB( 2*x+1, 2*y+1, fb1.getPixelFB(x, y) );
         }
      }

      final String baseName = args[0].substring(0, args[0].lastIndexOf("."));
      final String savedFileName = baseName + "_magnify_ver1" + ".ppm";
      fb2.dumpFB2File( savedFileName );
   }
}
