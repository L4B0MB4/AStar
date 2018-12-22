package AStar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DataSetReaderTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void readFromRealCsv() {
        ArrayList<ArrayList<Integer>> data = DataSetReader
                .readFromCSV("C:/Users/Lars/Documents/Software Projects/AStar/assets/dataset.csv");
        assertTrue("Data bigger 0", data.size() > 0);
    }

    @Test
    public void readFromNotExistingCsv() {
        ArrayList<ArrayList<Integer>> data = DataSetReader
                .readFromCSV("C:/Users/Lars/Documents/Software Projects/AStar/assets/test.csv");
        assertTrue("Data equals 0", data.size() == 0);
    }
}
