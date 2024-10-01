/*
 * Renderer Models. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

package renderer.models_L;

import renderer.scene.*;
import renderer.scene.primitives.*;

/**
   Create an x and y axis in the xy-plane, along with "tick marks".
*/
public class Axes2D extends Model
{
   /**
      Create an x and y axis from -1 to +1 on each axis.
   */
   public Axes2D( )
   {
      this(-1, 1, -1, 1, 5, 5, 0.0);
   }


   /**
      Create an x-axis from {@code xMin} to {@code xMax}
      and a y-axis from {@code yMin} to {@code yMax}.

      @param xMin    left end point for the x-axis
      @param xMax    right end point for the x-axis
      @param yMin    bottom end point for the y-axis
      @param yMax    top end point for the y-axis
      @param xMarks  number of evenly spaced tick marks on the x-axis
      @param yMarks  number of evenly spaced tick marks on the y-axis
   */
   public Axes2D(final double xMin, final double xMax,
                 final double yMin, final double yMax,
                 final int xMarks, final int yMarks)
   {
      this(xMin, xMax, yMin, yMax, xMarks, yMarks, 0.0);
   }


   /**
      Create an x-axis from {@code xMin} to {@code xMax}
      and a y-axis from {@code yMin} to {@code yMax}.
   <p>
      The {@code z} parameter is so that we can put the axis just above
      or just below the xy-plane (say {@code z=0.01} or {@code z=-0.01}).
      This way, the axes can be just in front of or just behind whatever
      is being drawn in the xy-plane.

      @param xMin    left end point for the x-axis
      @param xMax    right end point for the x-axis
      @param yMin    bottom end point for the y-axis
      @param yMax    top end point for the y-axis
      @param xMarks  number of evenly spaced tick marks on the x-axis
      @param yMarks  number of evenly spaced tick marks on the y-axis
      @param z       offset of the axes away from the xy-plane
   */
   public Axes2D(final double xMin, final double xMax,
                 final double yMin, final double yMax,
                 final int xMarks, final int yMarks,
                 final double z)
   {
      super(String.format("Axes 2D(%.2f,%.2f,%.2f,%.2f)",
                                   xMin,xMax,yMin,yMax));

      // x-axis
      addVertex(new Vertex(xMin, 0, z),
                new Vertex(xMax, 0, z));
      addPrimitive(new LineSegment(0, 1));

      // y-axis
      addVertex(new Vertex(0, yMin, z),
                new Vertex(0, yMax, z));
      addPrimitive(new LineSegment(2, 3));

      int index = 4;

      // Put evenly spaced tick marks on the x-axis.
      double xDelta = (xMax - xMin)/xMarks;
      double yDelta = (yMax - yMin)/50;
      for (double x = xMin; x <= xMax; x += xDelta)
      {
         addVertex(new Vertex(x,  yDelta/2, z),
                   new Vertex(x, -yDelta/2, z));
         addPrimitive(new LineSegment(index+0, index+1));
         index += 2;
      }

      // Put evenly spaced tick marks on the y-axis.
      yDelta = (yMax - yMin)/yMarks;
      xDelta = (xMax - xMin)/50;
      for (double y = yMin; y <= yMax; y += yDelta)
      {
         addVertex(new Vertex( xDelta/2, y, z),
                   new Vertex(-xDelta/2, y, z));
         addPrimitive(new LineSegment(index+0, index+1));
         index += 2;
      }
   }
}//Axes2D
