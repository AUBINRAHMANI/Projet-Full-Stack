package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

public class BotStatisticProfile {

    String botName;
    Playable bot;
    private int nbVictories;
    private int nbDefeats;
    private int nbOfRounds;
    private int nbOfGame;
    private int numberDealParPartie;

    private int numberDealMoveGardener;

    private int numberDealMovePanda;

    private int numberDealPickObjective;

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

    private int numberTotalPoints;

    public BotStatisticProfile(Playable bot, String botName) {

        this.bot = bot;
        this.botName = botName;
        this.nbVictories = 0;
        this.nbDefeats = 0;
        this.nbOfRounds = 0;
        this.nbOfGame = 0;
        this.numberDealParPartie=0;
        this.numberDealMoveGardener=0;
        this.numberDealMovePanda=0;
        this.numberDealPickObjective =0;
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
        this.numberTotalPoints=0;
    }

    public void addVictory() {
        nbVictories += 1;
    }

    public void addDefeat() {
        nbDefeats += 1;
    }
    public void addNbOfRounds() {
        ++nbOfRounds;
    }

    public void addNumberOfGames() {
        ++nbOfGame;
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
        ++this.numberDealPickObjective;

    }
    public void addDealPutIrrigation(){
        ++this.numberDealPutIrrigation;
    }

    public void addDealPutPlot(){
        ++this.numberDealPutPlot;
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

    public void addNumberPointsObjectiveGardener(int points){
        this.numberPointsObjectiveGardener+=points;
    }

    public void addNumberPointsObjectivePanda(int points){
        this.numberPointsObjectivePanda+=points;
    }

    public void addNumberPointsObjectivePlot(int points){
        this.numberPointsObjectivePlot+=points;
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
    public int getNbOfRounds() {
        return nbOfRounds;
    }

    public int getNbOfGames() {
        return nbOfGame;
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
        return this.numberDealPickObjective;

    }
    public int getDealPutIrrigation(){
        return this.numberDealPutIrrigation;
    }

    public int getDealPutPlot(){
        return this.numberDealPutPlot;
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

    public int getNumberObjectivePlots(){
        return this.numberObjectivePlots;
    }

    public int getNumberPointsObjectiveGardener(){
        return this.numberPointsObjectiveGardener;
    }

    public int getNumberPointsObjectivePanda(){
        return this.numberPointsObjectivePanda;
    }

    public int getNumberPointsObjectivePlot(){
        return this.numberPointsObjectivePlot;
    }

    public int getNumberTotalPoints(){
        this.numberTotalPoints = this.numberPointsObjectiveGardener + this.numberPointsObjectivePanda + this.numberPointsObjectivePlot;
        return numberTotalPoints;
    }

    public void resetNumberPointsObjective(){
        this.numberPointsObjectivePlot=0;
        this.numberPointsObjectivePanda=0;
        this.numberPointsObjectiveGardener=0;

    }

    public float pourcentageWin(){
       float pourcentage=0;
       pourcentage = ((float)this.getNbVictories() * 100)/(float)this.getNbOfGames();
       return pourcentage;
    }

    public float pourcentageDefeat(){
        float pourcentage =0;
        pourcentage = ((float)(this.getNbDefeats()*100)/(float) this.getNbOfGames());
        return pourcentage;
    }

    public float scoreAverage(){
        float average =0;
        average = (float)this.getNumberTotalPoints() / (float)this.getNbOfGames();
        return average;

    }
/* Pour le csv et des statistiques plus poussés

    public String toString() {
        return "Number of wins  " + this.getBotName() + ": " + this.getNbVictories() + "\n"
                + "Number of rounds done : " + this.getNbOfRounds() + " for " + this.getNbOfGames() + " games \n"
                + "ce qui fait une moyenne de : " + this.getNbOfRounds()/this.getNbOfGames() + " tours" + " \n" +
                this.getBotName() + " a effectué " + this.getNumberDealParPartie() + " actions. Parmis ces actions il a choisi l'action de deplacer le jardinier  "
                + this.getDealMoveGardener() + " fois" + ", l'action de deplacer le panda " + this.getDealMovePanda() +" fois, "
                + "l'action de prendre un objectif " + this.getDealPickObjective() + " fois, l'action de poser une irrigation " + this.getDealPutIrrigation()
                + " fois, l'action de poser une parcelle " + this.getDealPutPlot() + " fois, l'action de chosisir la pluie " +
                this.getDealRain() + " fois , et enfin l'action de choisir le tonnerre " + this.getDealThunder() + " fois \n"
                + this.getBotName() + " a completé " + this.getNumberObjectiveGardener() + " objectifs gardiens, ce qui lui a donné : "
                + this.getNumberPointsObjectiveGardener() + " points, " + this.getNumberObjectivePanda() + " objectifs Panda ce qui lui a donné : "
                +this.getNumberPointsObjectivePanda() + " points, et il a completé "  + this.getNumberObjectivePlots() + " objectifs Plot ce qui lui a donné : "
                + this.getNumberPointsObjectivePlot() + " points ! \n";
    }
 */
    public String toString(){
        return "Nombre de victoire de  " + this.getBotName() + ": " + this.getNbVictories() + " pour  " + this.getNbOfGames() + " parties. Soit " + this.pourcentageWin() + "% de victoire. \n"
                + "Nombre de defaite : " + this.getNbDefeats() + " pour " + this.getNbOfGames() + " parties. Soit " + this.pourcentageDefeat() + " % de defaites." +
                " \n Le score moyen de points de " + this.getBotName() + " est de  : " + String.format("%.2f",this.scoreAverage()) + " ! Il a obtenu en tout " + this.getNumberTotalPoints() + " points ! \n";
    }


}
