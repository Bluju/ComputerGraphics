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
   We can abstract the "decorated square" into a
   subclass of Model. The DecoratedSquare subclass
   is at the end of this file as a private class.

   http://people.scs.carleton.ca/~lanthier/teaching/COMP1406/Notes/COMP1406_Ch4_ClassHierarchiesAndInheritance.pdf
   http://people.scs.carleton.ca/~lanthier/teaching/COMP1406/notes.html
*/
public class Example5
{
   public static void main(String[] args)
   {
      final Model decoratedSquare1 = new DecoratedSquare_E5("Decorated Square 1");
      final Model decoratedSquare2 = new DecoratedSquare_E5("Decorated Square 2");
      final Model decoratedSquare3 = new DecoratedSquare_E5("Decorated Square 3");
      final Model decoratedSquare4 = new DecoratedSquare_E5("Decorated Square 4");
      final Model decoratedSquare5 = new DecoratedSquare_E5("Decorated Square 5");

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
      fb.dumpFB2File("Example5.ppm");

      // Use GraphViz to draw pictures of the Scene data structure.
      DrawSceneGraph.drawVertexList = true;
      DrawSceneGraph.draw(scene, "Example5_SG");
   }
}



class DecoratedSquare_E5 extends Model
{
   public DecoratedSquare_E5(final String name)
   {
      super(name); // create "this" object

      this.addVertex(new Vertex(-1, -1, 0), // add to "this" object
                     new Vertex(-1,  0, 0),
                     new Vertex(-1,  1, 0),
                     new Vertex( 0,  1, 0),
                     new Vertex( 1,  1, 0),
                     new Vertex( 1,  0, 0),
                     new Vertex( 1, -1, 0),
                     new Vertex( 0, -1, 0));

      this.addPrimitive(new LineSegment(0, 2),
                        new LineSegment(2, 4),
                        new LineSegment(4, 6),
                        new LineSegment(6, 0),
                        new LineSegment(1, 3),
                        new LineSegment(3, 5),
                        new LineSegment(5, 7),
                        new LineSegment(7, 1));
   }
}
