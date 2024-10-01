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
   two lines that use three vertices.
   <p>
   Look at the output in the console window to see how the
   three vertices gets projected into the view plane and
   then how the two line segments gets rasterized into the
   framebuffer.
   <p>
   This program's framebuffer is small on purpose so that
   there isn't too much console output from the rasterizer.
   The framebuffer is only 100 pixels by 100 pixels, so the
   two line segments from the scene end up covering only a
   few dozen pixels.
   <p>
   Use IrfanView to compare the pixel coordinates listed
   in the console window with the actual pixels in the PPM
   file.
   <p>
   Notice that one of the line segments is clipped. Notice
   how its vertex projects outside the view rectangle.
   Look in the console window to find that line segment's
   last pixel in the framebuffer. Use IrfanView to find
   the coordinates of where that line segment hits the
   edge of the window.
   <p>
   Change the third vertex from (5, 4, -3) to (0, 4, -3).
   Notice that the resulting line segment is now sloped
   at more that 45 degrees, so that line segment is now
   rasterized along the vertical y-axis. Look at how the
   output in the console window has changed for that line.
*/
public class Lines_R1
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("Lines_R1");
      final Model model = new Model("Lines");
      scene.addPosition(new Position(model, "p0"));

      model.addVertex(new Vertex( 5, 4,   -6),
                      new Vertex(-1, 0.5, -2),
                      new Vertex( 5, 4,   -3)); // try (0, 4, -3)

      model.addPrimitive(new LineSegment(0, 1),
                         new LineSegment(1, 2));

      final int width  = 100;
      final int height = 100;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      scene.debug = true;
      Rasterize.debug = true;
      Rasterize.doClipping = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("Lines_R1.ppm");
   }
}
