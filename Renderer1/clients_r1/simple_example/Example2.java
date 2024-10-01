/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.primitives.*;
import renderer.framebuffer.*;
import renderer.pipeline.*;

/**
   Draw a scene with five copies of the "square
   within a square" model from the previous example.

   This example shows a "hack" that draws five copies
   of the decorated square by using "multiple exposures"
   into the framebuffer of a single Model object that
   gets moved around in between the "exposures".

   This works in an "offline" rendering, but it will not
   work for an interactive renderer (like a game) that
   needs all five models on the screen at the same time.
*/
public class Example2
{
   public static void main(String[] args)
   {
      final Model decoratedSquare = new Model("Decorated Square");

      decoratedSquare.addVertex(new Vertex(-1, -1, 0),
                                new Vertex(-1,  0, 0),
                                new Vertex(-1,  1, 0),
                                new Vertex( 0,  1, 0),
                                new Vertex( 1,  1, 0),
                                new Vertex( 1,  0, 0),
                                new Vertex( 1, -1, 0),
                                new Vertex( 0, -1, 0));

      decoratedSquare.addPrimitive(new LineSegment(0, 2),
                                   new LineSegment(2, 4),
                                   new LineSegment(4, 6),
                                   new LineSegment(6, 0),
                                   new LineSegment(1, 3),
                                   new LineSegment(3, 5),
                                   new LineSegment(5, 7),
                                   new LineSegment(7, 1));

      final FrameBuffer fb = new FrameBuffer(800, 800);

      final Scene scene = new Scene();

      scene.addPosition(new Position(decoratedSquare));

      // Move the model.
      scene.getPosition(0).translate(0, 0, -3); // backwards

      // Render once.
      Pipeline.render(scene, fb);

      // Move the model.
      //This translate method is a "translate to" method. // the parameters are not shift amounts, they are the new x,y,z location
      scene.getPosition(0).translate(1.5, 1.5, -5.5); // right, up, back

      // Render the scene a second time into the same framebuffer.
      Pipeline.render(scene, fb);

      // Move the model again.
      scene.getPosition(0).translate(-1.5, 1.5, -5.5); // left, up, back

      // Render a third time into the same framebuffer.
      Pipeline.render(scene, fb);

      // Move the model again.
      scene.getPosition(0).translate(-1.5, -1.5, -5.5); // left, down, back

      // Render a fourth time into the framebuffer.
      Pipeline.render(scene, fb);

      // Move the model again.
      scene.getPosition(0).translate(1.5, -1.5, -5.5); // right, down, back

      // Render a fifth time into the same framebuffer.
      Pipeline.render(scene, fb);

      fb.dumpFB2File("Example2.ppm");
   }
}
