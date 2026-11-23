package stats;

import java.util.Random;

/**
 * Driver class to demonstrate the Stats class functionality.
 */
public class Driver {

    public static void main(String[] args) {
        final int VALUES = 10;
        Stats stats = new Stats(VALUES);
        Random random = new Random();

        // Populate with 10 random integer values between 1 and 100
        for (int i = 0; i < VALUES; i++) {
            stats.addValue(random.nextInt(100) + 1);
        }

        System.out.println("==================================================");
        System.out.println("📊 SSD Week 3: Stats Class Demonstration");
        System.out.println("==================================================");
        System.out.println("Numbers stored : " + stats);
        System.out.println("Count          : " + stats.getCount());
        System.out.println("Total          : " + stats.getTotal());
        System.out.printf("Average        : %.2f\n", stats.getAverage());
        System.out.println("Minimum value  : " + stats.getMin());
        System.out.println("Maximum value  : " + stats.getMax());
        System.out.println("==================================================");
    }
}
