package stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Step 2: Core Statistical Calculations Tests")
public class Step2StatisticsTest {

    private Stats stats;

    @BeforeEach
    void setUp() {
        // Capacity of 10
        stats = new Stats(10);
    }

    @Test
    @DisplayName("getTotal() accurately calculates sum of all added numbers")
    void testGetTotal() {
        stats.addValue(10);
        stats.addValue(25);
        stats.addValue(15);

        // 10 + 25 + 15 = 50
        assertEquals(50, stats.getTotal(), "Total of [10, 25, 15] should be 50");
    }

    @Test
    @DisplayName("getAverage() accurately calculates average as double")
    void testGetAverage() {
        stats.addValue(10);
        stats.addValue(20);
        stats.addValue(30);

        // (10 + 20 + 30) / 3 = 20.0
        assertEquals(20.0, stats.getAverage(), 0.001,
                "Average of [10, 20, 30] should be 20.0 (divided by count, not capacity)");

        stats.addValue(15);
        // (10 + 20 + 30 + 15) / 4 = 75 / 4 = 18.75
        assertEquals(18.75, stats.getAverage(), 0.001,
                "Average of [10, 20, 30, 15] should be 18.75");
    }

    @Test
    @DisplayName("getMax() finds the maximum value among added elements")
    void testGetMax() {
        stats.addValue(12);
        stats.addValue(95);
        stats.addValue(43);
        stats.addValue(77);

        assertEquals(95, stats.getMax(), "Maximum of [12, 95, 43, 77] should be 95");
    }

    @Test
    @DisplayName("getMin() finds the minimum value among added elements")
    void testGetMin() {
        stats.addValue(45);
        stats.addValue(12);
        stats.addValue(89);
        stats.addValue(100);

        assertEquals(12, stats.getMin(), "Minimum of [45, 12, 89, 100] should be 12");
    }

    @Test
    @DisplayName("Statistics work correctly on partially filled arrays (looping to count, NOT numbers.length)")
    void testPartiallyFilledArrayBounds() {
        // Capacity is 10, but only 3 negative numbers are added
        stats.addValue(-10);
        stats.addValue(-5);
        stats.addValue(-20);

        // In an uninitialized int[] in Java, unwritten slots are 0.
        // If the code iterates to numbers.length, 0 would incorrectly be chosen as getMax()!
        assertEquals(-5, stats.getMax(),
                "getMax() must only inspect added elements (count). It should return -5, not uninitialized 0");
        assertEquals(-20, stats.getMin(),
                "getMin() must only inspect added elements (count). It should return -20");
        assertEquals(-35, stats.getTotal(),
                "getTotal() must sum only added elements (-35)");
        assertEquals(-11.666, stats.getAverage(), 0.01,
                "getAverage() must divide by count 3, not capacity 10");
    }
}
