/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.util.Assets;
import renderer.models_L.*;
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
public class OfflineMovie
{
   private static final String assets = Assets.getPath();

   public static void main(String[] args)
   {
      // Create the Scene object that we shall render.
      final Scene scene = new Scene("OfflineMovie");

      // Create several Model objects.
      final Model m1 = new ObjSimpleModel(new File(assets + "apple.obj"));
      final Model m2 = new ObjSimpleModel(new File(assets + "cow.obj"));
      final Model m3 = new ObjSimpleModel(new File(assets + "cessna.obj"));
      final Model m4 = new ObjSimpleModel(new File(assets + "galleon.obj"));
      final Model m5 = new ObjSimpleModel(new File(assets + "teapot.obj"));
      final Model m6 = new ObjSimpleModel(new File(
                          assets + "small_rhombicosidodecahedron.obj"));
      final Model m7 = new ObjSimpleModel(new File(
                          assets + "great_rhombicosidodecahedron.obj"));

      // Add some models to the Scene, pushed
      // away from where the camera is.
      scene.addPosition(new Position(m1, "p0",  // apple
                            new Vector(0.0,  0.0, -3.0)),
                        new Position(m3, "p1",  // cessna
                            new Vector(0.0,  0.0, -3.0)),
                        new Position(m4, "p2",  // galleon
                            new Vector(0.0,  0.0, -3.0)),
                        new Position(m2, "p3",  // cow
                            new Vector(0.0,  0.0, -3.0)));

      // Create a framebuffer to render our Scene into.
      final int width  = 1024;
      final int height = 1024;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      Rasterize.doClipping = false;  // turn off clipping

      // Render our scene into the framebuffer.
      Pipeline.render(scene, fb);
      // Save the resulting image in a file.
      fb.dumpFB2File( String.format("PPM_OfflineModels_Frame%03d.ppm", 0) );

      for (int j = 1; j <= 100; ++j)
      {
         // Update the scene.
         final Vector t0 = scene.getPosition(0).getTranslation();
         scene.getPosition(0).translate(t0.x + 0.3,   // move right
                                        t0.y + 0.1,   // move up
                                        t0.z - 0.1);  // move back

         final Vector t1 = scene.getPosition(1).getTranslation();
         scene.getPosition(1).translate(t1.x - 0.3,   // move left
                                        t1.y - 0.1,   // move down
                                        t1.z - 0.1);  // move back

         final Vector t2 = scene.getPosition(2).getTranslation();
         scene.getPosition(2).translate(t2.x + 0.01,  // move right
                                        t2.y,
                                        t2.z);

         // Render again.
         fb.clearFB(); // What happens if we don't clear the framebuffer?
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_OfflineModels_Frame%03d.ppm", j));
      }
   }
}
