package control.maillon;

public abstract class MaillonJeu{
    private MaillonJeu suivant;

    public MaillonJeu(MaillonJeu suivant){
        this.suivant = suivant;
    }

    public MaillonJeu(){
        this(null);
    }

    public void setSuivant(MaillonJeu suivant){
        this.suivant = suivant;
    }

    final public void action(Jeu jeu){
        actionPropre(jeu);
        if(suivant!=null){
            suivant.action(jeu);
        }
    }

    public abstract void actionPropre(Jeu jeu);
}