package control.state;

import model.*;
import vue.VueDessin;

import java.awt.event.*;

public class Interaction extends AbsModeleEcoutable implements MouseMotionListener,MouseListener{
    public final EtatInteraction dessiner;
    public final EtatInteraction effacer;
    public final EtatInteraction deplacer;
    public final EtatInteraction dessinerRect;
    public final EtatInteraction dessinerCercle;
    public final EtatInteraction DessinerTriangle;
    public final EtatInteraction DessinerAnyPolygone;
    public  EtatInteraction etatActuel;
    private Dessin d;
    /**
     * La classe Interaction il s'agit de la classe qui va utiliser les différentes méthodes presentes dans AbsEtatInteraction.
     * Et va appelé la methode pour l'etatInteraction Actuel , cela permet de changer les capacités de la souris.
     * @param vd Il s'agit de la vue Dessin sur la quel les EtatInteractions et donc les souris vont agir .
     */
    public Interaction(VueDessin vd){
        this.d = Dessin.getInstance();
        vd.addMouseListener(this);
        vd.addMouseMotionListener(this);

        dessiner = new Dessiner(this);
        effacer =  new Effacer(this, d,vd);
        deplacer = new Deplacer(this,d,vd);
        dessinerRect = new DessinerRectangle(this,d,vd);
        dessinerCercle = new DessinerCercle(this,d,vd);
        DessinerTriangle= new DessinerTriangle(this,d,vd);
        DessinerAnyPolygone = new DessinerAnyPolygone(this,d,vd);
        etatActuel = deplacer;
    }

    

    public void setEtat(EtatInteraction e){
        etatActuel = e;
        fireChange();
    }

    public void update(){
        fireChange();
    }

    public EtatInteraction getEtatActuel(){
        return this.etatActuel;
    }

    public Dessin getDessin(){
        return d;
    }
    /**
     * Permet de dire au dessinerAnyPolygone combien de coté il a besoin
     * @param nbCotes le nombre de cotés demandé
     */
    public void setNbCotesPoly(int nbCotes){
        DessinerAnyPolygone dap = (DessinerAnyPolygone)this.DessinerAnyPolygone;
        dap.setNbCotes(nbCotes);
    }
    /*
     * Correspond à l'action apres que le bouton dessiner_Forme est appuyé
     */
    public void dessiner_Forme(){
        etatActuel.dessiner_Forme();
    }
    /**
     * L'Extend du mouseAdapter n'est pas present dans Etat Interraction.
     * Il faut donc le cast sur tout les types pour rassurer et voir que la méthode est bien implémenter dans absEtatInterraction.
     * Action à faire quand un click de souris est utilisé
     */
    public void mouseClicked(MouseEvent e){
        
        if(etatActuel instanceof DessinerRectangle){
            DessinerRectangle dr = (DessinerRectangle)etatActuel;
            dr.mouseClicked(e);
        }else if (etatActuel instanceof DessinerCercle){
            DessinerCercle dc = (DessinerCercle)etatActuel;
            dc.mouseClicked(e);
        }else if (etatActuel instanceof DessinerTriangle){
            DessinerTriangle dt = (DessinerTriangle)etatActuel;
            dt.mouseClicked(e);
        }else if (etatActuel instanceof DessinerAnyPolygone){
            DessinerAnyPolygone dp = (DessinerAnyPolygone)etatActuel;
            dp.mouseClicked(e);
        }else if(etatActuel instanceof Dessiner){
            Dessiner de = (Dessiner)etatActuel;
            de.mouseClicked(e);
        }else if(etatActuel instanceof Effacer){
            Effacer ef = (Effacer)etatActuel;
            ef.mouseClicked(e);
        }else if(etatActuel instanceof Deplacer){
            Deplacer dep = (Deplacer)etatActuel;
            dep.mouseClicked(e);
        }
    }

