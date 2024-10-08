/*
 * Renderer 1. The MIT License.
 * Copyright (c) 2022 rlkraft@pnw.edu
 * See LICENSE for details.
*/

import renderer.scene.*;
import renderer.scene.util.DrawSceneGraph;
import renderer.models_L.TriangularPyramid;
import renderer.models_L.Octahedron;
import renderer.framebuffer.*;
import renderer.pipeline.*;

import java.awt.Color;

/**

*/
public class ThreePositionsTwoModels_R1
{
   public static void main(String[] args)
   {
      final Scene scene = new Scene("ThreePositionsTwoModels_R1");
      final Model model_0 = new TriangularPyramid();
      final Model model_1 = new Octahedron();
      scene.addPosition(
         new Position(model_0, "p0", new Vector(-1.0,  0.0, -2.0)),
         new Position(model_1, "p1", new Vector(+1.0,  0.0, -2.0)),
         new Position(model_0, "p2", new Vector( 0.0, -1.0, -2.0)));

      // Use GraphViz to draw pictures of the Scene data structure.
      DrawSceneGraph.drawVector = false;
      DrawSceneGraph.drawVectorDetails = false;
      DrawSceneGraph.draw(scene, "ThreePositionsTwoModels_R1_SG");
      DrawSceneGraph.drawVectorDetails = true;
      DrawSceneGraph.draw(scene, "ThreePositionsTwoModels_R1_SG_with_Vectors");
      DrawSceneGraph.drawVertexList = true;
      DrawSceneGraph.draw(scene, "ThreePositionsTwoModels_R1_SG_with_Vectors_Vertices");


      final int width  = 1000;
      final int height = 1000;
      final FrameBuffer fb = new FrameBuffer(width, height, Color.darkGray);

      Rasterize.doClipping = true;

      scene.debug = true;
      Pipeline.render(scene, fb);
      fb.dumpFB2File("ThreePositionsTwoModels_R1.ppm");
   }
}
