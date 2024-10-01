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
   Draw a scene with five copies of the "square
   within a square" model from the previous examples.

   This time, we want all five copies of the model in
   the scene at the same time, so that we can render the
   scene just once. We do this with five Position objects
   that each hold a reference to a single instance of our
   Model object, but each Position object has a reference
   to a different translation Vector.
*/
public class Example3
{
   public static void main(String[] args)
   {
      final Model decoratedSquare = new Model("Decorated Square");

      decoratedSquare.addVertex(new Vertex(-1, -1, 0),
                                new Vertex(-1,  0, 0),
                                new Vertex(-1,  1, 0),
                                new Vertex( 0,  1, 0),
                                new Vertex( 1,  1, 0),
                                new Vertex( 1,  0, 0),
                                new Vertex( 1, -1, 0),
                                new Vertex( 0, -1, 0));

      decoratedSquare.addPrimitive(new LineSegment(0, 2),
                                   new LineSegment(2, 4),
                                   new LineSegment(4, 6),
                                   new LineSegment(6, 0),
                                   new LineSegment(1, 3),
                                   new LineSegment(3, 5),
                                   new LineSegment(5, 7),
                                   new LineSegment(7, 1));

      final Scene scene = new Scene();

      scene.addPosition(new Position(decoratedSquare, "p1",
                                     new Vector(0, 0, -3))); // backwards

      scene.addPosition(new Position(decoratedSquare, "p2",
                                     new Vector(1.5, 1.5, -5.5))); // right, up, back

      scene.addPosition(new Position(decoratedSquare, "p3",
                                     new Vector(-1.5, 1.5, -5.5))); // left, up, back

      scene.addPosition(new Position(decoratedSquare, "p4",
                                     new Vector(-1.5, -1.5, -5.5))); // left, down, back

      scene.addPosition(new Position(decoratedSquare, "p5",
                                     new Vector(1.5, -1.5, -5.5))); // right, down, back

      final FrameBuffer fb = new FrameBuffer(800, 800);

      Pipeline.render(scene, fb);  // render one time
      fb.dumpFB2File("Example3.ppm");

      // Use GraphViz to draw pictures of the Scene data structure.
      DrawSceneGraph.drawVertexList = true;
      DrawSceneGraph.draw(scene, "Example3_SG");
   }
}
