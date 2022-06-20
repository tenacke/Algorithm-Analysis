package algorithmAnalysis;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class ExpRunner {
    public enum Algorithms{
        INSERTION,
        QUICKSORT,
        SELECTION
    }
    private final AlgoLibrary lib = new AlgoLibrary();
    private final Random rng;
    private final int[] sizes;
    private Function<int[], Integer> currentAlgorithm;

    private final ArrayList<Float> countData = new ArrayList<>();
    private final ArrayList<Float> timeData = new ArrayList<>();
    private final ArrayList<Float> countMean = new ArrayList<>();
    private final ArrayList<Float> countStdDev = new ArrayList<>();
    private final ArrayList<Float> timeMean = new ArrayList<>();
    private final ArrayList<Float> timeStdDev = new ArrayList<>();

    public ExpRunner(long seed, int[] sizes) {
        this.rng = new Random(seed);
        this.sizes = sizes;
    }

    public ExpRunner(int[] sizes) {
        this.rng = new Random();
        this.sizes = sizes;
    }

    public ArrayList<Float> getCountMean() {
        return countMean;
    }
    public ArrayList<Float> getCountStdDev() {
        return countStdDev;
    }
    public ArrayList<Float> getTimeMean() {
        return timeMean;
    }
    public ArrayList<Float> getTimeStdDev() {
        return timeStdDev;
    }

    public void testSequence(int noOfExp){ // Execute a test sequence for the class to collect data
        countMean.clear();
        countStdDev.clear();
        timeMean.clear();
        timeStdDev.clear();
        for (int number: sizes) {
            for (int i = 0; i < noOfExp; i++) {
                testAlgorithm(number);
            }
            countMean.add(mean(countData));
            timeMean.add(mean(timeData));
            countStdDev.add(stdDev(countData));
            timeStdDev.add(stdDev(timeData));
            countData.clear();
            timeData.clear();
        }
    }

    public void setCurrentAlgorithm(Algorithms algortihmName){
        switch (algortihmName) {
            case INSERTION -> currentAlgorithm = lib::sortByInsertion;
            case QUICKSORT -> currentAlgorithm = lib::sortByQuickSort;
            case SELECTION -> currentAlgorithm = lib::sortBySelection;
        }
    }

    private void testAlgorithm(int size) { // Running the relevant algorithm and calculating the running time and counting the executed statements
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rng.nextInt();
        }

        long startTime = System.nanoTime();
        float count = currentAlgorithm.apply(array);
        long finishTime = System.nanoTime();
        long elapsedTime = (finishTime - startTime);

        countData.add(count);
        timeData.add((float) elapsedTime);
    }
    
    // Some statistics methods here
    private float mean(ArrayList<Float> list){
        float sum = 0;
        for (float number: list) {
            sum += number;
        }
        return (sum/list.size());
    }

    private float stdDev(ArrayList<Float> list){
        float average = mean(list);
        double normalizedSum = 0;
        for (float number : list) {
            normalizedSum += Math.pow(number-average,2);
        }
        return (float) Math.sqrt(normalizedSum/(list.size()));
    }
}
