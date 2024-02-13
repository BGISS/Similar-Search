import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ColorImage {
    private int width, height, depth;
    private int imagePixels[][][];

    public ColorImage(String filename) {
        try {
            BufferedReader data = new BufferedReader(new FileReader("queryImages//" + filename));
            // We ignore the first line with the P3 and the second line with the comment
            data.readLine();
            data.readLine();
            // We read the line with the width and height and store it in an array
            String dimensions[] = data.readLine().trim().split(" ");
            // The array is split and the corresponding values assigned to the width and
            // height
            width = Integer.parseInt(dimensions[0]);
            height = Integer.parseInt(dimensions[1]);
            this.depth = 8;
            data.readLine();
            imagePixels = new int[height][width][3];
            // Reading the pixel values and storing them in the array
            String line;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if ((line = data.readLine()) == null) {
                        break;
                    }
                    String[] lineData = line.split(" ");
                    for (int k = 0; k < lineData.length; k++) {
                        imagePixels[i][j][0] = Integer.parseInt(lineData[k++]);
                        imagePixels[i][j][1] = Integer.parseInt(lineData[k++]);
                        imagePixels[i][j][2] = Integer.parseInt(lineData[k]);
                    }
                }
            }
            data.close();
        } catch (

        IOException e) {
            e.printStackTrace();
        }

    }

    public int[] getPixel(int i, int j) {
        return imagePixels[i][j];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public void reduceColor(int d) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Using the bit shift operator we reduce the pixel values for R, G and B
                imagePixels[i][j][0] = (imagePixels[i][j][0] >> (depth - d));
                imagePixels[i][j][1] = (imagePixels[i][j][1] >> (depth - d));
                imagePixels[i][j][2] = (imagePixels[i][j][2] >> (depth - d));

            }
        }
    }

    public static void main(String[] args) {
        ColorImage image = new ColorImage("q00.ppm");
        // System.out.println(Arrays.toString(image.getPixel(350, 100)));
    }
}