/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.FrameBuffer;

import java.util.Random;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
   This program writes pixel data to a PPM file using the FrameBuffer class.
   The pixel data is a sequence of red, green, and blue stripes.
<p>
   In addition, this example creates two "viewports" within the framebuffer.
   One viewport holds random pixels and the other viewport contains the
   Yoda picture.
*/
public class Example_3
{
   
   public static void main(String[] args)
   {
      final String filename = "Example_3.ppm";

      final int height = 600;
      final int width  = 600;
      final FrameBuffer fb = new FrameBuffer(width, height);

      final Color color[] = {new Color(255, 0,     0),  // red
                             new Color(0,   255,   0),  // green
                             new Color(0,   0,   255)}; // blue

      for (int y = 0; y < height; ++y)
      {
         for(int x = 0; x < width; ++x)
         {
            // Create red, green, blue stripes.
            // How do you change the width of a stripe?
            // How do you change the number of alternating colors?
            fb.setPixelFB(x, y, color[ (x / 5) % 3 ]); // integer and modular division
         }
      }

      // Create a "viewport" within the framebuffer to hold random pixels.
      FrameBuffer.Viewport vp = fb.new Viewport(64, 64, 256, 128); // upper-left-hand-corner, width, height

      final Random random = new Random();
      for (int y = 0; y < 128; ++y)
      {
         for (int x = 0; x < 256; ++x)
         {
            final int r = random.nextInt(256);
            final int g = random.nextInt(256);
            final int b = random.nextInt(256);
            vp.setPixelVP(x, y, new Color(r, g, b));
         }
      }

      // Create a "viewport" within the framebuffer to hold the Yoda image. The ViewPort is an 'innerclass' of FrameBuffer (nested class)
      vp = fb.new Viewport(300, 300, "assets/Yoda.ppm"); // upper-left-hand-corner //ties the Viewport class to the framebuffer
      //for split screen, create 1 framebuffer and split it into two viewports

      fb.dumpFB2File(filename);
   }
}
