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
   This program shows that there is something wrong with our
   way of clipping. This program creates a line segment that
   is parallel to the z-axis with one vertex right on the edge
   of the camera's view volume and the other vertex behind the
   camera (it has a positive z-coordinate). This line segment
   never enters the view volume, so it should not be visible.
   But the renderer draws it as a line segment going across the
   whole framebuffer. The reason is that the vertex behind the
   camera gets projected in front of the camera but reflected
   to the opposite side of the origin. So then one vertex is on
   the positive side of the origin and the other vertex is on the
   negative side of the origin, so the line segment seems to cross
   from one side to the other.
   <p>
   If you switch the camera to use parallel projection, then this
   line segment, since it is parallel to the z-axis, projects to
   a single point, and it rasterizes into a single pixel.
*/
public class SomethingWrong_2
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("SomethingWrong_2");
      final Model model = new Model("Something Wrong 2");
      scene.addPosition(new Position(model, "p0"));

      model.addVertex(new Vertex(0.9, 0.0, -0.9),  // edge of the view volume
                      new Vertex(0.9, 0.0,  0.9)); // behind the camera

      model.addPrimitive(new LineSegment(0, 1));

      final int width  = 100;
      final int height = 100;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      scene.debug = true;
      Rasterize.debug = true;

      Pipeline.render(scene, fb);
      fb.dumpFB2File("SomethingWrong_2a.ppm");

      final Scene scene2 = scene.changeCamera(Camera.projOrtho());
      fb.clearFB();
      Pipeline.render(scene2, fb);
      fb.dumpFB2File("SomethingWrong_2b.ppm");
   }
}
