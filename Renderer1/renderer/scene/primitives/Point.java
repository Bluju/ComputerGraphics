/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

package renderer.scene.primitives;

import java.util.List;

/**
   A {@code Point} object has an integer that represent the location of
   a single {@link renderer.scene.Vertex}. The integer is an index into
   the {@link renderer.scene.Vertex} list of a {@link renderer.scene.Model}
   object.
*/
public class Point extends Primitive
{
   public int radius = 0;

   /**
      Construct a {@code Point} object using an integer index for
      the {@link renderer.scene.Vertex} list.
      <p>
      NOTE: This constructor does not put a {@link renderer.scene.Vertex}
      object into this {@link Primitive}'s {@link renderer.scene.Model} object.
      This constructor assumes that the given index is valid (or will be valid
      by the time this {@link Primitive} gets rendered).

      @param i  index for the {@link renderer.scene.Vertex} of the new {@code Point}
   */
   public Point(final int i)
   {
      super();

      vIndexList.add(i);
   }


   /**
      Construct a {@code Point} object using the given
      {@link List} of integer indices.
      <p>
      NOTE: This constructor does not put any {@link renderer.scene.Vertex}
      objects into this {@link Primitive}'s {@link renderer.scene.Model}
      object. This constructor assumes that the given indices are valid
      (or will be valid by the time this {@link Primitive} gets rendered).

      @param vIndexList  {@link List} of integer indices into a {@link renderer.scene.Vertex} list
      @throws NullPointerException if {@code vIndexList} is {@code null}
      @throws IllegalArgumentException if the length of {@code vIndexList} is not 1
   */
   public Point(final List<Integer> vIndexList)
   {
      super(vIndexList);

      if ( 1 != vIndexList.size() )
         throw new IllegalArgumentException("the index list must have length 1");
   }


   /**
      For debugging.

      @return {@link String} representation of this {@code Point} object
   */
   @Override
   public String toString()
   {
      return "Point: [" + vIndexList.get(0) + "], radius = " + radius;
   }
}
