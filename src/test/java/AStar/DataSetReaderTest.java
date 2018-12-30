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
                .readFromCSV(System.getProperty("user.dir")+"\\assets\\dataset.csv");
        assertTrue("Data bigger 0", data.size() > 0);
    }

    @Test
    public void readFromNotExistingCsv() {
        ArrayList<ArrayList<Integer>> data = DataSetReader
                .readFromCSV(System.getProperty("user.dir")+"\\assets\\notexsisting.csv");
        assertTrue("Data equals 0", data.size() == 0);
    }
}
