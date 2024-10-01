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
   Create an animation of a cube moving in 3D space.

<pre>{@code
                  y
                  |
                  |
                  | v[4]=(0,1,0)
                1 +---------------------+ v[5]=(1,1,0)
                 /|                    /|
                / |                   / |
               /  |                  /  |
              /   |                 /   |
             /    |                /    |
       v[7] +---------------------+ v[6]|
            |     |               |     |
            |     |               |     |
            |     | v[0]=(0,0,0)  |     | v[1]=(1,0,0)
            |     +---------------|-----+------> x
            |    /                |    /1
            |   /                 |   /
            |  /                  |  /
            | /                   | /
            |/                    |/
          1 +---------------------+
           /v[3]=(0,0,1)          v[2]=(1,0,1)
          /
         /
        z
}</pre>
*/
public class OfflineCube
{
   public static void main(String[] args)
   {
      // Create the Scene object that we shall render.
      final Scene scene = new Scene("OfflineCube");

      // Create a Model object to hold the geometry.
      final Model model = new Model("Cube");

      // Add the Model to the Scene, pushed
      // away from where the camera is.
      scene.addPosition(new Position(model, "p0",
                                     new Vector(0.0,  0.0, -2.0)));

      // Create the geometry for the Model.
      model.addVertex(new Vertex(0.0, 0.0, 0.0), // four vertices around the bottom face
                      new Vertex(1.0, 0.0, 0.0),
                      new Vertex(1.0, 0.0, 1.0),
                      new Vertex(0.0, 0.0, 1.0),
                      new Vertex(0.0, 1.0, 0.0), // four vertices around the top face
                      new Vertex(1.0, 1.0, 0.0),
                      new Vertex(1.0, 1.0, 1.0),
                      new Vertex(0.0, 1.0, 1.0));

      // Add the geometry to the Model.
      model.addPrimitive(new LineSegment(0, 1),  // bottom face
                         new LineSegment(1, 2),  // bottom face
                         new LineSegment(2, 3),  // bottom face
                         new LineSegment(3, 0),  // bottom face
                         new LineSegment(4, 5),  // top face
                         new LineSegment(5, 6),  // top face
                         new LineSegment(6, 7),  // top face
                         new LineSegment(7, 4),  // top face
                         new LineSegment(0, 4),  // back face
                         new LineSegment(1, 5),  // back face
                         new LineSegment(3, 7),  // front face
                         new LineSegment(2, 6)); // front face

      // Log information to standard output.
      System.out.println( scene.toString() );

      // Create a FrameBuffer to render our Scene into.
      final int width  = 1024;
      final int height = 1024;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      // Render our Scene into the framebuffer once with debugging.
      scene.debug  = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_OfflineCube_Frame%03d.ppm", 0));

      scene.debug  = false;          // turn off debugging
      Rasterize.doClipping = false;  // turn off clipping

      for (int j = 1; j <= 100; ++j)
      {
         // Update the scene by translating in camera
         // space the position that holds the cube.
         final Vector t = scene.getPosition(0).getTranslation();
         scene.getPosition(0).translate(t.x + 0.1, // move right
                                        t.y,
                                        t.z);

         // Render into the framebuffer again.
         fb.clearFB(); // What happens if we don't clear the framebuffer?
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_OfflineCube_Frame%03d.ppm", j));
      }
   }
}
