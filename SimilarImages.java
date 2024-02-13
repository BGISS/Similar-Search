public class SimilarImages {
    private double sum;
    private String name;

    public SimilarImages(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public Double getSum() {
        return sum;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setSum(double newSum) {
        this.sum = newSum;
    }
}
