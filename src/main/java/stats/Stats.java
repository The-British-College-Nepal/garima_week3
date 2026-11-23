package stats;

import java.util.Arrays;

/**
 * Calculates statistical metrics from a collection of integer values.
 *
 * @author Your Name
 */
public class Stats {

    private int[] numbers;
    private int count;

    /**
     * Creates a Stats object with the given capacity.
     *
     * @param capacity maximum number of values the array can store
     */
    public Stats(int capacity) {
        numbers = new int[capacity];
        count = 0;
    }

    /**
     * Adds an integer value to the collection.
     *
     * @param value the integer value to be added
     */
    public void addValue(int value) {
        numbers[count] = value;
        count++;
    }

    /**
     * Returns the number of values currently stored.
     *
     * @return the actual number of values added
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Calculates the maximum value in the collection.
     *
     * @return the highest integer value
     * @throws IllegalStateException if no values have been added
     */
    public int getMax() {

        if (count == 0) {
            throw new IllegalStateException(
                    "No values have been added to calculate statistics.");
        }

        int max = numbers[0];

        for (int i = 1; i < count; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        return max;
    }

    /**
     * Calculates the minimum value in the collection.
     *
     * @return the lowest integer value
     * @throws IllegalStateException if no values have been added
     */
    public int getMin() {

        if (count == 0) {
            throw new IllegalStateException(
                    "No values have been added to calculate statistics.");
        }

        int min = numbers[0];

        for (int i = 1; i < count; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }

        return min;
    }

    /**
     * Calculates the sum of all values in the collection.
     *
     * @return the total sum of all added values
     */
    public int getTotal() {

        int total = 0;

        for (int i = 0; i < count; i++) {
            total += numbers[i];
        }

        return total;
    }

    /**
     * Calculates the average of all values in the collection.
     *
     * @return the average value as a double
     * @throws IllegalStateException if no values have been added
     */
    public double getAverage() {

        if (count == 0) {
            throw new IllegalStateException(
                    "No values have been added to calculate statistics.");
        }

        return getTotal() / (double) count;
    }

    /**
     * Returns a string representation of the stored array.
     *
     * @return the array contents as a string
     */
    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}