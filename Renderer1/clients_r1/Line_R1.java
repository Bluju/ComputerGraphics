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
   The output in the console window shows you the debugging
   information from the renderer. It shows the results from
   each stage of the rendering pipeline. This program draws
   a single line, so the scene's model contains just two
   vertices and one line segment.
   <p>
   Look at the output in the console window to see how each
   vertex gets projected into the view plane and then how
   the line segment gets rasterized into the framebuffer.
   <p>
   This program's framebuffer is small on purpose so that
   there isn't too much console output from the rasterizer.
   The framebuffer is only 100 pixels by 100 pixels, so the
   single line segment from the scene ends up covering
   only a few dozen pixels.
   <p>
   Use IrfanView to compare the pixel coordinates listed
   in the console window with the actual pixels in the PPM
   file.
   <p>
   Change the first vertex from (5, 3, -6) to (5, 3, -4).
   This will cause the line segment to be clipped. Notice
   how that vertex now projects outside the view rectangle.
   Look in the console window to find the line segment's
   last pixel in the framebuffer. Use IrfanView to find
   the coordinates of where the line segment hits the
   edge of the window.
*/
public class Line_R1
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("Line_R1");
      final Model model = new Model("Line");
      scene.addPosition(new Position(model, "p0"));

      model.addVertex(new Vertex( 5, 3,   -6),  // try (5, 3, -4)
                      new Vertex(-1, 0.5, -2));

      model.addPrimitive(new LineSegment(0, 1));

      final int width  = 100;
      final int height = 100;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      Rasterize.doClipping = true; // Try setting this false.
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("Line_R1.ppm");
   }
}
