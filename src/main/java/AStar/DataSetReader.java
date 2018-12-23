package AStar;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSetReader {

    public static ArrayList<ArrayList<Integer>> readFromCSV(String fileName) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
        System.out.println(Paths.get(fileName));
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
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

        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.get(i).size(); j++) {
                System.out.print(arrayList.get(i).get(j) + " ");
            }
            System.out.println();
        }

        return arrayList;

    }

}