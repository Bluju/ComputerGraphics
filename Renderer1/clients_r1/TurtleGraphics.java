/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.models_L.turtlegraphics.*;
import renderer.pipeline.*;
import renderer.framebuffer.*;

import java.awt.Color;

/**
   This program draws several examples of turtle graphics.
*/
public class TurtleGraphics
{
   public static void main(String[] args)
   {
      // Create the Scene object that we shall render.
      final Scene scene = new Scene("TurtleGraphics");
      scene.addPosition(new Position(new Model(), "p0"));

      // Create a FrameBuffer to render our Scene into.
      final int width  = 1024;
      final int height = 1024;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);


      int j = 0; // used to count frames

      int N = 9;
      for (int i = 0; i <= 5; ++i)
      {
         final Model m = new Model("polygasket_"+N+"_"+i);
         new PolygasketTurtle(m, N, i, -1.0, -1.0, -2.0);
         scene.getPosition(0).setModel(m);

         // Render again.
         fb.clearFB();
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));
      }

      N = 7;
      for (int i = 0; i <= 5; ++i)
      {
         final Model m = new Model("polygasket_"+N+"_"+i);
         new PolygasketTurtle(m, N, i, -1.0, -1.0, -2.0);
         scene.getPosition(0).setModel(m);

         // Render again.
         fb.clearFB();
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));
      }

      for (int i = 0; i <= 7; ++i)
      {
         final Model m = new Model("pentagasket " + i);
         new PentagasketTurtle(m, i, -0.6, -0.6, -1.0);
         scene.getPosition(0).setModel(m);

         // Render again.
         fb.clearFB();
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));
      }

      for (int i = 0; i <= 8; ++i)
      {
         final Model m = new Model("sierpinski " + i);
         new SierpinskiTurtle(m, i, 2.0, -0.9, -1.0, -1.0);
         scene.getPosition(0).setModel(m);

         // Render again.
         fb.clearFB();
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));
      }

      for (int i = 0; i <= 9; ++i)
      {
         final Model m = new Model("sierpinski curve " + i);
         new SierpinskiCurveTurtle(m, i, 2.0, -0.9, -1.0, -1.0);
         scene.getPosition(0).setModel(m);

         // Render again.
         fb.clearFB();
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));
      }


      for (int i = 0; i <= 8; ++i)
      {
         final Model m = new Model("hilbert curve " + i);
         new HilbertCurveTurtle(m, i, 2.0, 0.1, -1.9, -2.0);
         scene.getPosition(0).setModel(m);

         // Render again.
         fb.clearFB();
         Pipeline.render(scene, fb);
         fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));
      }


      final Model ninjaModel = new Model("Ninja Turtle");
      new NinjaTurtle(ninjaModel, 180, 0.0, 0.0, -2.0);
      scene.getPosition(0).setModel(ninjaModel);
      // Render again.
      fb.clearFB();
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));

      final Model ninjaModel2 = new Model("Ninja Turtle");
      new NinjaTurtle(ninjaModel2, 360, 0.0, 0.0, -2.0);
      scene.getPosition(0).setModel(ninjaModel2);
      // Render again.
      fb.clearFB();
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));

      final Model spiralModel = new Model("Spiral Turtle");
      new SpiralTurtle(spiralModel, 270, -0.3, -0.5, -0.6);
      scene.getPosition(0).setModel(spiralModel);
      // Render again.
      fb.clearFB();
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));

      final Model spiralModel2 = new Model("Spiral Turtle");
      new SpiralTurtle(spiralModel2, 335, -0.3, -0.5, -0.6);
      scene.getPosition(0).setModel(spiralModel2);
      // Render again.
      fb.clearFB();
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));


      final Model turtleModel = new Model("Turtle Expeiment");
      final Turtle turtle = new Turtle(turtleModel, -2.0, 0.0, -3.0);
      // draw a pentagram
      final double length = 1.0;
      for (int i = 1; i <= 5; ++i)
      {
         turtle.forward(length);
         turtle.turn(144);
      }
      // draw a spiral
      for (int k = 1; k < 32; ++k)
      {
// https://www.clear.rice.edu/comp360/lectures/fall2008/TurtleFractalsL2New.pdf#page=3
         for (int i = 1; i <= 8; ++i)
         {
            turtle.forward(1);
            turtle.turn(60);
            turtle.resize(0.99);
            turtle.forward(1.0/4.0);
            turtle.turn(45);
            turtle.resize(0.99);
            turtle.forward(1.0/3.0);
            turtle.turn(-90);
            turtle.resize(0.99);
            turtle.forward(3.0/5.0);
            turtle.turn(30);
            turtle.resize(0.99);
         }
      }
      scene.getPosition(0).setModel(turtleModel);
      // Render again.
      fb.clearFB();
      Pipeline.render(scene, fb);
      fb.dumpFB2File(String.format("PPM_TurtleGraphics_%03d.ppm", j++));
/*
      for (int i = 1; i <= 8; ++i)
      {
         turtle.forward(1);
         turtle.turn(60);
         turtle.forward(1.0/4.0);
         turtle.turn(45);
         turtle.forward(1.0/3.0);
         turtle.turn(-90);
         turtle.forward(3.0/5.0);
         turtle.turn(30);
      }
*/
/*
      for (int j = 1; j < 32; ++j)
      {
         for (int i = 1; i <= 8; ++i)
         {
            turtle.forward(1);
            turtle.turn(60);
            turtle.forward(1.0/4.0);
            turtle.turn(45);
            turtle.forward(1.0/3.0);
            turtle.turn(-90);
            turtle.forward(3.0/5.0);
            turtle.turn(30);
         }
         turtle.resize(0.9);
      }
*/
   }
}
