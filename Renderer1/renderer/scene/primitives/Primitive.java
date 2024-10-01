/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

package renderer.scene.primitives;

import java.util.List;
import java.util.ArrayList;

/**
   A {@code Primitive} is something that we can build
   geometric shapes out of (a "graphics primitive").
<p>
   See <a href="https://en.wikipedia.org/wiki/Geometric_primitive" target="_top">
                https://en.wikipedia.org/wiki/Geometric_primitive</a>
<p>
   We have two geometric primitives,
   <ul>
   <li>{@link LineSegment},
   <li>{@link Point}.
   </ul>
<p>
   Each {@code Primitive} holds a list of integer indices into
   its {@link renderer.scene.Model}'s {@link List} of
   {@link renderer.scene.Vertex} objects. These are the vertices
   that determine the primitive's geometry.
*/
/*
   NOTE: The Primitive class could be an inner class of
         the Model class. Then each Primitive object would
         automatically have access to the actual Vertex and
         Color lists that the Primitive is indexing into.
*/
public abstract class Primitive
{
   // A Primitive object is made up of indices to vertices in a Model.
   public final List<Integer> vIndexList; // indices for this primitive's vertices

   /**
      Construct an empty {@code Primitive}.
   */
   protected Primitive()
   {
      this.vIndexList = new ArrayList<>();
   }


   /**
      Construct a {@code Primitive} object using the given
      {@link List} of integer indices.
      <p>
      NOTE: This constructor does not put any {@link renderer.scene.Vertex}
      objects into this {@link Primitive}'s {@link renderer.scene.Model}
      object. This constructor assumes that the given indices are valid
      (or will be valid by the time this {@link Primitive} gets rendered).

      @param vIndexList  {@link List} of integer indices into a {@link renderer.scene.Vertex} list
      @throws NullPointerException if {@code vIndexList} is {@code null}
   */
   protected Primitive(final List<Integer> vIndexList)
   {
      if (null == vIndexList)
         throw new NullPointerException("vIndexList must not be null");

      this.vIndexList = vIndexList;
   }


   /**
      Construct a {@code Primitive} with the given array of indices for the
      {@link renderer.scene.Vertex} index list.
      <p>
      NOTE: This constructor does not put any {@link renderer.scene.Vertex}
      objects into this {@link Primitive}'s {@link renderer.scene.Model}
      object. This constructor assumes that the given indices are valid
      (or will be valid by the time this {@link Primitive} gets rendered).

      @param indices  array of {@link renderer.scene.Vertex} indices to place in this {@code Primitive}
   */
   protected Primitive(final int... indices)
   {
      this();
      addIndex(indices);
   }


   /**
      Add the given array of indices to the {@link renderer.scene.Vertex}
      index list.
      <p>
      NOTE: This constructor does not put any {@link renderer.scene.Vertex}
      objects into this {@link Primitive}'s {@link renderer.scene.Model}
      object. This constructor assumes that the given indices are valid
      (or will be valid by the time this {@link Primitive} gets rendered).

      @param indices  array of {@link renderer.scene.Vertex} indices to add to this {@code Primitive}
   */
   public void addIndex(final int... indices)
   {
      for (final int i : indices)
      {
         vIndexList.add(i);
      }
   }


   /**
      For debugging.

      @return {@link String} representation of this {@code Primitive} object
   */
   @Override
   public abstract String toString();
   // Because this is an abstract method in the super class, the compiler forces you to create a concrete method in sub classes
}
