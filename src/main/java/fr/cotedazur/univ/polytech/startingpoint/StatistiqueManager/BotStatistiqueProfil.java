package fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Bot;

public class BotStatistiqueProfil {

    Bot bot;
    String botName;

    public BotStatistiqueProfil(Bot bot, String botName){
        this.bot=bot;
        this.botName= botName;
    }

    public Bot getBot() {
        return bot;
    }

    public String getBotName(){
        return this.botName;
    }
}
