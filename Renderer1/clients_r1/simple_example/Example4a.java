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

   This time, we again want all five copies of the model
   in the scene at the same time, so that we can render the
   scene just once. But this time we want there to be five
   distinct instances of the Model object (the last example
   had just one instance of the Model object that was shared
   by five Position objects). The five distinct instances of
   the Model object give us the option of making small changes
   to each one.

   In order to have five instances of the Model object, we need
   to define the Model five times. This can cause a lot of repeated
   code. This example minimizes some of the repeated code by reusing
   the Vertex and LineSegment definitions. In Example5.java we will
   see how to eliminate the repeated code.
*/
public class Example4a
{
   public static void main(String[] args)
   {
      final Vertex v1 = new Vertex(-1, -1, 0),
                   v2 = new Vertex(-1,  0, 0),
                   v3 = new Vertex(-1,  1, 0),
                   v4 = new Vertex( 0,  1, 0),
                   v5 = new Vertex( 1,  1, 0),
                   v6 = new Vertex( 1,  0, 0),
                   v7 = new Vertex( 1, -1, 0),
                   v8 = new Vertex( 0, -1, 0);

     final LineSegment ls1 = new LineSegment(0, 2),
                       ls2 = new LineSegment(2, 4),
                       ls3 = new LineSegment(4, 6),
                       ls4 = new LineSegment(6, 0),
                       ls5 = new LineSegment(1, 3),
                       ls6 = new LineSegment(3, 5),
                       ls7 = new LineSegment(5, 7),
                       ls8 = new LineSegment(7, 1);

      final Model decoratedSquare1 = new Model("Decorated Square 1");
      decoratedSquare1.addVertex(v1, v2, v3, v4, v5, v6, v7, v8);
      decoratedSquare1.addPrimitive(ls1, ls2, ls3, ls4, ls5, ls6, ls7, ls8);

      final Model decoratedSquare2 = new Model("Decorated Square 2");
      decoratedSquare2.addVertex(v1, v2, v3, v4, v5, v6, v7, v8);
      decoratedSquare2.addPrimitive(ls1, ls2, ls3, ls4, ls5, ls6, ls7, ls8);

      final Model decoratedSquare3 = new Model("Decorated Square 3");
      decoratedSquare3.addVertex(v1, v2, v3, v4, v5, v6, v7, v8);
      decoratedSquare3.addPrimitive(ls1, ls2, ls3, ls4, ls5, ls6, ls7, ls8);

      final Model decoratedSquare4 = new Model("Decorated Square 4");
      decoratedSquare4.addVertex(v1, v2, v3, v4, v5, v6, v7, v8);
      decoratedSquare4.addPrimitive(ls1, ls2, ls3, ls4, ls5, ls6, ls7, ls8);

      final Model decoratedSquare5 = new Model("Decorated Square 5");
      decoratedSquare5.addVertex(v1, v2, v3, v4, v5, v6, v7, v8);
      decoratedSquare5.addPrimitive(ls1, ls2, ls3, ls4, ls5, ls6, ls7, ls8);

      final Scene scene = new Scene();

      scene.addPosition(new Position(decoratedSquare1, "p1",
                                     new Vector(0, 0, -3))); // backwards

      scene.addPosition(new Position(decoratedSquare2, "p2",
                                     new Vector(1.5, 1.5, -5.5))); // right, up, back

      scene.addPosition(new Position(decoratedSquare3, "p3",
                                     new Vector(-1.5, 1.5, -5.5))); // left, up, back

      scene.addPosition(new Position(decoratedSquare4, "p4",
                                     new Vector(-1.5, -1.5, -5.5))); // left, down, back

      scene.addPosition(new Position(decoratedSquare5, "p5",
                                     new Vector(1.5, -1.5, -5.5))); // right, down, back

      final FrameBuffer fb = new FrameBuffer(800, 800);

      Pipeline.render(scene, fb);  // render one time
      fb.dumpFB2File("Example4a.ppm");

      // Use GraphViz to draw pictures of the Scene data structure.
      DrawSceneGraph.drawVertexList = true;
      DrawSceneGraph.draw(scene, "Example4a_SG");
   }
}
