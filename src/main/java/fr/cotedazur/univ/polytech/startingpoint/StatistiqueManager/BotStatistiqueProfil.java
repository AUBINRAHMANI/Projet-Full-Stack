package fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Bot;

import java.util.List;

public class BotStatistiqueProfil {

    Bot bot;
    String botName;
    private int NombreVictoireBot=0;

    public BotStatistiqueProfil(Bot bot, String botName){
        this.NombreVictoireBot=0;
        this.bot=bot;
        this.botName=botName;
    }

   /* public StatistiqueManager(int b1, int b2, int n){
        this.NombreVictoireBot1=b1;
        this.NombreVictoireBot2=b2;
        this.NombreMatchNul=n;
    }
*/
    public void addVictoireBot(){
        this.NombreVictoireBot+=1;
    }


    public int getNombreVictoireBot(){
        return this.NombreVictoireBot;
    }
    public Bot getBot() {
        return bot;
    }

    public String getBotName(){
        return this.botName;
    }

    public String toString(){
        return "Nombre de victoire de " + this.getBotName() + ": " + this.getNombreVictoireBot();
    }

}
