package algorithmAnalysis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;

public class Main {
    static ExpRunner expRunner;
    static int[] sizes;
    static int noOfExp;
    public static void main(String[] args){
        /* Before running the code, please make sure that these variables are initialized;
        *  "sizes" variable is an array that you want to make experiments with n€{int[]} (Main::getRange method can be used as an array generator)
        *  "noOfExp" variable holds the value which is how many experiments get repeated
        *  "randomSeed" variable is an option to select an initial seed for Random Number Generator (Remember that the variable should be added later at line:19) */
        sizes = new int[] {0, 10, 20, 30, 40, 50, 100, 500, 1000}; //getRange(0,1001,10);
        noOfExp = 10;
        long randomSeed;
        expRunner = new ExpRunner(sizes); // this can be like "new ExpRunner(randomSeed, sizes)"

        try {
            // File names can be custom organized
            FileWriter writerInsertion = new FileWriter("Insertion.txt");
            expRunner.setCurrentAlgorithm(ExpRunner.Algorithms.INSERTION);
            testUtility(writerInsertion);

            FileWriter writerQuickSort = new FileWriter("QuickSort.txt");
            expRunner.setCurrentAlgorithm(ExpRunner.Algorithms.QUICKSORT);
            testUtility(writerQuickSort);

            FileWriter writerSelection = new FileWriter("Selection.txt");
            expRunner.setCurrentAlgorithm(ExpRunner.Algorithms.SELECTION);
            testUtility(writerSelection);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void testUtility(FileWriter writer) throws IOException {
        expRunner.testSequence(noOfExp);
        for (int i = 0; i < sizes.length; i++) {
            writer.write("CountTest: "+i+" Mean: "+ expRunner.getCountMean().get(i)+" StdDev: "+ expRunner.getCountStdDev().get(i)+"\n");
        }
        writer.write("-".repeat(40)+"\n");
        for (int i = 0; i < sizes.length; i++) {
            writer.write("TimeTest: "+i+" Mean: "+ expRunner.getTimeMean().get(i)+" StdDev: "+ expRunner.getTimeStdDev().get(i)+"\n");
        }
        writer.close();
    }

    static int[] getRange(final int min, final int max, final int step) { // this can be used as custom sizes of input
        return IntStream.iterate(min, i -> i < max, i -> i + step).toArray();
    }
}
