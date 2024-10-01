/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import framebuffer.*;

import java.awt.Color;

/**
   Create a small FrameBuffer object containing a Viewport.
   Print the FrameBuffer and Viewport to the console window.
   This lets us see how the Viewport's pixels are stored in
   the FrameBuffer's pixel buffer.
*/
public class ViewportInFrameBuffer
{
   public static void main(String[] args)
   {
      final int widthFB  = 8;
      final int heightFB = 5;
      final FrameBuffer fb = new FrameBuffer(widthFB, heightFB, Color.white);

      final int widthVP  = 4;
      final int heightVP = 3;
      FrameBuffer.Viewport vp = fb.new Viewport(2, 1, widthVP, heightVP, Color.black);
      vp.clearVP();

      System.out.println( fb );
      System.out.println();
      System.out.println( java.util.Arrays.toString(fb.pixel_buffer) );

      fb.dumpFB2File("ViewportInFrameBuffer.ppm");
   }
}
