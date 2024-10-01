/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.util.Assets;
import renderer.models_L.GRSModel;
import renderer.pipeline.*;
import renderer.framebuffer.*;

import java.io.File;
import java.awt.Color;

/**
   This program creates an animation by repeatedly
   saving the framebuffer's contents to a file.
   <p>
   You can see the animation by quickly sequencing
   through the frame images.
*/
public class OfflineGRS
{
   private static final String assets = Assets.getPath();

   public static void main(String[] args)
   {
      // Create the Scene object that we shall render.
      final Scene scene = new Scene("OfflineGRS");

      // Create a Model object to hold the geometry.
      final Model model = new GRSModel(new File(assets + "grs/knight.grs"));

      // Add the Model to the Scene, pushed
      // away from where the camera is.
      scene.addPosition(new Position(model, "p0",
                                     new Vector(0.0,  0.0, -1.0)));

      // Log information to standard output.
      System.out.println( scene.toString() );

      // Create a FrameBuffer to render our Scene into.
      final int width  = 1024;
      final int height = 1024;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      // Render our Scene into the framebuffer.
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_OfflineGRS_Frame%03d.ppm", 0));

      Rasterize.doClipping = false;  // turn off clipping

      for (int j = 1; j <= 60; ++j)
      {
         // Update the scene by translating in camera
         // space the position that holds the model.
         final Vector t = scene.getPosition(0).getTranslation();
         scene.getPosition(0).translate(t.x + 0.1,    // move right
                                        t.y - 0.01,   // move up
                                        t.z - 0.05);  // move back

         // Render again.
         fb.clearFB(); // What happens if we don't clear the framebuffer?
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_OfflineGRS_Frame%03d.ppm", j));
      }
   }
}
