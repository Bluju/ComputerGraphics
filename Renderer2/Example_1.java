/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.awt.Color;

/**
   This program writes pixel data to a PPM file using the FrameBuffer class.
   The pixel data is a set of lines.
*/
public class Example_1
{
   public static void main(String[] args)
   {
      final String filename = "Example_1.ppm";

      final int height = 600;  // try 601
      final int width  = 600;
      final FrameBuffer fb = new FrameBuffer(width, height); //fb is a reference variable pointing to the new FrameBuffer object in the heap
      //java cannot put objects in the stack like c++ can

      //color is represented by 24 bits (8 bits per R G B)
      //color data struct is stored as a 32 bit int (alpha R G B)
      final Color color[] = {new Color(255, 0,     0),  // red
                             new Color(0,   255,   0),  // green
                             new Color(0,   0,   255),  // blue
                             Color.cyan};

      /// A horizontal line.
      for (int i = 0; i < 250; ++i)
      {
         fb.setPixelFB(50 + i, 50, color[0]); // red line
      }

      /// A vertical line.
      for (int i = 0; i < 100; ++i)
      {
         fb.setPixelFB(300, 50 + i, color[1]); // green line
      }

      /// A horizontal line.
      for (int i = 0; i < 200; ++i)
      {
         fb.setPixelFB(300 + i, 150, color[2]); // blue line
      }

      /// A diagonal line.
      for (int i = 0; i < 250; ++i)
      {
         fb.setPixelFB(50 + i, 50 + i, color[3]); // cyan line
      }

      fb.dumpFB2File(filename);
   }
}
