package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.BotStatisticProfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVManager implements Loggeable {

    private List<BotStatisticProfile> botsStatisticsProfiles;
    private File file;

    public void exportData(List<BotStatisticProfile> botsStatisticsProfiles, int nbDrawMatch, String fileName) {
        this.file = new File(fileName);
        this.setData(botsStatisticsProfiles);
        if(file.exists()){
            LOGGER.info("File already exist");
            this.saveData(this.parseDataIfFileExist(getCSVFile(), nbDrawMatch));
        }
        else{
            this.createFileAndDirectoryIfNotExist();
            LOGGER.info("File doesn't exist");
            this.saveData(this.parseDataIfFileNotExist(nbDrawMatch));
        }
    }
    public void setData(List<BotStatisticProfile> botsStatisticsProfiles) {
        this.botsStatisticsProfiles = botsStatisticsProfiles;
    }

    public List<BotStatisticProfile> getData() {
        return botsStatisticsProfiles;
    }

    public List<String[]> getCSVFile() {
        Path path = Paths.get(this.file.toURI());
        List<String[]> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path)) {
            csvReader(reader, list);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void csvReader(Reader reader, List<String[]> list){
        try (CSVReader csvReader = new CSVReader(reader)) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                list.add(line);
            }
        }
        catch (IOException e) {
            LOGGER.warning("Error while reading the file");
        }
    }

    public String[] getHeader(){
        String[] header = new String[7];
        header[0] = "Bot";
        header[1] = "Victories";
        header[2] = "Defeats";
        header[3] = "Draws";
        header[4] = "Number of rounds";
        header[5] = "Number of games";
        header[6] = "Number of rounds per game";
        return header;
    }

    public List<String[]> parseDataIfFileNotExist(int nbDrawMatch){
        List<String[]> data = new ArrayList<>();
        String[] header = getHeader();
        data.add(header);
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            String[] statBot = new String[7];
            statBot[0] = botStatisticProfile.getBotName();
            statBot[1] = Integer.toString(botStatisticProfile.getNbVictories());
            statBot[2] = Integer.toString(botStatisticProfile.getNbDefeats());
            statBot[4] = Integer.toString(botStatisticProfile.getNbOfRounds());
            statBot[5] = Integer.toString(botStatisticProfile.getNbOfGames());
            statBot[6] = Integer.toString(botStatisticProfile.getNbOfRounds()/botStatisticProfile.getNbOfGames());
            data.add(statBot);
        }
        data.get(1)[3] = Integer.toString(nbDrawMatch);
        return data;
    }

    public List<String[]> parseDataIfFileExist(List<String[]> data, int nbDrawMatch){

        for(int i = 1; i < data.size(); i++) {
            for(BotStatisticProfile botStatisticProfile : botsStatisticsProfiles){
                if(data.get(i)[0].equals(botStatisticProfile.getBotName())){
                    data.get(i)[1] = Integer.toString(Integer.parseInt(data.get(i)[1]) + botStatisticProfile.getNbVictories());
                    data.get(i)[2] = Integer.toString(Integer.parseInt(data.get(i)[2]) + botStatisticProfile.getNbDefeats());
                    data.get(i)[4] = Integer.toString(Integer.parseInt(data.get(i)[4]) + botStatisticProfile.getNbOfRounds());
                    data.get(i)[5] = Integer.toString(Integer.parseInt(data.get(i)[5]) + botStatisticProfile.getNbOfGames());
                    data.get(i)[6] = Integer.toString((Integer.parseInt(data.get(i)[6]) + botStatisticProfile.getNbOfRounds())/botStatisticProfile.getNbOfGames());
                }
            }
        }
        data.get(1)[3] = Integer.toString(Integer.parseInt(data.get(1)[3]) + nbDrawMatch);
        return new ArrayList<>(data);
    }

    public void createFileAndDirectoryIfNotExist(){
        Path path = Paths.get(this.file.toPath().getName(1).toUri());
        if(!this.file.exists() && !this.file.isDirectory()){
            try {
                Files.createDirectories(path);
                if(!this.file.createNewFile()){
                    LOGGER.warning("Error while creating the file");
                }
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
