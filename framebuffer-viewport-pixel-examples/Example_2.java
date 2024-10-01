/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.awt.Color;

/**
   This program writes pixel data to a PPM file using the FrameBuffer class.
   The pixel data is a sequence of red, then green, then blue pixels.
*/
public class Example_2
{
   public static void main(String[] args)
   {
      final String filename = "Example_2.ppm";

      final int height = 600;  // try 601
      final int width  = 600;
      final FrameBuffer fb = new FrameBuffer(width, height);

      final Color color[] = {new Color(255, 0,     0),  // red
                             new Color(0,   255,   0),  // green
                             new Color(0,   0,   255)}; // blue

      for (int y = 0; y < height; ++y)
      {
         for(int x = 0; x < width; ++x)
         {
            fb.setPixelFB(x, y, color[x % 3]); // a red, green, or blue pixel
         }
      }

      fb.dumpFB2File(filename);
   }
}
