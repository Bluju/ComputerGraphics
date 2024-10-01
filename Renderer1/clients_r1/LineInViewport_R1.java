/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.framebuffer.*;
import renderer.pipeline.*;

import java.awt.Color;

/**
   Compile and run this program. Look at its output, both
   in the console window and in the PPM file that it creates.
   <p>
   This version draws the same line as Line.java but in a
   viewport within a framebuffer. Notice that the debugging
   output tells you the viewport coordinates for rasterized
   pixels.
   <p>
   Use IrfanView to compare the pixel coordinates listed
   in the console window with the actual pixels in the PPM
   file. IrfanView will tell you the framebuffer (image)
   coordinate for every pixel. If you add the viewport's
   upper left-hand corner coordinate to each pixel's viewport
   coordinate, you should get the pixel's framebuffer (image)
   coordinate.
*/
public class LineInViewport_R1
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("LineInViewport_R1");
      final Model model = new Model("Line");
      scene.addPosition(new Position(model, "p0"));

      model.addVertex(new Vertex( 5, 3,   -6),  // try (5, 3, -4)
                      new Vertex(-1, 0.5, -2));

      model.addPrimitive(new LineSegment(0, 1));

      final int widthFB  = 200;
      final int heightFB = 200;
      final FrameBuffer fb = new FrameBuffer(widthFB, heightFB, Color.darkGray);

      final int widthVP  = 100;
      final int heightVP = 100;
      FrameBuffer.Viewport vp = fb.new Viewport(30, 30, widthVP, heightVP, Color.gray);
      vp.clearVP();

      Rasterize.doClipping = true;  // Try setting this false.
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, vp);
      fb.dumpFB2File("LineInViewport_R1.ppm");
   }
}
