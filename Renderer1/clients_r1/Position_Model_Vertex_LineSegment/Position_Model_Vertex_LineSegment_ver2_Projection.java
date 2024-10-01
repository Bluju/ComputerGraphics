/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.scene.util.DrawSceneGraph;
import renderer.framebuffer.*;
import renderer.pipeline.*;

/**
   Use this program to analyze the
   Projection.java pipeline stage.
*/
public class Position_Model_Vertex_LineSegment_ver2_Projection
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("Position_Model_Vertex_LineSegment_ver2");

      // Use these four vertex objects
      // to build two different models.
      final Vertex v0 = new Vertex(0, 0, 0),
                   v1 = new Vertex(1, 0, 0),
                   v2 = new Vertex(1, 1, 0),
                   v3 = new Vertex(0, 1, 0);

      // Create two model objects.
      final Model model_0 = new Model("Model_0");
      final Model model_1 = new Model("Model_1");

      // Add the four vertex objects to
      // each of the two model objects.
      model_0.addVertex(v0, v1, v2, v3);
      model_1.addVertex(v0, v1, v2, v3);

      // Make the two models look different by
      // giving them different line segments.
      model_0.addPrimitive(new LineSegment(3, 2),
                           new LineSegment(2, 0),
                           new LineSegment(0, 1));

      model_1.addPrimitive(new LineSegment(3, 0),
                           new LineSegment(0, 1),
                           new LineSegment(1, 2));

      // Add the model objects to the scene by
      // placing each model in a position object.
      scene.addPosition(
               new Position(model_0, "p0", new Vector( 1,  1, -3)),
               new Position(model_1, "p1", new Vector(-2, -2, -3)));

      final int width  = 600;
      final int height = 600;
      final FrameBuffer fb = new FrameBuffer(width, height);

      System.out.println( scene );
      scene.debug = true;

      Pipeline.render(scene, fb);
      fb.dumpFB2File("Position_Model_Vertex_LineSegment_ver2_Projection.ppm");

      // Draw a picture of the scene's tree data structure.
      DrawSceneGraph.drawVertexList = true;
      DrawSceneGraph.draw(scene, "Position_Model_Vertex_LineSegment_ver2_Projection_SG");
   }
}

/*
Here is what the Scene data structure looks like AFTER the projection transformation.

                       Scene
                      /     \
                     /       \
               Camera         List<Position>
                                    /    \
                       /-----------/      \------------------\
                      /                                       \
                 Position                                   Position
                  /     \                                    /     \
                 /       \                                  /       \
             Vector       Model                         Vector       Model
             / | \       /     \                        / | \       /     \
            x  y  z     /       \                      x  y  z     /       \
            1  1 -3    /         \                    -2 -2 -3    /         \
                      /           \                              /           \
       List<Primitive>             List<Vertex>            List<Vertex>       List<Primitive>
      /     /  \     \                 /  |  \ \                | | | |      /       /  \    \
     /     /    \     \               /   |   \ \----\          | | | |     /       /    \    \
    /     /      \     \             /    |    \      \         | | | |    /       /      \    \
   / LineSegment  \     \        Vertex Vertex Vertex Vertex    | | | |    |  LineSegment  \    \
  /    |   LineSegment   \         |      |      |      |       | | | |    |    |   LineSegment  \
 /     |        |   LineSegment   /|\    /|\    /|\    /|\      | | | |    |    |        |   LineSegment
|      |        |        |       x y z  x y z  x y z  x y z     | | | |    |    |        |        |
|    int[2]   int[2]   int[2]    0 0 0  1 0 0  1 1 0  0 1 0     | | | |    |  int[2]   int[2]   int[2]
|    {3, 2}   {2, 0}   {0, 1}     \|/    \|/    \|/    \|/      | | | |    |  {3, 0}   {0, 1}   {1, 2}
|                                  |      |      |      |       | | | |    |
|                                  |      |      |      +-------|-|-|-+    |
|                                  |      |      +--------------|-|-+      |
|\                                 |      +---------------------|-+        |
| \                                +----------------------------+          |
|  \                                                                       |
|   \           Model                                           Model      /\     //// Model2Camera stage ////
|    \          /   \                                           /   \     /  \
|     \--------/     \                                         /     \---/    \
|                     \                                       /                \
|              List<Vertex>                         List<Vertex>                \
|             /  |     |   \                       /  |     |    \               \
|            /   |     |    \                     /   |     |     \              |
|           /    |     |     \                   /    |     |      \             |
|      Vertex  Vertex Vertex  Vertex       Vertex  Vertex  Vertex   Vertex       |
|      /| \    /| \    /| \    /| \       / | \     / | \    / | \    / | \      |
\     x y  z  x y  z  x y  z  x y  z     x  y  z   x  y  z  x  y  z  x  y  z     |
 \    1 1 -3  2 1 -3  2 2 -3  1 2 -3    -2 -2 -3  -1 -2 -3 -1 -1 -3 -2 -1 -3     |
  \                                                                              |
   \            Model                                           Model            /  //// Projection stage ////
    \           /   \                                           /   \           /
     \---------/     \                                         /     \---------/
                      \                                       /
               List<Vertex>                             List<Vertex>
              /    | |     \                           /     | |    \
             /     | |      \                         /      | |     \
       Vertex      | |       Vertex              Vertex      | |      Vertex
      /  |  \      | |      /  |  \             /  |  \      | |      /  |  \
     /   |   \     | |     /   |   \           /   |   \     | |     /   |   \
    x    y    z    / \    x    y    z         x    y    z    / \    x    y    z
 0.33  0.33 -1.0  /   \ 0.33 0.67 -1.0    -0.67 -0.67 -1.0  /   \ -0.67 -0.33 -1.0
                 /     \                                   /     \
                /       \                                 /       \
         Vertex          Vertex                    Vertex          Vertex
         / |  \          /  |  \                   / |  \          /  |  \
        /  |   \        /   |   \                 /  |   \        /   |   \
       x   y    z      x    y    z               x   y    z      x    y    z
    0.67 0.33 -1.0   0.67 0.67 -1.0          -0.33 -0.67 -1.0  -0.33 -0.33 -1.0
*/
