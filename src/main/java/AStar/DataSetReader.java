package AStar;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSetReader {

    /**
     * Gets a two dimensional arraylist of integers out of a csv file where the values are split by ";" and new lines split the datasets. Each value has to be an integer.
     * @param path path to the file 
     * @return two dimensional arraylist of integeres
     */
    public static ArrayList<ArrayList<Integer>> readFromCSV(String path) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
        Path pathToFile = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split(";");
                ArrayList<Integer> aList = new ArrayList<Integer>();

                for (int i = 0; i < attributes.length; i++) {
                    aList.add(Integer.parseInt(attributes[i]));
                }
                arrayList.add(aList);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return arrayList;
    }

}