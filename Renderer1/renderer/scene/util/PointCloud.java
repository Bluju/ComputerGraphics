/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

package renderer.scene.util;

import renderer.scene.*;
import renderer.scene.primitives.*;

import java.util.ArrayList;

/**
   Convert a {@link Model} object into a point cloud {@link Model}.
<p>
   See <a href="https://en.wikipedia.org/wiki/Point_cloud" target="_top">
                https://en.wikipedia.org/wiki/Point_cloud</a>
*/
public class PointCloud
{
   /**
      A static factory method that converts a given {@link Model}
      into a {@link Model} made up of only {@link Point} primitives.

      @param model  {@link Model} to convert into a point cloud
      @return a {@link Model} that is a point cloud version of the input {@link Model}
      @throws NullPointerException if {@code model} is {@code null}
   */
   public static Model make(final Model model)
   {
      return make(model, 0); // set the point size to 0
   }


   /**
      A static factory method that converts a given {@link Model}
      into a {@link Model} made up of only {@link Point} primitives.

      @param model      {@link Model} to convert into a point cloud
      @param pointSize  integer diameter of the rasterized points
      @return a {@link Model} that is a point cloud version of the input {@link Model}
      @throws NullPointerException if {@code model} is {@code null}
      @throws IllegalArgumentException if {@code pointSize} is less than 0
   */
   public static Model make(final Model model, final int pointSize)
   {
      if (null == model)
         throw new NullPointerException("model must not be null");
      if (pointSize < 0)
         throw new IllegalArgumentException("pointSize must be greater than or equal to 0");

      final Model pointCloud = new Model(new ArrayList<Vertex>(model.vertexList),
                                         new ArrayList<>(), // empty primitiveList
                                         "PointCloud: " + model.name,
                                         model.visible);

      // Find the vertices that are being used by a primitive.
      final boolean[] indices = new boolean[model.vertexList.size()];

      for (final Primitive p : model.primitiveList)
      {
         for (final int i : p.vIndexList)
         {
            indices[i] = true;
         }
      }

      // Create a Point for each vertex that is used by some primitive.
      for (int i = 0; i < indices.length; ++i)
      {
         if ( indices[i] )
         {
            pointCloud.addPrimitive( new Point(i) );
         }
      }

      // Set the radius for each new Point primitive.
      for (final Primitive p : pointCloud.primitiveList)
      {
         ((Point)p).radius = pointSize;;
      }

      return pointCloud;
   }



   // Private default constructor to enforce noninstantiable class.
   // See Item 4 in "Effective Java", 3rd Ed, Joshua Bloch.
   private PointCloud() {
      throw new AssertionError();
   }
}
