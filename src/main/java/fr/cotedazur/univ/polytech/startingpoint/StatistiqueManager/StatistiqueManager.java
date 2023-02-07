package fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager;

import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.List;
import java.util.logging.Logger;

public class StatistiqueManager implements Loggeable {

   private int NombreVictoireBot1=0;
   private int NombreVictoireBot2=0;

    private int NombreMatchNul=0;

    List<BotStatistiqueProfil> botStatistiqueProfils;

    public StatistiqueManager(){
        this.NombreVictoireBot1=0;
        this.NombreVictoireBot2=0;
        this.NombreMatchNul=0;
        botStatistiqueProfils=null;
    }

    public StatistiqueManager(int b1, int b2, int n){
        this.NombreVictoireBot1=b1;
        this.NombreVictoireBot2=b2;
        this.NombreMatchNul=n;
    }

    public void addVictoireBot1(){
        LOGGER.fine("Ronqldo +1");
        this.NombreVictoireBot1+=1;
    }

    public void addVictoireBot2(){
        this.NombreVictoireBot2+=1;
    }

    public void addNombreMatchNul(){
        this.NombreMatchNul+=1;
    }

    public int getNombreVictoireBot1(){
        return this.NombreVictoireBot1;
    }

    public int getNombreVictoireBot2(){
        return this.NombreVictoireBot2;
    }

    public int getNombreMatchNul(){
        return this.NombreMatchNul;
    }

    public String toString(){
        return "Nombre de victoire Ronaldo : " + this.getNombreVictoireBot1() + "\n"
                + "Nombre de victoire Messi : " + this.getNombreVictoireBot2() + "\n" +
                "Nombre de match nul : " + this.getNombreMatchNul();
    }



    public void addBotStatistiqueProfil(BotStatistiqueProfil botStatistiqueProfil){
        this.botStatistiqueProfils.add(botStatistiqueProfil);
    }



public void addActionPanda(Playeable bot){
    for (BotstatProf  botstatprofil  : this.botStatProfils){
        if(botstatprofil.getBot() == bot)
            botstatprofil.add ActionPanda
}