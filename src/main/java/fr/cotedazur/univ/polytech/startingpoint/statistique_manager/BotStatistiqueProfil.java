package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

public class BotStatistiqueProfil {

    String botName;
    Playable bot;
    private int nbVictories;
    private int nbDefeats;

    private int numberdeTours;
    private int numberdeParties;

    private int numberDealParPartie;

    private int numberDealMoveGardener;

    private int numberDealMovePanda;

    private int numberDealPickObjectif;

    private int numberDealPutIrrigation;

    private int numberDealPutPlot;

    private int numberDealRain;

    private int numberDealThunder;




    public BotStatistiqueProfil(Playable bot, String botName) {
        this.bot = bot;
        this.botName = botName;
        this.nbVictories = 0;
        this.nbDefeats = 0;
        this.numberdeTours = 0;
        this.numberdeParties = 0;
        this.numberDealParPartie=0;
        this.numberDealMoveGardener=0;
        this.numberDealMovePanda=0;
        this.numberDealPickObjectif=0;
        this.numberDealPutIrrigation=0;
        this.numberDealPutPlot=0;
        this.numberDealRain=0;
        this.numberDealThunder=0;
    }

    public void addVictory() {
        nbVictories += 1;
    }

    public void addDefeat() {
        nbDefeats += 1;
    }

    public void addnumberOfRounds() {
        ++numberdeTours;
    }

    public void addNumberOfGames() {
        ++numberdeParties;
    }
    public void addNumbereDealPerGames(){
        ++numberDealParPartie;
    }

    public void addDealMoveGardener(){
        ++this.numberDealMoveGardener;
    }
    public void addDealMovePanda(){
        ++this.numberDealMovePanda;
    }

    public void addDealPickObjective(){
        ++this.numberDealPickObjectif;

    }
    public void addDealPutIrrigation(){
        ++this.numberDealPutIrrigation;
    }

    public void addDealPutPlot(){
        ++this.numberDealPutIrrigation;
    }

    public void addDealRain(){
        ++this.numberDealRain;
    }

    public void addDealThunder(){
        ++this.numberDealThunder;
    }

    public Playable getBot() {
        return bot;
    }

    public String getBotName() {
        return this.botName;
    }

    public int getNbVictories() {
        return nbVictories;
    }

    public int getNbDefeats() {
        return nbDefeats;
    }

    public int getNbdeTours() {
        return numberdeTours;
    }

    public int getNumberOfGames() {
        return numberdeParties;
    }

    public int getNumberDealParPartie(){
        return numberDealParPartie;
    }

    public int getDealMoveGardener(){
        return this.numberDealMoveGardener;
    }
    public int getDealMovePanda(){
        return this.numberDealMovePanda;
    }

    public int getDealPickObjective(){
        return this.numberDealPickObjectif;

    }
    public int getDealPutIrrigation(){
        return this.numberDealPutIrrigation;
    }

    public int getDealPutPlot(){
        return this.numberDealPutIrrigation;
    }

    public int getDealRain(){
        return this.numberDealRain;
    }

    public int getDealThunder(){
        return this.numberDealThunder;
    }

    public String toString() {
        return "number de victoire de  " + this.getBotName() + ": " + this.getNbVictories() + "\n"
                + "number de tours effectué : " + this.getNbdeTours() + " pour " + this.getNumberOfGames() + " parties \n"
                + "ce qui fait une moyenne de : " + this.getNbdeTours()/this.getNumberOfGames() + " \n" +
                this.getBotName() + " a effectué " + this.getNumberDealParPartie() + " Deals (actions)" + "Dont "
                + this.getDealMoveGardener() + " de Move Gardener" + ", " + this.getDealMovePanda() +" de Move Panda, "
                + this.getDealPickObjective() + " de Pick Objective, " + this.getDealPutIrrigation() + " de Put Irrigation"
                + this.getDealPutIrrigation() + " de Put Irrigation, " + this.getDealPutPlot() + " de Put Plot" +
                this.getDealRain() + " de GET Rain, " + this.getDealThunder() + " de Thudnder action !";

    }
}
