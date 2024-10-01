/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/
// classpath
// .;../..
// real programs often use build systems because then developers can use any IDE without compiler compatibility issues
import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.scene.util.DrawSceneGraph;
import renderer.framebuffer.*;
import renderer.pipeline.*;

/**
   Draw a simple scene with a single model
   that is a square within a square.
*/
public class Example1
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

      scene.addPosition(new Position(decoratedSquare,
                                     new Vector(0, 0, -3)));

      final FrameBuffer fb = new FrameBuffer(800, 800);

      Pipeline.render(scene, fb);
      fb.dumpFB2File("Example1.ppm");

      // Use GraphViz to draw pictures of the Scene data structure.
      DrawSceneGraph.drawVertexList = true;
      DrawSceneGraph.draw(scene, "Example1_SG");
   }
}
