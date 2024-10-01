/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

/**
   This code simulates a Framebuffer class with a
   Viewport inner class.

   Copy this code into the Java Visualizer web site.

   https://cscircles.cemc.uwaterloo.ca/java_visualize/
*/
public class ViewportInFramebufferSimulation
{
   public static void main(String[] args)
   {
      Framebuffer fb1 = new Framebuffer(200, 200);
      Framebuffer fb2 = new Framebuffer(100, 100);
      Framebuffer.Viewport vp1 = fb1.new Viewport( 30,  30, 50, 50);
      Framebuffer.Viewport vp2 = fb1.new Viewport(100, 100, 40, 40);
      Framebuffer.Viewport vp3 = fb2.new Viewport( 30,  30, 60, 60);
      System.out.println( vp1 );
      System.out.println( vp2 );
      System.out.println( vp3 );
   }
}

class Framebuffer
{
   public final int width;
   public final int height;

   public Framebuffer(final int width, final int height) {
      this.width = width;
      this.height = height;
   }

   @Override public String toString() {
      return "Framebuffer: w="+width+", height="+height;
   }

   public class Viewport { // Inner class.
      public final int x;
      public final int y;
      public final int width;
      public final int height;

      public Viewport(final int x, final int y,
                      final int width, final int height) {
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
      }

      @Override public String toString() {
         return "Viewport: w="+width+", height="+height+", in: "+Framebuffer.this.toString();
      }
   }
}

/*
Here is one FrameBuffer containing two Viewports and another
FrameBuffer containing a single Viewport.

   fb1
 +------------------------------------------+
 |                                          |         fb2
 |     +------------+                       |       +-----------------------------+
 |     |            |                       |       |                             |
 |     |            |                       |       |     +------------+          |
 |     |            |                       |       |     |            |          |
 |     |            |                       |       |     |            |          |
 |     +------------+                       |       |     |            |          |
 |                                          |       |     |            |          |
 |                    +------------+        |       |     +------------+          |
 |                    |            |        |       |                             |
 |                    |            |        |       |                             |
 |                    |            |        |       +-----------------------------+
 |                    |            |        |
 |                    +------------+        |
 +------------------------------------------+

Here is the code that instantiates these five objects.

   FrameBuffer fb1 = new FrameBuffer(200, 200);
   FrameBuffer fb2 = new FrameBuffer(100, 100);
   FrameBuffer.Viewport vp1 = fb1.new Viewport( 30,  30, 50, 50);
   FrameBuffer.Viewport vp2 = fb1.new Viewport(100, 100, 50, 50);
   FrameBuffer.Viewport vp3 = fb2.new Viewport( 30,  30, 50, 50);

Notice how the FrameBuffer.Viewport notation reminds us that the
Viewport class is define inside of the FrameBuffer class. So the
Viewport class is kind of like a "field" in the FrameBuffer class
(instead of being a data member or a method member of FrameBuffer,
Viewport is a class member of FrameBuffer).

Notice that the fb1.new and fb2.new notation reminds us that a new
instance of the Viewport class must be tied to a specific instance
of FrameBuffer.

In the Java heap we now have five objects, two FrameBuffer objects and
three ViewPort objects. Each Viewport object is "tied" to a specific
FrameBuffer object. A Viewport does not itself store any pixel data.
Each Viewport object references its FrameBuffer object to access the
pixels that are within the Viewport.
*/
