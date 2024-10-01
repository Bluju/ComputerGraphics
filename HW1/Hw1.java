/*
   Course: CS 45500
   Name: Julian Sahagun
   Email: sahaguj@pnw.edu
   Assignment: 1
*/

import framebuffer.FrameBuffer;

import java.awt.Color;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

/**
   Outline of CS 45500 Assignment 1.
*/
public class Hw1
{
   //Colors 
   final static Color COFFEE = new Color(192,56,14); // Background color
   final static Color TAN = new Color(255,189,96); // Color of the checkerboard squares
   final static Color LIGHTRED = new Color(241,95,116);
   final static Color LIGHTGREEN = new Color(152,203,74);
   final static Color LIGHTBLUE = new Color(84,129,230);
   final static Color LIGHTGRAY = new Color(192,192,192); // Color of the frame in Step 10

   public static void main(String[] args)
   {
      // Use a properties file to find out
      // which PPM files to use as assets.
      final Properties properties = new Properties();
      try
      {
         properties.load(
            new FileInputStream(
               new File("assets.properties")));
      }
      catch (IOException e)
      {
         e.printStackTrace(System.err);
         System.exit(-1);
      }
      final String file_1 = properties.getProperty("file1"); // 1st ppm file
      final String file_2 = properties.getProperty("file2"); // 2nd ppm file

      // This framebuffer holds the image that will be embedded
      // into two viewports of the larger framebuffer.
      final FrameBuffer fbEmbedded = new FrameBuffer(file_1);

      /******************************************/

      // Your code goes here.
      //  1. Create a 1100-by-700 framebuffer with the appropriate background color.
      
      final FrameBuffer fb = new FrameBuffer(1100,700, COFFEE);
      
      //  2. Fill the framebuffer with the checkerboard pattern.
      int offset = 0; //alternate between 0 and 100 pixels
      int horizontalLocation = 0;
      int verticalLocation = 0;
      for(int y = 50; y < 650; y = y + 100){
         // y is the top coordinate of the individual square
         for(int x = 50; x < 1050; x = x + 200){
            // x is the left side coordinate of the individual square
            for(int i = 0; i < 100; i++){
               verticalLocation = y + i;
               for(int j = 0; j < 100; j++){
                  horizontalLocation = x + j + offset;
                  fb.setPixelFB(horizontalLocation,verticalLocation,TAN);
               } 
            }
         }
         //alternate offset value
         if(offset == 0){
            offset = 100;
         }else{
            offset = 0;
         }
      } 
      
      //  3. Put a one pixel wide border around the checkerboard.
      
      for(int y = 49; y <= 650; y += 601){
         for(int x = 49; x < 1050;x++){
            // Draw top and bottom border
            fb.setPixelFB(x,y,TAN);
         }
      }
      for(int x = 49; x <= 1050; x += 1001 ){
         for(int y = 49; y < 650; y++){
            // Draw left and right border
            fb.setPixelFB(x,y,TAN);
         }
      }
      //  4. Put four one pixel wide diagonals at the four coners of the checkerboard.
      //Top-left diagonal
      for(int i = 0; i < 50; ++i){
         fb.setPixelFB(i,i,TAN);
      }

      //Bottom-left diagonal
      for(int i = 1; i < 51; ++i){
         fb.setPixelFB(i-1,700-i,TAN);
      }

      //Top-right diagonal
      for(int i = 0; i < 50; ++i){
         fb.setPixelFB(1050+i,49-i,TAN);
      }

      //Bottom-right diagonal
      for(int i = 0; i < 50; ++i){
         fb.setPixelFB(1050+i,650+i,TAN);
      }

      //  5. Create a viewport and put in it the striped pattern.
      final FrameBuffer.Viewport stripedPattern = fb.new Viewport(650,475,270,130);
      
      for(int i = 0; i < 3; i++){
         // DrawStripe() function is at the bottom of this file
         DrawStripe(stripedPattern,i*90,0,LIGHTRED);
         DrawStripe(stripedPattern,30+i*90,0,LIGHTGREEN);
         DrawStripe(stripedPattern,60+i*90,0,LIGHTBLUE);
      }
      
      //  6. Create a viewport and put in it the striped disk pattern.
      final FrameBuffer.Viewport diskPattern = fb.new Viewport(200,400,300,300);
      DrawDisk(diskPattern); // DrawDisk() function is at the bottom of this file
      
      //  7. Create a viewport and put in it a flipped copy of the 1st ppm file.
      final FrameBuffer.Viewport vertFlipTroop = fb.new Viewport(125,175,256,256);
      //  8. Create another viewport put in it another flipped copy of the 1st ppm file.
      final FrameBuffer.Viewport horizFlipTroop = fb.new Viewport(381,175,256,256);
      // the Viewport fbEmbedded has the image being flipped 
      for(int y = 0; y < vertFlipTroop.getHeightVP(); y++){
         for(int x = 0; x < vertFlipTroop.getWidthVP(); x++){
            if(fbEmbedded.getPixelFB(x,y).getRed() > 251 && fbEmbedded.getPixelFB(x,y).getGreen() > 251 && fbEmbedded.getPixelFB(x,y).getBlue() > 251){
               continue; // skip pixels that are white or almost white
            }
            vertFlipTroop.setPixelVP(x,vertFlipTroop.getHeightVP() - 1 - y,fbEmbedded.getPixelFB(x,y));
            horizFlipTroop.setPixelVP(horizFlipTroop.getWidthVP()-1-x,y,fbEmbedded.getPixelFB(x,y));
         }
      }

      //  9. Create a viewport that covers the 6 checkerboard squares that need to be copied.
      final FrameBuffer.Viewport checkerCopyVP = fb.new Viewport(550,250,200,300);
      // 10. Create another viewport to hold a "framed" copy of the previous viewport.
      final FrameBuffer.Viewport framedCheckerCopyVP = fb.new Viewport(775,75,250,350);
      //     Give this viewport a grayish background color.
      framedCheckerCopyVP.clearVP(LIGHTGRAY);
      // 11. Create another viewport within the last, gray viewport, and initialize
      //     it to hold a copy of the viewport from step 9.
      
      final FrameBuffer.Viewport copyCheckerCopyVP = fb.new Viewport(800,100, checkerCopyVP);
      // 12. Load Dumbledore (the 2nd ppm file) into another FrameBuffer.
      final FrameBuffer dumbledoreFB = new FrameBuffer(file_2);
      
      // 13. Create a viewport to hold Dumbledore's ghost.
      final FrameBuffer.Viewport dumbledoreGhostVP = fb.new Viewport(450,150,500,500);
      // 14. Blend Dumbledore from its framebuffer into the viewport.
      for(int y = 0; y < dumbledoreGhostVP.getHeightVP();y++){
         for(int x = 0; x < dumbledoreGhostVP.getWidthVP();x++){
            if(dumbledoreFB.getPixelFB(x,y).getRed() > 250 && dumbledoreFB.getPixelFB(x,y).getGreen() > 250 && dumbledoreFB.getPixelFB(x,y).getBlue() > 250){
               continue; // Skip white or nearly white pixels
            }
            float new_red = (0.6f * dumbledoreFB.getPixelFB(x,y).getRed() + 0.4f * dumbledoreGhostVP.getPixelVP(x,y).getRed())/255.0f;
            float new_green = (0.6f * dumbledoreFB.getPixelFB(x,y).getGreen() + 0.4f * dumbledoreGhostVP.getPixelVP(x,y).getGreen())/255.0f;
            float new_blue = (0.6f * dumbledoreFB.getPixelFB(x,y).getBlue() + 0.4f * dumbledoreGhostVP.getPixelVP(x,y).getBlue())/255.0f;
            Color newColor = new Color(new_red,new_green,new_blue);
            dumbledoreGhostVP.setPixelVP(x,y,newColor);
         }  
      }
      /******************************************/
      // Save the resulting image in a file.
      final String savedFileName = "Hw1.ppm";
      fb.dumpFB2File( savedFileName );
      System.err.println("Saved " + savedFileName);
   }

