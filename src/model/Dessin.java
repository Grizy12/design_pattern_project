package model;

import java.util.*;

import control.command.CommandAddForme;
import control.command.CommandDeleteForme;
import control.command.CommandHandler;
import control.command.CommandMoveForme;


public class Dessin extends AbsModeleEcoutable {
    static private Dessin instance =null;
    ArrayList<Forme> formes = new ArrayList<Forme>();
    private ArrayList<Forme> formesAReproduire = new ArrayList<Forme>();
    Forme formeTmp;//utilisé pour montrer la preview d'une piece
    CommandHandler c ;

    private Dessin(){
          this.c = CommandHandler.getInstance();
    }

    public static Dessin getInstance(){
        if (instance ==null){
            instance = new Dessin();
        }
        return instance;
    }

    public void addForme(Forme f){
        //formes.add(f);
        System.out.println("Forme ajoutée: " + f);
        c.handle(new CommandAddForme(this,f));
        this.fireChange();
    }

    public void removeForme(Forme f){
        c.handle(new CommandDeleteForme(this,f));
        this.fireChange();
    }

    public void loadFormesAReproduire(){
        for(Forme f : this.getFormes()){
            this.addFormeAReproduire(f);
        }
        this.clearFormes();
    }


    public void deplacement(Forme f,int newX,int newY,int oldx,int oldy){
        c.handle(new CommandMoveForme(this, f,newX,newY,oldx,oldy));
        this.fireChange();

    }

    public void addFormeAReproduire(Forme f){
        this.formesAReproduire.add(f);
        this.fireChange();
        //System.out.println("ajouté forme " + f + "pos x:" + f.getX() +", y: " +  f.getY());
    }

    

    public void setFormeTmp(Forme f){
        this.formeTmp = f;
        this.fireChange();
    }

    public void removeFormeTmp(){
        this.formeTmp = null; 
    }
    //pour effacer les formes du joueur en fin de partie
    public void clearFormes(){
        this.formes.clear();
    }
    //pour effacer les formes initiales
    public void clearFormesAReproduire(){
        this.formesAReproduire.clear();
    }

    public Forme getFormeTmp(){
        return this.formeTmp;
    }

    public ArrayList<Forme> getFormes(){
        return this.formes;
    }

    public ArrayList<Forme> getFormesAReproduire(){
        return this.formesAReproduire;
    }

    public void update(){
        this.fireChange();
    }

    

    //implémenter méthodes pour les écouteurs
    

}