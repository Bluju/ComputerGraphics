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
   This program writes pixel data to a PPM file using the FrameBuffer
   and Viewport class.
<p>
   This program creates three Viewports within a single FrameBuffer
   and then it writes the exact same pixel data into each viewport.
   That pixel data appears in three places in the FrameBuffer.
*/
public class Example_4
{
   public static void main(String[] args)
   {
      final String filename = "Example_4.ppm";

      final int height = 600;
      final int width  = 600;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.white);

      // Create three viewports within the framebuffer.
      final int heightVP = 200;
      final int widthVP  = 250;
      FrameBuffer.Viewport vp1 = fb.new Viewport( 20,  20, heightVP, widthVP); // upper-left-hand-corner, width, height
      FrameBuffer.Viewport vp2 = fb.new Viewport(300, 100, heightVP, widthVP); // upper-left-hand-corner
      FrameBuffer.Viewport vp3 = fb.new Viewport(150, 350, heightVP, widthVP); // upper-left-hand-corner, width, height

      final Color color[] = {Color.red, Color.green, Color.blue, Color.magenta};

      // stripes
      for (int y = 0; y < heightVP; ++y)
      {
         for(int x = 0; x < widthVP; ++x)
         {
            vp1.setPixelVP(x, y, color[ (x / 25) % 4 ]);
            vp2.setPixelVP(x, y, color[ (x / 25) % 4 ]);
            vp3.setPixelVP(x, y, color[ (x / 25) % 4 ]);
         }
      }
      // and a 3-pixel wide diagonal line
      final double slope = (double)heightVP / (double)widthVP;
      for(int x = 0; x < widthVP; ++x)
      {
         final int y = (int)Math.round(slope * x);
         vp1.setPixelVP(x, y, Color.orange);
         vp2.setPixelVP(x, y, Color.orange);
         vp3.setPixelVP(x, y, Color.orange);

         vp1.setPixelVP(x, y-1, Color.orange);
         vp2.setPixelVP(x, y-1, Color.orange);
         vp3.setPixelVP(x, y-1, Color.orange);

         vp1.setPixelVP(x, y+1, Color.orange);
         vp2.setPixelVP(x, y+1, Color.orange);
         vp3.setPixelVP(x, y+1, Color.orange);
      }

      fb.dumpFB2File(filename);
   }
}
