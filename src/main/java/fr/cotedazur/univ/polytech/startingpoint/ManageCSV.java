package fr.cotedazur.univ.polytech.startingpoint;
import  com.opencsv.CSVReader;
import  com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ManageCSV {

    public ManageCSV() {

    }

    public void writeLineByLine(List<String[]> lines, Path path) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))) {
            for (String[] line : lines) {
                writer.writeNext(line);
            }
        }
    }

    public List<String[]> readLineByLine(Path filePath) throws Exception {
        List<String[]> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
        }
        return list;
    }

    public void exportData(String[] data, String path) throws IOException {
        data = parseData(getData(data)).split(",");
        saveData(data, path);
    }
    public String getData(String[] data) {
        String result = "";
        for (String s : data) {
            result += s + ",";
        }
        return result;
    }

    public String parseData(String data){
        String result = "";
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == ',') {
                result += ";";
            } else {
                result += data.charAt(i);
            }
        }
        return result;
    }

    public void saveData(String[] data, String path) throws IOException {
        data = parseData(getData(data)).split(",");
        CSVWriter writer = new CSVWriter(new FileWriter(path, true));
        writer.writeNext(data);
        writer.close();
    }


}
