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
   This program shows that there is something wrong with doing
   clipping in the rasterizer. This program creates a line segment
   very near to the camera at (0, 0, 0) but slightly outside of
   the camera's view volume. The projection stage will transform
   this line segment into a very, very long line segment outside
   of the camera's view rectangle. The rasterizer will rasterize
   the whole line segment and find out that none of its pixels
   are visible. But it takes the rasterizer so long to rasterize
   this line that it seems to be in an infinite loop.
   <p>
   Turn on the rasterizer's debugging to see what is happening.
   <p>
   This problem can easily come up if you are moving a model
   around in camera space and one of the model's vertices
   almost lands on the camera.
*/
public class SomethingWrong_1
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("SomethingWrong_1");
      final Model model = new Model("Something Wrong 1");
      scene.addPosition(new Position(model, "p0"));

      model.addVertex(new Vertex(0.00000002, 0.00000002, -0.00000001),
                      new Vertex(1.0,        1.0,        -0.00000001));

      model.addPrimitive(new LineSegment(0, 1));

      final int width  = 100;
      final int height = 100;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      scene.debug = true;
      Rasterize.debug = true;

      Pipeline.render(scene, fb);
      fb.dumpFB2File("SomethingWrong_1.ppm");
   }
}
