
package stats;

import java.util.Arrays;

public class Stats {

    // Step 1.2: Encapsulated attributes
    private int[] numbers;
    private int count;

    public Stats(int capacity) {
        numbers = new int[capacity];
    }

    public void addValue(int value) {
        numbers[count] = value;
        count++;
    }

    // Step 1.3: Return actual number of values added
    public int getCount() {
        return this.count;
    }

    // Step 2: Calculate maximum value
    public int getMax() {
        int max = numbers[0];

        for (int i = 1; i < count; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        return max;
    }

    // Step 2: Calculate minimum value
    public int getMin() {
        int min = numbers[0];

        for (int i = 1; i < count; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }

        return min;
    }

    // Step 2: Calculate total
    public int getTotal() {
        int total = 0;

        for (int i = 0; i < count; i++) {
            total += numbers[i];
        }

        return total;
    }

    // Step 2: Calculate average
    public double getAverage() {
        int total = getTotal();

        return total / (double) count;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}
