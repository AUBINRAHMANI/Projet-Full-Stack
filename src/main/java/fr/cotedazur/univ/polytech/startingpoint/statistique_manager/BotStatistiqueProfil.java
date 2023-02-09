package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

public class BotStatistiqueProfil {

    String botName;
    Playable bot;
    private int nbVictories;
    private int nbDefeats;

    private int numberOfRounds;
    private int numberdeParties;

    private int numberDealParPartie;

    private int numberDealMoveGardener;

    private int numberDealMovePanda;

    private int numberDealPickObjectif;

    private int numberDealPutIrrigation;

    private int numberDealPutPlot;

    private int numberDealRain;

    private int numberDealThunder;

    private int numberObjectiveGardener;

    private int numberObjectivePanda;

    private int numberObjectivePlots;

    private int numberPointsObjectiveGardener;

    private int numberPointsObjectivePanda;

    private int numberPointsObjectivePlot;




    public BotStatistiqueProfil(Playable bot, String botName) {
        this.bot = bot;
        this.botName = botName;
        this.nbVictories = 0;
        this.nbDefeats = 0;
        this.numberOfRounds = 0;
        this.numberdeParties = 0;
        this.numberDealParPartie=0;
        this.numberDealMoveGardener=0;
        this.numberDealMovePanda=0;
        this.numberDealPickObjectif=0;
        this.numberDealPutIrrigation=0;
        this.numberDealPutPlot=0;
        this.numberDealRain=0;
        this.numberDealThunder=0;
        this.numberObjectiveGardener=0;
        this.numberObjectivePanda=0;
        this.numberObjectivePlots=0;
        this.numberPointsObjectiveGardener=0;
        this.numberPointsObjectivePanda=0;
        this.numberPointsObjectivePlot=0;
    }

    public void addVictory() {
        nbVictories += 1;
    }

    public void addDefeat() {
        nbDefeats += 1;
    }

    public void addnumberOfRounds() {
        ++numberOfRounds;
    }

    public void addNumberOfGames() {
        ++numberdeParties;
    }
    public void addNumberDealPerGames(){
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

    public void addObjectiveGardener(){
        ++this.numberObjectiveGardener;
    }

    public void addObjectivePanda(){
        ++this.numberObjectivePanda;
    }

    public void addObjectivePlots(){
        ++this.numberObjectivePlots;
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
        return numberOfRounds;
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

    public int getNumberObjectiveGardener(){
        return this.numberObjectiveGardener;
    }

    public int getNumberObjectivePanda(){
        return this.numberObjectivePanda;
    }

    public int getNumberObjectifPlots(){
        return this.numberObjectivePlots;
    }

    public String toString() {
        return "Nombre de victoire de  " + this.getBotName() + ": " + this.getNbVictories() + "\n"
                + "Nombre de tours effectué : " + this.getNbdeTours() + " pour " + this.getNumberOfGames() + " parties \n"
                + "ce qui fait une moyenne de : " + this.getNbdeTours()/this.getNumberOfGames() + " tours" + " \n" +
                this.getBotName() + " a effectué " + this.getNumberDealParPartie() + " actions. Parmis ces actions il a choisi l'action de deplacer le jardinier  "
                + this.getDealMoveGardener() + " fois" + ", l'action de deplacer le panda " + this.getDealMovePanda() +" fois, "
                + "l'action de prendre un objectif " + this.getDealPickObjective() + " fois, l'action de poser une irrigation " + this.getDealPutIrrigation()
                + " fois, l'action de poser une parcelle " + this.getDealPutPlot() + " fois, l'action de chosisir la pluie " +
                this.getDealRain() + " fois , et enfin l'action de choisir le tonnerre " + this.getDealThunder() + " fois \n"
                + this.getBotName() + " a completé " + this.getNumberObjectiveGardener() + " objectifs gardiens, "
                + this.getNumberObjectivePanda() + " objectifs Panda, et " + this.getNumberObjectifPlots() + " objectifs Plot";

    }
}
