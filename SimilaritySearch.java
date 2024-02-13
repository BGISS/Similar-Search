
//Girish Bissessur
//Student num 300302319
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SimilaritySearch {
    public static void main(String[] args) {
        ColorImage image = new ColorImage("q00.ppm");
        ColorHistogram queryHistogram = new ColorHistogram(3);
        queryHistogram.setImage(image);
        PriorityQueue<SimilarImages> queue = new PriorityQueue<>(new SimilarImagesComparator());
        Map<Double, String> map = new TreeMap<>(Comparator.reverseOrder());
        File filePath = new File("imageDataset2_15_20");
        File filesList[] = filePath.listFiles();
        for (File file : filesList) {
            if (file.getName().contains("txt")) {
                ColorHistogram imageHistogram = new ColorHistogram("imageDataset2_15_20//" + file.getName());
                // if (map.containsKey(queryHistogram.compare(imageHistogram))) {
                // list.add(queryHistogram.compare(imageHistogram));
                // }
                // map.put(queryHistogram.compare(imageHistogram), file.getName());
                double x = queryHistogram.compare(imageHistogram);
                String y = file.getName();
                queue.add(new SimilarImages(y, x));
            }
        }
        String[] mostSimilarImages = new String[5];
        int count = 0;
        for (int i = 0; i < 5; i++) {
            mostSimilarImages[i] = queue.poll().getName();

        }
        System.out.println(Arrays.toString(mostSimilarImages));
        // for (Map.Entry<Double, String> entry : map.entrySet()) {
        // if (count < 5) {
        // mostSimilarImages[count] = entry.getValue();
        // // System.out.println(entry.getKey());
        // count++;
        // } else {
        // break;
        // }
        // }
        // System.out.println(Arrays.toString(mostSimilarImages));

    }
}
