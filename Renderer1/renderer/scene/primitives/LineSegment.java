/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

package renderer.scene.primitives;

import java.util.List;

/**
   A {@code LineSegment} object has two integers that
   represent the endpoints of the line segment. The
   integers are indices into the {@link renderer.scene.Vertex}
   {@link java.util.List} of a {@link renderer.scene.Model} object.
*/
public class LineSegment extends Primitive
{
   /**
      Construct a {@code LineSegment} object using two integer indices
      for the two vertices.
      <p>
      NOTE: This constructor does not put any {@link renderer.scene.Vertex}
      objects into this {@link Primitive}'s {@link renderer.scene.Model}
      object. This constructor assumes that the given indices are valid
      (or will be valid by the time this {@link Primitive} gets rendered).

      @param i0  index of 1st endpoint {@link renderer.scene.Vertex} of the new {@code LineSegment}
      @param i1  index of 2nd endpoint {@link renderer.scene.Vertex} of the new {@code LineSegment}
   */
   public LineSegment(final int i0, final int i1)
   {
      super();

      vIndexList.add(i0);
      vIndexList.add(i1);
   }


   /**
      Construct a {@code LineSegment} object using the given
      {@link List} of integer indices.
      <p>
      NOTE: This constructor does not put any {@link renderer.scene.Vertex}
      objects into this {@link Primitive}'s {@link renderer.scene.Model}
      object. This constructor assumes that the given indices are valid
      (or will be valid by the time this {@link Primitive} gets rendered).

      @param vIndexList  {@link List} of integer indices into a {@link renderer.scene.Vertex} list
      @throws NullPointerException if {@code vIndexList} is {@code null}
      @throws IllegalArgumentException if the length of {@code vIndexList} is not 2
   */
   public LineSegment(final List<Integer> vIndexList)
   {
      super(vIndexList);

      if ( 2 != vIndexList.size() )
         throw new IllegalArgumentException("the index list must have length 2");
   }


   /**
      For debugging.

      @return {@link String} representation of this {@code LineSegment} object
   */
   @Override
   public String toString()
   {
      return "LineSegment: [" + vIndexList.get(0) + ", " + vIndexList.get(1) + "]";
   }
}
