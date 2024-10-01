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
   The exact same pixel data is written as in Example_3.java but the Yoda
   pixel data is read from a RAW image file (instead of a PPM image file).
*/
public class Example_7
{
   public static void main(String[] args)
   {
      final String filename = "Example_7.ppm";

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

      // Create a "viewport" within the framebuffer to hold the raw Yoda image.
      vp = fb.new Viewport(300, 300, 256, 256); // upper-left-hand-corner, width, height

      // Read the pixel data in a RAW file.
      final String inputFileName = "assets/Yoda.raw";
      try
      {
         final FileInputStream fis = new FileInputStream(inputFileName);
         final BufferedInputStream bis = new BufferedInputStream(fis);

         // Create a data array.
         final byte[] pixelData = new byte[3]; // three bytes is one pixel
         // Read pixel data, one pixel at a time, from the RAW file.
         for (int y = 0; y < 256; ++y)
         {
            for (int x = 0; x < 256; ++x)
            {
               if ( bis.read(pixelData, 0, 3) != 3 ) // read one pixel (3 bytes)
               {
                  System.err.printf("ERROR! Could not load %s\n", inputFileName);
                  System.exit(-1);
               }
               int r = pixelData[0];
               int g = pixelData[1];
               int b = pixelData[2];
               if (r < 0) r = 256+r;  // convert from signed byte to unsigned byte
               if (g < 0) g = 256+g;
               if (b < 0) b = 256+b;
               vp.setPixelVP(x, y, new Color(r, g, b));
            }
         }

         fis.close();
      }
      catch (IOException e)
      {
         e.printStackTrace(System.err);
         System.err.printf("ERROR! Could not read %s\n", inputFileName);
         System.exit(-1);
      }

      fb.dumpFB2File(filename);
   }
}
