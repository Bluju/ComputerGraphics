/*
   A 350x250 FrameBuffer containing a 250x150 Viewport
   with upper left-hand corner at (50,50).

   (0,0)
     +----------------------------------------+
     | (50,50)                                |
     |    +------------------------------+    |
     |    |                              |    |
     |    |                              |    |
     |    |                              |    |
     |    |                              |    |
     |    |                              |    |
     |    |                              |    |
     |    |                              |    |
     |    |                              |    |
     |    |                              |    |
     |    +------------------------------+    |
     |                               (299,199)|
     +----------------------------------------+
                                          (349,249)


      FrameBuffer fb = new FrameBuffer(350, 250);
      FrameBuffer.Viewport vp = fb.new Viewport(50, 50, 250, 150);
      vp.setPixelVP(325, 100, Color.white);

   Where is this pixel in the FrameBuffer?
   Where is this pixel in the FrameBuffer's 1-dimensional pixel array?
*/

import framebuffer.FrameBuffer;

import java.awt.Color;

public class Quiz
{
   public static void main(String[] args)
   {
      FrameBuffer fb = new FrameBuffer(350, 250);
      FrameBuffer.Viewport vp = fb.new Viewport(50, 50, 250, 150);
      vp.clearVP(Color.gray); // make the viewport visible
      vp.setPixelVP(325, 100, Color.white);

      fb.dumpFB2File("Quiz.ppm");
   }
}
