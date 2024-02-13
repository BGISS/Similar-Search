import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ColorHistogram {
    private int[] histogram;
    private ColorImage image;
    private boolean query;// Checking if the query image is being used
    private int totalPixels, d;

    public ColorHistogram(int d) {
        this.query = true;
        this.d = d;
        histogram = new int[(int) Math.pow(2, 3 * d)];

    }

    public ColorHistogram(String filename) {
        try {
            BufferedReader data = new BufferedReader(new FileReader(filename));
            this.query = false;
            String line = data.readLine();
            d = (int) ((Math.log(Integer.parseInt(line)) / Math.log(2)) / 3);
            histogram = new int[Integer.parseInt(line)];
            while ((line = data.readLine()) != null) {
                String[] histogramData = line.split(" ");
                for (int i = 0; i < histogramData.length; i++) {
                    histogram[i] = Integer.parseInt(histogramData[i]);
                    totalPixels += Integer.parseInt(histogramData[i]);
                }
            }
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage(ColorImage image) {
        this.image = image;
    }

    public double[] getHistogram() {
        double[] normalHistogram = new double[histogram.length];
        if (query) {
            totalPixels = (image.getHeight() * image.getWidth());
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    int[] pixel = image.getPixel(i, j);
                    int red = pixel[0];
                    int green = pixel[1];
                    int blue = pixel[2];
                    int index = (red << (2 * d)) + ((green << d)) + blue;
                    histogram[index]++;
                }
            }
        }
        double kok = 0;
        for (int i = 0; i < histogram.length; i++) {
            normalHistogram[i] = ((double) histogram[i] / (double) totalPixels);
            kok += normalHistogram[i];
        }
        System.out.println(kok);
        return normalHistogram;
    }

    public double compare(ColorHistogram hist) {
        double[] hist0 = getHistogram();
        double[] hist1 = hist.getHistogram();
        double min = 0;
        for (int i = 0; i < histogram.length; i++) {// Assuming they both have the same length
            min += Math.min(hist1[i], hist0[i]);
        }
        return min;
    }

    public int getTotalPixels() {
        return totalPixels;
    }

    public ColorImage getColorImage() {
        return this.image;
    }

    public void SaveColorHistogram(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.write(totalPixels);
            String histoString = " ";
            for (int i = 0; i < histogram.length; i++) {
                histoString += histogram[i] + " ";
            }
            writer.write(histoString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ColorImage image = new ColorImage("q00.ppm");
        image.reduceColor(3);
        ColorHistogram hist = new ColorHistogram(3);
        hist.setImage(image);
        // System.out.println(Arrays.toString(hist.getHistogram()));
        ColorHistogram histogram = new ColorHistogram("imageDataset2_15_20//4862.jpg.txt");
        System.out.println(hist.compare(histogram));
    }
}
