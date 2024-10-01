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
   Compile and run this program. Look at its output, both
   in the console window and in the PPM files that it creates.
   <p>
   The output in the console window shows you the debugging
   information from the renderer. It shows the results from
   each stage of the rendering pipeline. This program draws
   a single line, so the scene's model contains just two
   vertices and one line segment.
   <p>
   Look at the output in the console window to see how each
   vertex gets projected into the view plane and then how
   the line segment gets rasterized into the framebuffer.
   <p>
   This program's framebuffer is small on purpose so that
   there isn't too much console output from the rasterizer.
   The framebuffer is only 20 pixels by 20 pixels, so the
   single line segment from the scene ends up covering
   only a few dozen pixels.
   <p>
   Use IrfanView to compare the pixel coordinates listed
   in the console window with the actual pixels in the PPM
   file.
*/
public class DiagonalLineInFramebuffer
{
   public static void main(String[] args)
   {
      final Color backgroundColor = Color.darkGray;

      final Scene scene = new Scene("DiagonalLineInFramebuffer");

      final Model model = new Model("Diagonal Line");
      scene.addPosition(new Position(model, "p0"));

      model.addVertex(
         new Vertex(-1,  1, -1),  // upper left-hand corner of view rectangle
         new Vertex( 1, -1, -1)); // lower right-hand corner of view rectangle

      model.addPrimitive(new LineSegment(0, 1));

      FrameBuffer fb = new FrameBuffer(15, 15, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      System.out.println( fb );
      fb.dumpFB2File("DiagonalLineInFramebuffer_v0.ppm");

      fb = new FrameBuffer(20, 20, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("DiagonalLineInFramebuffer_v1.ppm");

      fb = new FrameBuffer(25, 20, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("DiagonalLineInFramebuffer_v2.ppm");

      fb = new FrameBuffer(30, 20, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("DiagonalLineInFramebuffer_v3.ppm");

      fb = new FrameBuffer(35, 20, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("DiagonalLineInFramebuffer_v4.ppm");

      fb = new FrameBuffer(40, 20, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("DiagonalLineInFramebuffer_v5.ppm");

      fb = new FrameBuffer(50, 20, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("DiagonalLineInFramebuffer_v6.ppm");

      fb = new FrameBuffer(20, 50, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("DiagonalLineInFramebuffer_v7.ppm");
   }
}
