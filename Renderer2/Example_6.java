/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import java.io.FileOutputStream;
import java.io.IOException;

/**
   This program writes pixel data to a RAW file.
   The exact same pixel data is written as in Example_2.java.
<p>
   Notice that this program doesn't use a FrameBuffer object.
   It just writes the (raw) pixel data directly to a file.
<p>
   You can open the raw pixel data with Irfanview, but you
   need to tell Irfanview about the pixel data's "meta data".
*/
public class Example_6
{
   public static void main(String[] args)
   {
      final String filename = "Example_6.raw";

      final int height = 600;
      final int width  = 600;
      // create a 1-dimensional, row major, pixel buffer
      final byte[] pixel_buffer = new byte[height * width * 3];

      // put pixel data in the pixel buffer
      for (int i = 0; i < height*width*3; i+=9)
      {
         pixel_buffer[i+0] = (byte)255;  // a red pixel
         pixel_buffer[i+1] = 0;
         pixel_buffer[i+2] = 0;

         pixel_buffer[i+3] = 0;    // followed by a green pixel
         pixel_buffer[i+4] = (byte)255;
         pixel_buffer[i+5] = 0;

         pixel_buffer[i+6] = 0;    // followed by a blue pixel
         pixel_buffer[i+7] = 0;
         pixel_buffer[i+8] = (byte)255;
      }

      try  // write the pixel buffer to the raw output file
      {
         final FileOutputStream fos = new FileOutputStream(filename);
         fos.write(pixel_buffer);
         fos.close();
      }
      catch (IOException e)
      {
         e.printStackTrace(System.err);
         System.err.printf("ERROR! Could not write to file %s\n", filename);
         System.exit(-1);
      }
   }
}
