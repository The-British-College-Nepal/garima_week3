package stats;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Step 1: Clean Code, Naming Conventions & Encapsulation Tests")
public class Step1CleanCodeTest {

    @Test
    @DisplayName("Attribute 'numbers' is encapsulated (declared private)")
    void testNumbersFieldIsPrivate() throws NoSuchFieldException {
        Field numbersField = Stats.class.getDeclaredField("numbers");
        assertTrue(Modifier.isPrivate(numbersField.getModifiers()),
                "Attribute 'numbers' must be private to enforce encapsulation (e.g. 'private int[] numbers;')");
    }

    @Test
    @DisplayName("Attribute 'count' is encapsulated (declared private)")
    void testCountFieldIsPrivate() throws NoSuchFieldException {
        Field countField = Stats.class.getDeclaredField("count");
        assertTrue(Modifier.isPrivate(countField.getModifiers()),
                "Attribute 'count' must be private to enforce encapsulation (e.g. 'private int count;')");
    }

    @Test
    @DisplayName("Methods use meaningful names: getMax(), getMin(), getAverage()")
    void testMeaningfulMethodNamesExist() throws NoSuchMethodException {
        Method getMax = Stats.class.getMethod("getMax");
        assertEquals(int.class, getMax.getReturnType(), "getMax() must return int");

        Method getMin = Stats.class.getMethod("getMin");
        assertEquals(int.class, getMin.getReturnType(), "getMin() must return int");

        Method getAverage = Stats.class.getMethod("getAverage");
        assertEquals(double.class, getAverage.getReturnType(), "getAverage() must return double");

        Method getTotal = Stats.class.getMethod("getTotal");
        assertEquals(int.class, getTotal.getReturnType(), "getTotal() must return int");
    }

    @Test
    @DisplayName("Cryptic legacy names (getA, getB, get) are eliminated")
    void testCrypticNamesEliminated() {
        assertThrows(NoSuchMethodException.class, () -> Stats.class.getMethod("getA"),
                "Legacy method 'getA()' must be removed or renamed to 'getMax()'");
        assertThrows(NoSuchMethodException.class, () -> Stats.class.getMethod("getB"),
                "Legacy method 'getB()' must be removed or renamed to 'getMin()'");
        assertThrows(NoSuchMethodException.class, () -> Stats.class.getMethod("get"),
                "Legacy method 'get()' must be removed or renamed to 'getAverage()'");
    }

    @Test
    @DisplayName("getCount() returns the number of added elements, not total capacity")
    void testGetCountReturnsAddedCount() {
        Stats stats = new Stats(10);
        assertEquals(0, stats.getCount(), "A newly instantiated Stats object should have count 0");

        stats.addValue(42);
        stats.addValue(88);
        stats.addValue(12);

        assertEquals(3, stats.getCount(),
                "getCount() must return the number of elements added (3), NOT the array capacity (10)");
    }
}
