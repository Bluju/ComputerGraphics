/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.pipeline.*;
import renderer.framebuffer.*;

import java.awt.Color;

/**
<pre>{@code
                        |
                      2 + v[1]
                        |
                        |
                        |
                        |
                  |     |
                  |     |
                2 +     |
                  |     |
            y     |     |
            |     |   1 +
            |     |     |
            |     |     |        -z
          2 +     |     |       /
            |     |     |      /
            |     |     |     /
            |     |     |    /       This 3D space is called "camera space".
            |   1 +     |   /
            |     |     |  /
            |     |     | /
            |     |     |/
            |     |  -2 +-----------------+-----------------+---- y = -2 plane
            |     |    /v[2]              1                 v[0]
          1 +     |   /
            |     |  /
            |     | /
            |     |/
            |  -1 +-----------------+----- image plane (y = -1)
            |    /                  1
            |   /
            |  /
            | /
            |/
     Camera +-----------------+---------> x-axis
           /0                 1
          /
         z-axis
}</pre>
   Render a wireframe triangle. This is just about the
   simplest possible model. It is useful for debugging.
*/
public class OfflineTriangle
{
   public static void main(String[] args)
   {
      // Create the Scene object that we shall render.
      final Scene scene = new Scene("OfflineTriangle");

      // Create a Model object to hold the geometry.
      final Model model = new Model("triangle");

      // Add the Model to the Scene.
      scene.addPosition(new Position(model, "p0"));

      // Create the geometry for the Model.
      model.addVertex(new Vertex(2.0, 0.0, -2.0),
                      new Vertex(0.0, 2.0, -2.0),
                      new Vertex(0.0, 0.0, -2.0));

      // Add the geometry to the Model.
      model.addPrimitive(new LineSegment(0, 1),
                         new LineSegment(1, 2),
                         new LineSegment(2, 0));

      // Log information to standard output.
      System.out.println( scene.toString() );

      // Create a FrameBuffer to render our Scene into.
      final int width  = 1024;
      final int height = 1024;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      // Render our Scene into the framebuffer once with debugging.
      scene.debug  = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_OfflineTriangle_Frame%03d.ppm", 0));

      scene.debug  = false;         // turn off debugging
      Rasterize.debug = false;      // turn off debugging
      Rasterize.doClipping = false; // turn off clipping

      for (int j = 1; j <= 60; ++j)
      {
         // Update the scene by translating in
         // camera space each of the 3 vertices.
         final Vector t = scene.getPosition(0).getTranslation();
         scene.getPosition(0).translate(t.x + 0.2,
                                        t.y,
                                        t.z - 0.05);

         // Render into the framebuffer again.
         fb.clearFB(); // What happens if we don't clear the framebuffer?
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_OfflineTriangle_Frame%03d.ppm", j));
      }
   }
}
