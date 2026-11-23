package stats;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Step 3: Javadoc Documentation & Defensive Exceptions Tests")
public class Step3DocumentationTest {

    @Test
    @DisplayName("getMax() throws IllegalStateException when no values have been added")
    void testGetMaxThrowsOnEmpty() {
        Stats stats = new Stats(5);
        IllegalStateException ex = assertThrows(IllegalStateException.class, stats::getMax,
                "Calling getMax() on an empty Stats object must throw IllegalStateException");
        assertTrue(ex.getMessage().toLowerCase().contains("no value") || ex.getMessage().toLowerCase().contains("empty"),
                "Exception message should mention that no values have been added");
    }

    @Test
    @DisplayName("getMin() throws IllegalStateException when no values have been added")
    void testGetMinThrowsOnEmpty() {
        Stats stats = new Stats(5);
        IllegalStateException ex = assertThrows(IllegalStateException.class, stats::getMin,
                "Calling getMin() on an empty Stats object must throw IllegalStateException");
        assertTrue(ex.getMessage().toLowerCase().contains("no value") || ex.getMessage().toLowerCase().contains("empty"),
                "Exception message should mention that no values have been added");
    }

    @Test
    @DisplayName("getAverage() throws IllegalStateException when no values have been added")
    void testGetAverageThrowsOnEmpty() {
        Stats stats = new Stats(5);
        IllegalStateException ex = assertThrows(IllegalStateException.class, stats::getAverage,
                "Calling getAverage() on an empty Stats object must throw IllegalStateException");
        assertTrue(ex.getMessage().toLowerCase().contains("no value") || ex.getMessage().toLowerCase().contains("empty"),
                "Exception message should mention that no values have been added");
    }

    @Test
    @DisplayName("Stats.java source file contains required Javadoc tags (@author, @param, @return)")
    void testJavadocTagsPresentInSource() throws IOException {
        Path sourcePath = Paths.get("src", "main", "java", "stats", "Stats.java");
        assertTrue(Files.exists(sourcePath), "Stats.java source file must exist at " + sourcePath);

        String content = Files.readString(sourcePath);

        assertTrue(content.contains("@author"),
                "Stats.java must contain an @author Javadoc tag on the class header");
        assertTrue(content.contains("@param"),
                "Stats.java must contain @param Javadoc tags on methods/constructors with parameters");
        assertTrue(content.contains("@return"),
                "Stats.java must contain @return Javadoc tags on methods returning values");
    }

    @Test
    @DisplayName("Driver main method executes cleanly without errors")
    void testDriverExecution() {
        assertDoesNotThrow(() -> Driver.main(new String[0]),
                "Driver.main() should run cleanly without unhandled exceptions");
    }
}
