import java.awt.Color;

public class PixelArrays_v2
{
   public static void main(String[] args)
   {
      // This shows why we prefer row-major storage.
       int[]     a = new  int[4 * 5];
      byte[][][] b = new byte[4][5][3];
      Color[][]  c = new Color[4][5];
      for (int i = 0; i < 4; ++i) {
         for (int j = 0; j < 5; ++j) {
            c[i][j] = Color.black;
         }
      }

      System.out.println(java.util.Arrays.toString(a));
      System.out.println(java.util.Arrays.deepToString(b));
      System.out.println(java.util.Arrays.deepToString(c));
   }
}
