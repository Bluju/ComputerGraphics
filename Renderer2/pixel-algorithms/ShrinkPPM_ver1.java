/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

/**
   This program shrinks a PPM image to half its original dimensions.
   Out of every square block of four pixels, only one is kept.
   Version 2 improves on this simple algorithm.
*/
public class ShrinkPPM_ver1
{
   public static void main(String[] args)
   {
      // Check for a file name on the command-line.
      if ( 0 == args.length )
      {
         System.err.println("Usage: java ShrinkPPM_ver1 <PPM-file-name>");
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
            fb2.setPixelFB( x, y, fb1.getPixelFB(2*x, 2*y) );
         }
      }

      final String baseName = args[0].substring(0, args[0].indexOf("."));
      final String savedFileName = baseName + "_shrunk_ver1" + ".ppm";
      fb2.dumpFB2File( savedFileName );
   }
}
