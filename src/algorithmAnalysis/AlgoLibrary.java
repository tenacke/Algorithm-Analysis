package algorithmAnalysis;

public class AlgoLibrary {
    private int innermostCount;
    public int sortByInsertion(int[] array){
        innermostCount = 0;
        for (int i = 1; i < array.length; i++) { // looking every element except the first one
            innermostCount++;
            int element = array[i]; // storing the relevant element
            int stride = 0; // taking a stride variable that holds the value the element will move
            for (int j = 0; j < i; j++) {  // check the element with all other elements in the left
                if (array[i] < array[i-j-1]) { stride++; innermostCount++; } else { break; }
            }
            for (int j = 0; j < stride; j++) { // sliding other elements to the right
                array[i-j] = array[i-j-1]; innermostCount++;
            }
            array[i-stride] = element; // inserting the main element to its real position
        }
        return innermostCount;
    }

    public int sortByQuickSort(int[] array){
        innermostCount = 0;
        quickSort(array, 0, array.length-1);
        return innermostCount;
    }

    private void quickSort(int[] array, int _initial, int _final){
        if (_initial < _final){ // quickSort both partitions around the pivot recursively
            innermostCount++;
            int p = partition(array, _initial, _final); // p returns the location of pivot value
            quickSort(array, _initial, p-1);
            quickSort(array, p+1, _final);
        }
    }

    private int partition(int[] array, int _initial, int _final){
        innermostCount++;
        int pivot = array[_initial]; // always pick the first element as pivot
        int count = _initial; // taking a count variable to hold how many elements are swapped and use it for swapping
        // it also takes the initial location of pivot
        for (int i = _initial+1; i <= _final; i++) { // iterate over every element except pivot
            innermostCount++;
            if (array[i] < pivot){ // compare with pivot and if it is smaller then swap
                count++;
                innermostCount++;
                swap(array, i, count);
            }
        }
        swap(array, _initial, count); // finally swap the pivot to its position supposed to
        return count;
    }

    private void swap(int[] array, int i, int j){ // simple swapping method
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int sortBySelection(int[] array){
        innermostCount = 0;
        for (int i = 0; i < array.length; i++) { // Iterate through every element
            innermostCount++;
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) { // Iterate again to find the index of the minimum element
                innermostCount++;
                if (array[j] < array[minIndex]){
                    minIndex = j;
                    innermostCount++;
                }
            }
            swap(array, i, minIndex); // swap the minimum element to its supposed position
        }
        return innermostCount;
    }
}
