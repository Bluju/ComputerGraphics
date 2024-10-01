/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.util.DrawSceneGraph;
import renderer.framebuffer.*;
import renderer.pipeline.*;

/**
   In this example we move the subclass of Model into
   its own file. That makes the code in the subclass
   more reusable (since a private class in a file
   cannot be used outside of that file).

   We instantiate one instance of the model, and share
   it with each position. See the scene graph.
*/
public class Example6
{
   public static void main(String[] args)
   {
      final Model decoratedSquare = new DecoratedSquare("Decorated Square");

      final Scene scene = new Scene();

      scene.addPosition(new Position(decoratedSquare, "p1",
                                     new Vector(0, 0, -3))); // backwards

      scene.addPosition(new Position(decoratedSquare, "p2",
                                     new Vector(1.5, 1.5, -5.5))); // right, up, back

      scene.addPosition(new Position(decoratedSquare, "p3",
                                     new Vector(-1.5, 1.5, -5.5))); // left, up, back

      scene.addPosition(new Position(decoratedSquare, "p4",
                                     new Vector(-1.5, -1.5, -5.5))); // left, down, back

      scene.addPosition(new Position(decoratedSquare, "p5",
                                     new Vector(1.5, -1.5, -5.5))); // right, down, back

      final FrameBuffer fb = new FrameBuffer(800, 800);

      Pipeline.render(scene, fb);  // render one time
      fb.dumpFB2File("Example6.ppm");

      // Use GraphViz to draw pictures of the Scene data structure.
      DrawSceneGraph.drawVertexList = true;
      DrawSceneGraph.draw(scene, "Example6_SG");
   }
}
