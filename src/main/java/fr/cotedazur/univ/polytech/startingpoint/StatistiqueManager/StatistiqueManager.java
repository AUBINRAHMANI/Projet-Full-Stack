package fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager;

import fr.cotedazur.univ.polytech.startingpoint.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StatistiqueManager implements Loggeable {

    private List<BotStatistiqueProfil> botStatistiqueProfils;
    private int NombreMatchNul=0;

    public StatistiqueManager(){
        botStatistiqueProfils=null;
        this.NombreMatchNul=0;
        botStatistiqueProfils = new ArrayList<>();
    }

    public void addNombreMatchNul(){
        this.NombreMatchNul+=1;
    }

    public int getNombreMatchNul(){
        return this.NombreMatchNul;
    }

    public void addBotStatistiqueProfil(BotStatistiqueProfil botStatistiqueProfil){
        this.botStatistiqueProfils.add(botStatistiqueProfil);
    }


   /* public void addActionPanda(Playeable bot) {
        for (BotStatistiqueProfil botstatprofil : this.botStatistiqueProfils) {
            if (botstatprofil.getBot() == bot) {
                botstatprofil.add ActionPanda;
            }
        }
    }
*/
    public void initBotsStatistiquesProfiles(List<BotProfil> botProfils) {
        for(BotProfil botProfil : botProfils){
            botStatistiqueProfils.add(new BotStatistiqueProfil(botProfil.getBot(),botProfil.getBot().getBotName()));
        }
    }


}
