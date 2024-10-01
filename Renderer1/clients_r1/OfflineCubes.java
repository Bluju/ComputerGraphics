/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.pipeline.*;
import renderer.framebuffer.*;
import renderer.scene.util.DrawSceneGraph;

import java.awt.Color;

/**
   Create an animation of two cubes moving in 3D space.
<p>
   This version creates one cube Model for each cube
   in the Scene.

<pre>{@code
                  y
                  |
                  |
                  | v[4]
                1 +---------------------+ v[5]=(1,1,0)
                 /|                    /|
                / |                   / |
               /  |                  /  |
              /   |                 /   |
             /    |                /    |
       v[7] +---------------------+ v[6]|
            |     |               |     |
            |     |               |     |
            |     | v[0]          |     | v[1]
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
public class OfflineCubes
{
   public static void main(String[] args)
   {
      // Create the Scene object that we shall render.
      final Scene scene = new Scene("OfflineCubes");

      // Create Model objects to hold the geometry.
      final Model model1 = new Model("Cube 1");
      final Model model2 = new Model("Cube 2");

      // Add the Models to the Scene, pushed
      // away from where the camera is.
      scene.addPosition(new Position(model1, "p1",
                                     new Vector(0.0,  0.0, -4.0)));
      scene.addPosition(new Position(model2, "p2",
                                     new Vector(0.0,  0.0, -4.0)));

      // Add geometry to the Models.
      // First cube.
      model1.addVertex(new Vertex(0.0, 0.0, 0.0), // four vertices around the bottom face
                       new Vertex(1.0, 0.0, 0.0),
                       new Vertex(1.0, 0.0, 1.0),
                       new Vertex(0.0, 0.0, 1.0),
                       new Vertex(0.0, 1.0, 0.0), // four vertices around the top face
                       new Vertex(1.0, 1.0, 0.0),
                       new Vertex(1.0, 1.0, 1.0),
                       new Vertex(0.0, 1.0, 1.0));

      // Second cube.
      model2.addVertex(new Vertex(0.0, 0.0, 0.0),  // four vertices around the bottom face
                       new Vertex(2.0, 0.0, 0.0),
                       new Vertex(2.0, 0.0, 2.0),
                       new Vertex(0.0, 0.0, 2.0),
                       new Vertex(0.0, 2.0, 0.0),  // four vertices around the top face
                       new Vertex(2.0, 2.0, 0.0),
                       new Vertex(2.0, 2.0, 2.0),
                       new Vertex(0.0, 2.0, 2.0));

      // First cube.
      model1.addPrimitive(new LineSegment(0, 1),  // bottom face
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

      // Second cube.
      model2.addPrimitive(new LineSegment(0, 1),  // bottom face
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

      // Draw a picture of the scene's tree data structure.
      DrawSceneGraph.draw(scene, "SG_OfflineCubes");

      // Create a FrameBuffer to render our Scene into.
      final int width  = 1024;
      final int height = 1024;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      Rasterize.doClipping = false;  // turn off clipping

      for (int j = 0; j <= 100; ++j)
      {
         // Render again.
         fb.clearFB(); // What happens if we don't clear the framebuffer?
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_OfflineCubes_Frame%03d.ppm", j));

         // Update the scene by translating each
         // of the positions in camera space.
         final Vector t0 = scene.getPosition(0).getTranslation();
         scene.getPosition(0).translate(t0.x - 0.1,  // move left
                                        t0.y - 0.03, // move up
                                        t0.z);
         final Vector t1 = scene.getPosition(0).getTranslation();
         scene.getPosition(1).translate(t1.x + 0.1,  // move right
                                        t1.y,
                                        t1.z);
      }
   }
}
