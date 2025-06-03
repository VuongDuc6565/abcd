package hus.oop.statistics;

public class Statistics {
    private MyList data;

    /**
     * Khởi tạo dữ liệu cho BasicStatistic.
     */
    public Statistics(MyList data) {
        this.data = data;
    }

    public double max() {
        MyIterator it = data.iterator(0);
        double max = Double.NEGATIVE_INFINITY;
        while (it.hasNext()) {
            max = Math.max(max, it.next().doubleValue());
        }
        return max;
    }

    public double min() {
        MyIterator it = data.iterator(0);
        double min = Double.POSITIVE_INFINITY;
        while (it.hasNext()) {
            min = Math.min(min, it.next().doubleValue());
        }
        return min;
    }

    public double mean() {
        MyIterator it = data.iterator(0);
        double sum = 0;
        int count = 0;
        while (it.hasNext()) {
            sum += it.next().doubleValue();
            count++;
        }
        return sum / count;
    }

    public double variance() {
        double mean = mean();
        MyIterator it = data.iterator(0);
        double sum = 0;
        int count = 0;
        while (it.hasNext()) {
            double x = it.next().doubleValue();
            sum += (x - mean) * (x - mean);
            count++;
        }
        return sum / count;
    }

    public int search(double data) {
        return this.data.binarySearch(data);
    }

    public double[] rank() {
        MyList sorted = data.sortIncreasing();
        double[] rank = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            double val = ((Number) data.iterator(i).next()).doubleValue();
            rank[i] = sorted.binarySearch(val) + 1;
        }
        return rank;
    }

}
