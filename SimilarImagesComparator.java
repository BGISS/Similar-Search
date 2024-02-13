import java.util.Comparator;

public class SimilarImagesComparator implements Comparator<SimilarImages> {
    public int compare(SimilarImages o1, SimilarImages o2) {
        // return (o1.getSum().compareTo(o2.getSum()));
        if (o1.getSum() < o2.getSum()) {
            return 1;
        } else if (o2.getSum() == o1.getSum()) {
            return 0;
        } else {
            return -1;
        }
    }

}
