/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.models_L.Circle;
import renderer.framebuffer.*;
import renderer.pipeline.*;

import java.awt.Color;

/**
   Compile and run this program. Look at its output, both in
   the console window and in the PPM files that it creates.
   <p>
   The output in the console window shows you the debugging
   information from the renderer. It shows the results from
   each stage of the rendering pipeline. This program draws
   a circle in framebuffers of various sizes.
   <p>
   Use IrfanView to compare the pixel coordinates listed
   in the console window with the actual pixels in the PPM
   file.
*/
public class CircleInFramebuffer
{
   public static void main(String[] args)
   {
      final Color backgroundColor = Color.darkGray;

      final Scene scene = new Scene();

      // Place a circle in front of the camera.
      final Model model = new Circle(1.0, 16);
      scene.addPosition(new Position(model, "p0",
                                     new Vector(0.0,  0.0, -1.0)));

      FrameBuffer fb = new FrameBuffer(100, 100, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("CircleInFramebuffer_v1.ppm");

      fb = new FrameBuffer(120, 100, backgroundColor);
      scene.debug = true;
      Rasterize.debug = false;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("CircleInFramebuffer_v2.ppm");

      fb = new FrameBuffer(140, 100, backgroundColor);
      scene.debug = true;
      Rasterize.debug = false;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("CircleInFramebuffer_v3.ppm");

      fb = new FrameBuffer(160, 100, backgroundColor);
      scene.debug = true;
      Rasterize.debug = false;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("CircleInFramebuffer_v4.ppm");

      fb = new FrameBuffer(180, 100, backgroundColor);
      scene.debug = true;
      Rasterize.debug = false;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("CircleInFramebuffer_v5.ppm");

      fb = new FrameBuffer(200, 100, backgroundColor);
      scene.debug = true;
      Rasterize.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("CircleInFramebuffer_v6.ppm");
   }
}
