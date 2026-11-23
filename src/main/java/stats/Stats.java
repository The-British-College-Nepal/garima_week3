
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

    // Step 1.4: Descriptive method name
    public int getMax() {
        return 0;
    }

    // Step 1.5: Descriptive method name
    public int getMin() {
        return 0;
    }

    public int getTotal() {
        return 0;
    }

    // Step 1.6: Descriptive method name
    public double getAverage() {
        return 0.0;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}
