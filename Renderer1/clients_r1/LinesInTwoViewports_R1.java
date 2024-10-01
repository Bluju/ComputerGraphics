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
   This version draws the same lines as Lines.java but it
   draws them twice, once in each of two viewports within
   the framebuffer. Each viewport has a different background
   color, so you can easily see where each viewport is located
   within the framebuffer.
   <p>
   Use IrfanView to compare the pixel coordinates listed
   in the console window with the actual pixels in the PPM
   file. IrfanView will tell you the framebuffer (image)
   coordinate for every pixel. If you add the viewport's
   upper left-hand corner coordinate to each pixel's viewport
   coordinate, you should get the pixel's framebuffer (image)
   coordinate.
*/
public class LinesInTwoViewports_R1
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("LinesInTwoViewports_R1");
      final Model model = new Model("Lines");
      scene.addPosition(new Position(model, "p0"));

      model.addVertex(new Vertex( 5, 4,  -6),
                      new Vertex(-1, .5, -2),
                      new Vertex( 5, 4,  -2)); // try (0, 4, -3)

      model.addPrimitive(new LineSegment(0, 1),
                         new LineSegment(1, 2));

      final int widthFB  = 300;
      final int heightFB = 200;
      final FrameBuffer fb = new FrameBuffer(widthFB, heightFB, Color.darkGray);

      final int widthVP  = 100;
      final int heightVP = 100;
      FrameBuffer.Viewport vp1 = fb.new Viewport( 50, 50, widthVP, heightVP, Color.gray);
      FrameBuffer.Viewport vp2 = fb.new Viewport(150, 50, widthVP, heightVP, Color.black);
      vp1.clearVP();
      vp2.clearVP();

      scene.debug = true;
      Rasterize.debug = true;

      Rasterize.doClipping = true;
      Pipeline.render(scene, vp1);

      Rasterize.doClipping = false;
      Pipeline.render(scene, vp2);

      fb.dumpFB2File("LinesInTwoViewports_R1.ppm");
   }
}
