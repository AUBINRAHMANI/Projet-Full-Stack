package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager.BotStatistiqueProfil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {

    private List<BotStatistiqueProfil> botStatistiqueProfils;
    private File file;

    public void exportData(List<BotStatistiqueProfil> botStatistiqueProfils, int nbDrawMatch, String fileName) {
        this.file = new File(fileName);
        this.setData(botStatistiqueProfils);
        if(file.exists()){
            this.saveData(this.parseDataIfFileExist(getCSVFile(), nbDrawMatch));
            System.out.println("caca");
        }
        else{
            this.createFileIfNotExist();
            System.out.println(file.getAbsoluteFile());
            this.saveData(this.parseDataIfFileNotExist(nbDrawMatch));
        }
    }
    public void setData(List<BotStatistiqueProfil> botStatistiqueProfils) {
        this.botStatistiqueProfils = botStatistiqueProfils;
    }

    public List<String[]> getCSVFile() {
        Path path = Paths.get(this.file.toURI());
        List<String[]> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
            catch (IOException e) {
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> parseDataIfFileNotExist(int nbDrawMatch){
        List<String[]> data = new ArrayList<>();
        String[] header = new String[4];
        header[0] = "Bot";
        header[1] = "Victoires";
        header[2] = "Défaites";
        header[3] = "Match nul";
        data.add(header);
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            String[] statBot = new String[3];
            statBot[0] = botStatistiqueProfil.getBotName();
            statBot[1] = Integer.toString(botStatistiqueProfil.getNbVictories());
            statBot[2] = Integer.toString(botStatistiqueProfil.getNbDefeats());
            data.add(statBot);
        }
        String[] statMatchNul = new String[4];
        statMatchNul[0] = "";
        statMatchNul[1] = "";
        statMatchNul[2] = "";
        statMatchNul[3] = Integer.toString(nbDrawMatch);
        data.add(statMatchNul);
        return data;
    }

    public List<String[]> parseDataIfFileExist(List<String[]> data, int nbDrawMatch){
        data = getCSVFile();
        List<String[]> refreshedData = new ArrayList<>();

        for(int i = 1; i < data.size(); i++) {
            for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
                if(data.get(i)[0].equals(botStatistiqueProfil.getBotName())){
                    data.get(i)[1] = Integer.toString(Integer.parseInt(data.get(i)[1]) + botStatistiqueProfil.getNbVictories());
                    data.get(i)[2] = Integer.toString(Integer.parseInt(data.get(i)[2]) + botStatistiqueProfil.getNbDefeats());
                }
            }
        }
        data.get(data.size()-1)[3] = Integer.toString(Integer.parseInt(data.get(data.size()-1)[3]) + nbDrawMatch);
        for (int i = 0; i < data.size(); i++) {
            refreshedData.add(data.get(i));
        }
        return refreshedData;
    }

    public void createFileIfNotExist(){
        if(!this.file.exists()){
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData(List<String[]> data){
        try {

            CSVWriter writer = new CSVWriter(new FileWriter(this.file, false));
            for(String[] line : data){
                writer.writeNext(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
