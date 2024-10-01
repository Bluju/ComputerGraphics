/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.primitives.*;

/**
   Used in Example5.java.
*/
public class DecoratedSquare extends Model
{
   public DecoratedSquare(final String name)
   {
      super(name); // create "this" object

      addVertex(new Vertex(-1, -1, 0), // add to "this" object
                new Vertex(-1,  0, 0),
                new Vertex(-1,  1, 0),
                new Vertex( 0,  1, 0),
                new Vertex( 1,  1, 0),
                new Vertex( 1,  0, 0),
                new Vertex( 1, -1, 0),
                new Vertex( 0, -1, 0));

      addPrimitive(new LineSegment(0, 2),
                   new LineSegment(2, 4),
                   new LineSegment(4, 6),
                   new LineSegment(6, 0),
                   new LineSegment(1, 3),
                   new LineSegment(3, 5),
                   new LineSegment(5, 7),
                   new LineSegment(7, 1));
   }
}
