package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.BotStatistiqueProfil;

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

    private List<BotStatistiqueProfil> botStatistiqueProfils;
    private File file;

    public void exportData(List<BotStatistiqueProfil> botStatistiqueProfils, int nbDrawMatch, String fileName) {
        this.file = new File(fileName);
        this.setData(botStatistiqueProfils);
        if(file.exists()){
            LOGGER.info("Le fichier existe déjà");
            this.saveData(this.parseDataIfFileExist(getCSVFile(), nbDrawMatch));
        }
        else{
            this.createFileAndDirectoryIfNotExist();
            LOGGER.info("Le fichier n'existe pas");
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
                LOGGER.warning("Erreur lors de la lecture du fichier");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> parseDataIfFileNotExist(int nbDrawMatch){
        List<String[]> data = new ArrayList<>();
        String[] header = new String[7];
        header[0] = "Bot";
        header[1] = "Victoires";
        header[2] = "Défaites";
        header[3] = "Match nul";
        header[4] = "Nombre de tours";
        header[5] = "Nombre de parties";
        header[6] = "Nombre de tours moyen";
        data.add(header);
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            String[] statBot = new String[7];
            statBot[0] = botStatistiqueProfil.getBotName();
            statBot[1] = Integer.toString(botStatistiqueProfil.getNbVictories());
            statBot[2] = Integer.toString(botStatistiqueProfil.getNbDefeats());
            statBot[4] = Integer.toString(botStatistiqueProfil.getNbdeTours());
            statBot[5] = Integer.toString(botStatistiqueProfil.getNumberOfGames());
            statBot[6] = Integer.toString(botStatistiqueProfil.getNbdeTours()/botStatistiqueProfil.getNumberOfGames());
            data.add(statBot);
        }
        data.get(1)[3] = Integer.toString(nbDrawMatch);
        return data;
    }

    public List<String[]> parseDataIfFileExist(List<String[]> data, int nbDrawMatch){

        for(int i = 1; i < data.size(); i++) {
            for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
                if(data.get(i)[0].equals(botStatistiqueProfil.getBotName())){
                    data.get(i)[1] = Integer.toString(Integer.parseInt(data.get(i)[1]) + botStatistiqueProfil.getNbVictories());
                    data.get(i)[2] = Integer.toString(Integer.parseInt(data.get(i)[2]) + botStatistiqueProfil.getNbDefeats());
                    data.get(i)[4] = Integer.toString(Integer.parseInt(data.get(i)[4]) + botStatistiqueProfil.getNbdeTours());
                    data.get(i)[5] = Integer.toString(Integer.parseInt(data.get(i)[5]) + botStatistiqueProfil.getNumberOfGames());
                    data.get(i)[6] = Integer.toString((Integer.parseInt(data.get(i)[6]) + botStatistiqueProfil.getNbdeTours())/botStatistiqueProfil.getNumberOfGames());
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
                    LOGGER.warning("Le fichier n'a pas pu être créé");
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
