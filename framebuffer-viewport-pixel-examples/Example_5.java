/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.util.Random;
import java.awt.Color;

/**
   This program writes random pixel data to a PPM file using the FrameBuffer class.
<p>
   Take the resulting PPM file and convert it into a PNG file and also a JPG
   file. Those are "compressed" file formats so the PNG file and the JPG file
   should be smaller than the original PPM file. But are they?
*/
public class Example_5
{
   public static void main(String[] args)
   {
      final String filename = "Example_5";

      final int height = 600;
      final int width  = 600;
      final FrameBuffer fb = new FrameBuffer(width, height);

      final Random random = new Random();

      for (int y = 0; y < height; ++y)
      {
         for(int x = 0; x < width; ++x)
         {
            final int r = random.nextInt(256);
            final int g = random.nextInt(256);
            final int b = random.nextInt(256);
            fb.setPixelFB(x, y, new Color(r, g, b));
         }
      }

      fb.dumpFB2File(filename + ".ppm");
      fb.dumpFB2File(filename + ".png", "png");
      fb.dumpFB2File(filename + ".jpg", "jpg");
      fb.dumpFB2File(filename + ".gif", "gif");
   }
}
