/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.framebuffer.*;
import renderer.pipeline.*;

import java.awt.Color;

/**
   Draw images of a pixel-plane and
   its associated framebuffer.
*/
public class PixelPlane
{
   // A pixel-plane that is w pixels wide and h pixels high.
   private final static int w = 30;
   private final static int h = 15; // assume that h <= w
   // The size, in real pixels, of a pixel image.
   private final static int size = 50;

   public static void main(String[] args)
   {
      final Scene scene = new Scene();
      final Model framebuffer = new Model("framebuffer");
      final Model pixel_plane = new Model("pixel-plane");
      scene.addPosition(new Position(framebuffer, "p0"));
      scene.addPosition(new Position(pixel_plane, "p1"));

      final double xStep = 2.0 / w,
                   yStep = xStep;

      // An array of vertices to be used to create the line segments.
      final Vertex[][] v0 = new Vertex[h+1][w+1];

      // An array of vertices to be used to define the point pixels.
      final Vertex[][] v1 = new Vertex[h][w];

      // Create all the vertices.
      for (int i = 0; i <= h; ++i)
      {
         for (int j = 0; j <= w; ++j)
         {
            // from top-to-bottom and left-to-right
            v0[i][j] = new Vertex(-1 + j * xStep,
                                   1 - i * yStep,
                                  -1);
         }
      }

      // point pixels
      for (int i = 0; i < h; ++i)
      {
         for (int j = 0; j < w; ++j)
         {
            // from top-to-bottom and left-to-right
            v1[i][j] = new Vertex(-1 + (xStep/2.0) + j * xStep,
                                   1 - (yStep/2.0) - i * yStep,
                                  -1);
         }
      }


      // Add all of the vertices to the models.
      for (int i = 0; i <= h; ++i)
      {
         for (int j = 0; j <= w; ++j)
         {
            framebuffer.addVertex( v0[i][j] );
         }
      }

      for (int i = 0; i < h; ++i)
      {
         for (int j = 0; j < w; ++j)
         {
            pixel_plane.addVertex( v1[i][j] );
         }
      }

      // Create the line segments parallel to the x-axis.
      for (int i = 0; i <= h; ++i)
      {
         for (int j = 0; j < w; ++j)
         {
            framebuffer.addPrimitive(
                           new LineSegment(i*(w+1) + j,      // v0[i][j  ]
                                           i*(w+1) + j+1 )); // v0[i][j+1]
         }
      }

      // Create the line segments parallel to the y-axis.
      for (int j = 0; j <= w; ++j)
      {
         for (int i = 0; i < h; ++i)
         {
            framebuffer.addPrimitive(
                           new LineSegment(    i*(w+1) + j,   // v0[i  ][j]
                                           (i+1)*(w+1) + j)); // v0[i+1][j]
         }
      }

      // Create the point pixels.
      for (int i = 0; i < h; ++i)
      {
         for (int j = 0; j < w; ++j)
         {
            final Point p = new Point(i*w + j);
            p.radius = 0;
            pixel_plane.addPrimitive( p );
         }
      }

      final int width  = w * size;
      final int height = w * size;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.black);

      framebuffer.visible = false;
      pixel_plane.visible = true;

      Pipeline.render(scene, fb);
      fb.dumpFB2File("PixelPlane.ppm");
   }
}
