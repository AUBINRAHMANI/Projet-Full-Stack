package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

public class BotStatisticProfile {

    String botName;
    Playable bot;
    private int nbVictories;
    private int nbDefeats;
    private int nbOfRounds;
    private int nbOfGame;
    private int nbOfActionPerGame;

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
        this.nbOfActionPerGame = 0;
        this.numberDealMoveGardener = 0;
        this.numberDealMovePanda = 0;
        this.numberDealPickObjective = 0;
        this.numberDealPutIrrigation = 0;
        this.numberDealPutPlot = 0;
        this.numberDealRain = 0;
        this.numberDealThunder = 0;
        this.numberObjectiveGardener = 0;
        this.numberObjectivePanda = 0;
        this.numberObjectivePlots = 0;
        this.numberPointsObjectiveGardener = 0;
        this.numberPointsObjectivePanda = 0;
        this.numberPointsObjectivePlot = 0;
        this.numberTotalPoints = 0;
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

    public void addNumberDealPerGames() {
        ++nbOfActionPerGame;
    }

    public void addDealMoveGardener() {
        ++this.numberDealMoveGardener;
    }

    public void addDealMovePanda() {
        ++this.numberDealMovePanda;
    }

    public void addDealPickObjective() {
        ++this.numberDealPickObjective;

    }

    public void addDealPutIrrigation() {
        ++this.numberDealPutIrrigation;
    }

    public void addDealPutPlot() {
        ++this.numberDealPutPlot;
    }

    public void addDealRain() {
        ++this.numberDealRain;
    }

    public void addDealThunder() {
        ++this.numberDealThunder;
    }

    public void addObjectiveGardener() {
        ++this.numberObjectiveGardener;
    }

    public void addObjectivePanda() {
        ++this.numberObjectivePanda;
    }

    public void addObjectivePlots() {
        ++this.numberObjectivePlots;
    }

    public void addNumberPointsObjectiveGardener(int points) {
        this.numberPointsObjectiveGardener += points;
    }

    public void addNumberPointsObjectivePanda(int points) {
        this.numberPointsObjectivePanda += points;
    }

    public void addNumberPointsObjectivePlot(int points) {
        this.numberPointsObjectivePlot += points;
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

    public int getNbOfActionsPerGame() {
        return nbOfActionPerGame;
    }

    public int getDealMoveGardener() {
        return this.numberDealMoveGardener;
    }

    public int getDealMovePanda() {
        return this.numberDealMovePanda;
    }

    public int getDealPickObjective() {
        return this.numberDealPickObjective;

    }

    public int getDealPutIrrigation() {
        return this.numberDealPutIrrigation;
    }

    public int getDealPutPlot() {
        return this.numberDealPutPlot;
    }

    public int getDealRain() {
        return this.numberDealRain;
    }

    public int getDealThunder() {
        return this.numberDealThunder;
    }

    public int getNumberObjectiveGardener() {
        return this.numberObjectiveGardener;
    }

    public int getNumberObjectivePanda() {
        return this.numberObjectivePanda;
    }

    public int getNumberObjectivePlots() {
        return this.numberObjectivePlots;
    }

    public int getNumberPointsObjectiveGardener() {
        return this.numberPointsObjectiveGardener;
    }

    public int getNumberPointsObjectivePanda() {
        return this.numberPointsObjectivePanda;
    }

    public int getNumberPointsObjectivePlot() {
        return this.numberPointsObjectivePlot;
    }

    public int getNumberTotalPoints() {
        this.numberTotalPoints = this.numberPointsObjectiveGardener + this.numberPointsObjectivePanda + this.numberPointsObjectivePlot;
        return numberTotalPoints;
    }

    public float percentageWin() {
        float percentage;
        percentage = ((float) this.getNbVictories() * 100) / this.getNbOfGames();
        return percentage;
    }

    public float percentageDefeat() {
        float percentage;
        percentage = ((float) (this.getNbDefeats() * 100) / this.getNbOfGames());
        return percentage;
    }

    public float scoreAverage() {
        float average;
        average = (float) this.getNumberTotalPoints() / this.getNbOfGames();
        return average;

    }

    public String toString() {
        return "Number of victories : " + this.getBotName() + ": " + this.getNbVictories() + " for " + this.getNbOfGames() + " parties. i.e. " + this.percentageWin() + "% of victories. \n"
                + "Number of defeats : " + this.getNbDefeats() + " for " + this.getNbOfGames() + " Games. i.e. " + this.percentageDefeat() + " % of defeats." +
                " \n The average score of " + this.getBotName() + " is equal to  : " + String.format("%.2f", this.scoreAverage()) + " ! He got in total " + this.getNumberTotalPoints() + " points ! \n";
    }


}