   /**
    * Draws an individual diagonal stripe with the specified color
    * @param VP
    * @param x
    * @param y
    * @param C
    */
   private static void DrawStripe(FrameBuffer.Viewport VP, int x, int y, Color C){
      for(int i = y; i < VP.getHeightVP(); i++){
         int xLocation = i;
         for(int xOffset = x; xOffset < 3*90; xOffset += 90){
            xLocation += xOffset;
            for(int j = 0; j < 30; j++){
               VP.setPixelVP(xLocation + j,i,C);
            }
            xLocation -= xOffset; // Remove previous offset to horizontal-axis
         }
      }
   }

   /**
    * Fills in a Viewport with colored disks 
    * @param VP
    */
   private static void DrawDisk(FrameBuffer.Viewport VP){
      int centerX = VP.getWidthVP()/2;
      int centerY = VP.getHeightVP()/2;
      double d; //horizontal distance from the center of the disk
      //calculations for distance
      double xDiff; // square of x2 - x1
      double yDiff; // square of y2 - y1
      for(int j = 0; j < VP.getHeightVP();j++){
         yDiff = Math.pow(j-centerY,2.0);
         for(int i = 0; i < VP.getWidthVP(); i++){
            xDiff = Math.pow(i-centerX,2.0);
            d = Math.sqrt(xDiff + yDiff);
            
            if(d <= 150.0 && d >= 120.0) VP.setPixelVP(i,j,LIGHTBLUE);
            else if(d < 120.0 && d >= 90.0) VP.setPixelVP(i,j,LIGHTRED);
            else if(d < 90.0 && d >= 60.0) VP.setPixelVP(i,j,LIGHTGREEN);         
         }
      }
   }
}


