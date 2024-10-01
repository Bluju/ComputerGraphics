/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import java.awt.Color;

/**
   Copy this code into the Java Visualizer web site.
      https://cscircles.cemc.uwaterloo.ca/java_visualize/

   The main() method instantiates each one of
   the two implementations of a framebuffer,
      a 2d array of Color objects and as
      a 1d (row-major) array in ints.
   You can then see, in the Java Visualizer, the
   difference in the complexity of these two
   implementations.
*/
public class FrameBufferImplementations
{
   public static void main(String[] args)
   {
      FrameBuffer2D fb2 = new FrameBuffer2D(4, 5);
      System.out.println( fb2 );

      System.out.println();

      FrameBuffer1D fb1 = new FrameBuffer1D(4, 5);
      System.out.println( fb1 );
   }
}


/**
   This class simulates a framebuffer with a
   two-dimensional array of Color for storing pixels.

   In the Java Visualizer web site you can see why
   this does not make for an efficient data structure.

   This is why Java represents pixel data using a
   one-dimensional, row-major, array of integer.

   https://blogs.oracle.com/javamagazine/post/java-array-objects
*/
class FrameBuffer2D
{
   public final Color[][] pixel_buffer;

   public FrameBuffer2D(final int width, final int height)
   {
      this.pixel_buffer = new Color[height][width];
      for (int i = 0; i < height; ++i)
      {
         for (int j = 0; j < width; ++j)
         {
            pixel_buffer[i][j] = Color.black;
         }
      }
   }

   @Override public String toString()
   {
      final int width  = pixel_buffer[0].length;
      final int height = pixel_buffer.length;
      return "FrameBuffer2D: width = "+width+", height = "+height+",\n"
              + java.util.Arrays.deepToString(pixel_buffer);
   }
}


/**
   This class simulates a framebuffer with a one-dimensional,
   row-major array of int for storing pixels.
*/
class FrameBuffer1D
{
   public final int width;
   public final int height;
   public final int[] pixel_buffer;

   public FrameBuffer1D(final int width, final int height)
   {
      this.width = width;
      this.height = height;
      this.pixel_buffer = new int[height * width];
   }

   @Override public String toString()
   {
      return "FrameBuffer1D: width = "+width+", height = "+height+",\n"
              + java.util.Arrays.toString(pixel_buffer);
   }
}
