package control.maillon;


public class Jeu implements Runnable{

    private MaillonJeu tete;
    Boolean isRunning = true;
    int joueurEnCours = 0;
    int tourActuel = 0;
    int etatSuivant;                                                                                       
    int nbTours = 10; 
    double scoreA = 0;
    double scoreB = 0;

    public Jeu(){
    }

    public void setBoolean(Boolean isRunning){
        this.isRunning = isRunning;

    }

    public void setJoueurEnCours(int j){
        this.joueurEnCours = j;
    }

    public int getJoueurEnCours(){
        return this.joueurEnCours;
    }

    public void demarrer(MaillonJeu maillon){
        this.tete = maillon;
        new Thread(this).start();
    }

    public void actionJoueur(String action){
        System.out.println("action en cours:"+action);
    }

    public void setTour(int t){
        this.tourActuel = t;
    }

    public void setNbTours(int nbT){
        this.nbTours = nbT;
    }

    public int getTourActuel(){
        return this.tourActuel;
    }

    public int getNbTours(){
        return this.nbTours;
    }

    public void addScoreA(double score){
        this.scoreA += score;
    }

    public void addScoreB(double score){
        this.scoreB += score;
    }

    public void resetScores(){
        this.scoreA=0;
        this.scoreB=0;
    }

    public double getScoreA(){
        return this.scoreA;
    }

    public double getScoreB(){
        return this.scoreB;
    }

    @Override
    public synchronized void run(){
        while(isRunning){
            System.out.println("Démarrage du jeu");
            tete.action(this);
        }
    }

}
