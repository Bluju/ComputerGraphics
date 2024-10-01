/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.awt.Color;

/**
   This program magnifies a PPM image to twice its original dimensions.
   For every pixel in the original image, a square block of four
   pixels is written to the magnified image. Each pixel in the
   square block is the average of the original pixel and one of
   its adjacent neighbors.
*/
public class MagnifyPPM_ver2
{
   public static void main(String[] args)
   {
      // Check for a file name on the command-line.
      if ( 0 == args.length )
      {
         System.err.println("Usage: java MagnifyPPM_ver2 <PPM-file-name>");
         System.exit(-1);
      }
      // The input image.
      final FrameBuffer fb1 = new FrameBuffer( args[0] );
      // The output image.
      final FrameBuffer fb2 = new FrameBuffer(2*fb1.width, 2*fb1.height);

      for (int y = 0; y < fb1.height - 1; ++y)   // the last row is different
      {
         for (int x = 0; x < fb1.width - 1; ++x) // the last column is different
         {
            final Color c1 = fb1.getPixelFB(x+0, y+0);
            final Color c2 = fb1.getPixelFB(x+0, y+1);
            final Color c3 = fb1.getPixelFB(x+1, y+0);
            final Color c4 = fb1.getPixelFB(x+1, y+1);
/*
            final int r2 = (int)Math.round( (c1.getRed()   + c2.getRed()  )/2.0 );
            final int g2 = (int)Math.round( (c1.getGreen() + c2.getGreen())/2.0 );
            final int b2 = (int)Math.round( (c1.getBlue()  + c2.getBlue() )/2.0 );

            final int r3 = (int)Math.round( (c1.getRed()   + c3.getRed()  )/2.0 );
            final int g3 = (int)Math.round( (c1.getGreen() + c3.getGreen())/2.0 );
            final int b3 = (int)Math.round( (c1.getBlue()  + c3.getBlue() )/2.0 );

            final int r4 = (int)Math.round( (c1.getRed()   + c4.getRed()  )/2.0 );
            final int g4 = (int)Math.round( (c1.getGreen() + c4.getGreen())/2.0 );
            final int b4 = (int)Math.round( (c1.getBlue()  + c4.getBlue() )/2.0 );
*/
            final float[] c1f = c1.getColorComponents(null);
            final float[] c2f = c2.getColorComponents(null);
            final float[] c3f = c3.getColorComponents(null);
            final float[] c4f = c4.getColorComponents(null);

            final float r2 = (c1f[0] + c2f[0]) / 2.0f;
            final float g2 = (c1f[1] + c2f[1]) / 2.0f;
            final float b2 = (c1f[2] + c2f[2]) / 2.0f;

            final float r3 = (c1f[0] + c3f[0]) / 2.0f;
            final float g3 = (c1f[1] + c3f[1]) / 2.0f;
            final float b3 = (c1f[2] + c3f[2]) / 2.0f;

            final float r4 = (c1f[0] + c4f[0]) / 2.0f;
            final float g4 = (c1f[1] + c4f[1]) / 2.0f;
            final float b4 = (c1f[2] + c4f[2]) / 2.0f;

            fb2.setPixelFB( 2*x+0, 2*y+0, c1 );
            fb2.setPixelFB( 2*x+0, 2*y+1, new Color(r2, g2, b2) );
            fb2.setPixelFB( 2*x+1, 2*y+0, new Color(r3, g3, b3) );
            fb2.setPixelFB( 2*x+1, 2*y+1, new Color(r4, g4, b4) );
         }
         // handle the last column
         fb2.setPixelFB( 2*(fb1.width-1)+0, 2*y+0, fb1.getPixelFB(fb1.width-1, y) );
         fb2.setPixelFB( 2*(fb1.width-1)+0, 2*y+1, fb1.getPixelFB(fb1.width-1, y) );
         fb2.setPixelFB( 2*(fb1.width-1)+1, 2*y+0, fb1.getPixelFB(fb1.width-1, y) );
         fb2.setPixelFB( 2*(fb1.width-1)+1, 2*y+1, fb1.getPixelFB(fb1.width-1, y) );
      }
      // handle the last row
      for (int x = 0; x < fb1.width - 1; ++x) // the last column is different
      {
         fb2.setPixelFB( 2*x+0, 2*(fb1.height-1)+0, fb1.getPixelFB(x, fb1.height-1) );
         fb2.setPixelFB( 2*x+0, 2*(fb1.height-1)+1, fb1.getPixelFB(x, fb1.height-1) );
         fb2.setPixelFB( 2*x+1, 2*(fb1.height-1)+0, fb1.getPixelFB(x, fb1.height-1) );
         fb2.setPixelFB( 2*x+1, 2*(fb1.height-1)+1, fb1.getPixelFB(x, fb1.height-1) );
      }

      final String baseName = args[0].substring(0, args[0].lastIndexOf("."));
      final String savedFileName = baseName + "_magnify_ver2" + ".ppm";
      fb2.dumpFB2File( savedFileName );
   }
}
