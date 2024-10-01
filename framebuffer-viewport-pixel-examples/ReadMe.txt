
Use the
   build_&_test_FrameBuffer.cmd
command-file to both compile the FrameBuffer.java file and to run
the tests that are built into the file's main() method. Open the
FrameBuffer.java file and look at its main() method (near the end
of the file) to see some examples of how to use a FrameBuffer and
Viewports. Compare the main() method's code to the image files
created by the main() method.


Use the
   build_FrameBuffer_javadoc.cmd
command-file to build the Javadocs for the FrameBuffer class. Open the
html folder and double click on the index.hrml file to start reading
the Javadocs. In particular, what constructors and methods does the
FrameBuffer class provide?


Use the
   build_all_classes.cmd
command-file to compile all the sample Java program in this folder.


The example programs
   Example_1.java through Example_7.java
each create a FrameBuffer object, fill it with some simple pixel data,
and store the results in an image file.


The example program
   FlippedViewports.java
shows how to use several Viewport objects within a single FrameBuffer
object. This  program takes a command-line argument that specifies
which PPM image file to load into the viewports. Sample PPM image
files are in the assets folder.


The example program
   ViewportInFrameBuffer.java
creates a very small FrameBuffer containing a Viewport and then prints the
FrameBuffer to the console window. This give us an (ascii) visualization
of how a Viewport object gets its pixels from the FrameBuffer object it
is nested in.


The document
   Pixel data - framebuffer vs data structures vs file formats.txt
contains an explanation of the many different ways we can store pixel
data in a data structure, or in an image file on a storage device,
or in a hardware framebuffer.


The sub-folder
   pixel arrays & framebuffer storage
contains examples that simulate a simplified framebuffer data structure.
These simplified examples try to give you an idea of how the more complex
(larger) FrameBuffer data structure is stored in memory.


The sub-folder
   pixel-algorithms
contains three examples of image processing algorithms that manipulate
all the pixels in a FrameBuffer. One algorithm does "edge detection".
The other two algorithms magnify and shrink the image in the FrameBuffer.
The image processing programs all take a command-line argument that
specifies which PPM image file to process.


The sub-folder
   image-file-formats
contains many examples of different image file formats. Try to do
the experiments described in the folder's ReadMe.txt file.

Cmd commands for running the magnify command
javac -cp .. MagnifyPPM_ver2.java
java -cp ..;. MagnifyPPM_ver2 ..\assets\Dumbledore.ppm

