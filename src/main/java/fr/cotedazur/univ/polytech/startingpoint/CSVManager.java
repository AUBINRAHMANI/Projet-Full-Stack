package fr.cotedazur.univ.polytech.startingpoint;
import  com.opencsv.CSVWriter;
import fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager.BotStatistiqueProfil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {

    private List<BotStatistiqueProfil> botStatistiqueProfils;
    private int nbDrawMatch;
    public void exportData(List<BotStatistiqueProfil> botStatistiqueProfils, int nbDrawMatch, String fileName) {
        this.setData(botStatistiqueProfils, nbDrawMatch);
        this.saveData(parseData(), fileName);
    }
    public void setData(List<BotStatistiqueProfil> botStatistiqueProfils, int nbDrawMatch) {
        this.botStatistiqueProfils = botStatistiqueProfils;
        this.nbDrawMatch = nbDrawMatch;
    }

    public List<List<String>> parseData(){
        List<List<String>> data = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Bot");
        header.add("Victoires");
        header.add("Défaites");
        data.add(header);

        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            List<String> statBot = new ArrayList<>();
            statBot.add(botStatistiqueProfil.getBotName());
            statBot.add(Integer.toString(botStatistiqueProfil.getNbVictories()));
            statBot.add(Integer.toString(botStatistiqueProfil.getNbDefeats()));
            data.add(statBot);
        }
        List<String> statMatchNul = new ArrayList<>();
        statMatchNul.add("Match nul : ");
        statMatchNul.add(Integer.toString(nbDrawMatch));
        data.add(statMatchNul);
        return data;
    }

    public void saveData(List<List<String>> data, String filename){
        Path path = Path.of(".", "stats", "statistique.csv");
        File file = new File(path.toString());
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            data = parseData();
            CSVWriter writer = new CSVWriter(new FileWriter(filename, true));
            for(List<String> line : data){
                writer.writeNext(line.toArray(new String[0]));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