    /**
     * Action à faire quand la souris rentre dans le composant 
     * Pas utilisé
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }
    /**
     * Action à faire quand la souris sort du composant 
     * Pas utilisé
     */
    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
    /**
     * Action à faire quand le click gauche est pressé
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
        if(etatActuel instanceof DessinerRectangle){
            DessinerRectangle dr = (DessinerRectangle)etatActuel;
            dr.mousePressed(e);
        }else if(etatActuel instanceof DessinerCercle){
            DessinerCercle dc = (DessinerCercle)etatActuel;
            dc.mousePressed(e);
        }else if(etatActuel instanceof DessinerTriangle){
            DessinerTriangle dt = (DessinerTriangle)etatActuel;
            dt.mousePressed(e);
        }else if (etatActuel instanceof DessinerAnyPolygone){
            DessinerAnyPolygone dp = (DessinerAnyPolygone)etatActuel;
            dp.mousePressed(e);
        }else if(etatActuel instanceof Dessiner){
            Dessiner de = (Dessiner)etatActuel;
            de.mousePressed(e);
        }else if(etatActuel instanceof Effacer){
            Effacer ef = (Effacer)etatActuel;
            ef.mousePressed(e);
        }else if(etatActuel instanceof Deplacer){
            Deplacer dep = (Deplacer)etatActuel;
            dep.mousePressed(e);
        }
    }
    /**
     * Action à faire quand le click gauche est relaché
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        
        if(etatActuel instanceof DessinerRectangle){
            DessinerRectangle dr = (DessinerRectangle)etatActuel;
            dr.mouseReleased(e);
        }else if(etatActuel instanceof DessinerCercle){
            DessinerCercle dc = (DessinerCercle)etatActuel;
            dc.mouseReleased(e);
        }else if(etatActuel instanceof DessinerTriangle){
            DessinerTriangle dt = (DessinerTriangle)etatActuel;
            dt.mouseReleased(e);
        }else if (etatActuel instanceof DessinerAnyPolygone){
            DessinerAnyPolygone dp = (DessinerAnyPolygone)etatActuel;
            dp.mouseReleased(e);
        }else if(etatActuel instanceof Dessiner){
            Dessiner de = (Dessiner)etatActuel;
            de.mouseReleased(e);
        }else if(etatActuel instanceof Effacer){
            Effacer ef = (Effacer)etatActuel;
            ef.mouseReleased(e);
        }else if(etatActuel instanceof Deplacer){
            Deplacer dep = (Deplacer)etatActuel;
            dep.mouseReleased(e);
        }
    }
    /**
     * Action à faire quand on fait du drag & drop
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(etatActuel instanceof DessinerRectangle){
            DessinerRectangle dr = (DessinerRectangle)etatActuel;
            dr.mouseDragged(e);
        }else if(etatActuel instanceof DessinerCercle){
            DessinerCercle dc = (DessinerCercle)etatActuel;
            dc.mouseDragged(e);
        }else if(etatActuel instanceof DessinerTriangle){
            DessinerTriangle dt = (DessinerTriangle)etatActuel;
            dt.mouseDragged(e);
        }else if (etatActuel instanceof DessinerAnyPolygone){
            DessinerAnyPolygone dp = (DessinerAnyPolygone)etatActuel;
            dp.mouseDragged(e);
        }else if(etatActuel instanceof Dessiner){
            Dessiner de = (Dessiner)etatActuel;
            de.mouseDragged(e);
        }else if(etatActuel instanceof Effacer){
            Effacer ef = (Effacer)etatActuel;
            ef.mouseDragged(e);
        }else if(etatActuel instanceof Deplacer){
            Deplacer dep = (Deplacer)etatActuel;
            dep.mouseDragged(e);
        }
    }
    /**
     * Action à faire quand la souris se déplace sur le composant
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if(etatActuel instanceof DessinerRectangle){
            DessinerRectangle dr = (DessinerRectangle)etatActuel;
            dr.mouseMoved(e);
        }else if(etatActuel instanceof DessinerCercle){
            DessinerCercle dc = (DessinerCercle)etatActuel;
            dc.mouseMoved(e);
        }else if(etatActuel instanceof DessinerTriangle){
            DessinerTriangle dt = (DessinerTriangle)etatActuel;
            dt.mouseMoved(e);
        }else if (etatActuel instanceof DessinerAnyPolygone){
            DessinerAnyPolygone dp = (DessinerAnyPolygone)etatActuel;
            dp.mouseMoved(e);
        }else if(etatActuel instanceof Dessiner){
            Dessiner de = (Dessiner)etatActuel;
            de.mouseMoved(e);
        }else if(etatActuel instanceof Effacer){
            Effacer ef = (Effacer)etatActuel;
            ef.mouseMoved(e);
        }else if(etatActuel instanceof Deplacer){
            Deplacer dep = (Deplacer)etatActuel;
            dep.mouseMoved(e);
        }
    }
}