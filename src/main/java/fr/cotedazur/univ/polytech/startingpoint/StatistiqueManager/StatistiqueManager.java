package fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StatistiqueManager implements Loggeable {

    private List<BotStatistiqueProfil> botStatistiqueProfils;
    private int NombreMatchNul=0;


    public StatistiqueManager(){
        this.botStatistiqueProfils = new ArrayList<>();
        this.NombreMatchNul=0;
    }

    public void addMatchNul(){
        this.NombreMatchNul+=1;
    }

    public void initBotsStatistiquesProfiles(List<BotProfil> botProfils) {
        for(BotProfil botProfil : botProfils){
            botStatistiqueProfils.add(new BotStatistiqueProfil(botProfil.getBot(),botProfil.getBotName()));
        }
    }

    void addWinner(Playable bot){

    }
    void addLoser(Playable bot){

    }

    @Override
    public String toString() {
        return "StatistiqueManager{" +
                "botStatistiqueProfils=" + botStatistiqueProfils +
                ", NombreMatchNul=" + NombreMatchNul +
                '}';
    }
}
