
Compile each Java program from this folder with the following command-line.

> javac  -cp ..  ClientProgram.java

Run each Java program from this folder with the following command-line.

> java  -cp .;..  ClientProgram  ..\assets\image-file.ppm


The programs MagnifyPPM_ver1.java and MagnifyPPM_ver2.java show two ways
magnify an image to twice its original size. The programs ShrinkPPM_ver1.java
and ShrinkPPM_ver2.java show two ways to shrink an image to half of its
original size. These files are good examples of typical "image processing"
algorithms that manipulate an image pixel-by-pixel.

Shrink (several times) the following images and notice the difference
in quality between ShrinkPPM_ver1.java and ShrinkPPM_ver2.java.
  Vasily Kandinsky, Farbstudie Quadrate.ppm
  Vasily Kandinsky.ppm

Magnify (several times) the following images and notice the difference
in quality between MagnifyPPM_ver1.java and MagnifyPPM_ver2.java.
  Hulk.ppm
  Dumbledore.ppm


The program EdgeDetection.java is another simple "image processing"
algorithm.


These image processing programs all take a command-line argument
that specifies which PPM image file to process. Sample PPM image
files are in the ..\assets folder.
