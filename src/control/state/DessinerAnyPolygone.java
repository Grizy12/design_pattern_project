package control.state;

import java.awt.event.*;

import model.*;

import vue.VueDessin;
/**
 * Classe pour dessiner un Polygone avec n'importe quel nombre de coté mais on limite sur le jeux de 5-10 cotés
 */
public class DessinerAnyPolygone extends AbsEtatInteraction{
    private int x,y,width,nbCotes;
    private Dessin d;
    private VueDessin vd;
    /**
     * 
     * @param i L'interaction que l'on utilise
     * @param d Le dessin pour communiquer la forme qui va être ajoutée
     * @param vd La vue Dessin pour savoir quel composant on interagit 
     */
    public DessinerAnyPolygone(Interaction i ,Dessin d,VueDessin vd){
        super(i,"Mode_Dessin");
        this.d = d;
        this.vd = vd;
        nbCotes=5;
    }

    public void setNbCotes(int n){
        nbCotes=n;
    }

    

    @Override
    public void dessiner_Forme() {
        return;
    }

    /**
     * Lorsque la souris est dragged , la forme temporaire qui correspond à la preview est tout le temps update avec les coordonnées de la souris.
     * Puis la vue est mise à jour afin d'update le dessin
     * Ici on recree un polygone et on s'assure que le width est tout le temps positif
     */
    @Override
    public void mouseDragged(MouseEvent e){
        int widthPreviewed = Math.max(Math.abs(e.getX()-x),Math.abs(e.getY()-y));
        int previewX = Math.min(e.getX(), x);
        int previewY = Math.min(e.getY(), y);
        //System.out.println(previewX+","+previewY+","+widthPreviewed+","+nbCotes);
        Forme ftmp = new AnyPolygone(previewX,previewY,widthPreviewed,nbCotes);
        d.setFormeTmp(ftmp);
        
        vd.repaint();

        return;
    }
    /**
     * Enregistre la coordonnées x,y et initialise la preview qui sera tout le temps update par mouseDragged.
     */
    @Override
    public void mousePressed(MouseEvent e){
       
        x=e.getX();
        y=e.getY();
        this.d.setFormeTmp(new AnyPolygone(x,y,0,nbCotes));
        return;
    }
    /**
     * Ajoute un polygone avec les coordonnées dans les formes de la classe Dessin
     * La position x,y du polygone est seléctionné de maniere à ce que la longeur soit positif.
     * Puis change l'etat de l'interaction pour revenir sur celui par défaut
     */
    @Override
    public void mouseReleased(MouseEvent e){
        this.d.removeFormeTmp();//on arrete de prévisualiser
        
        width = Math.max(Math.abs(e.getX()-x),Math.abs(e.getY()-y));
        int finalX = Math.min(e.getX(), x);
        int finalY = Math.min(e.getY(), y);
        Forme f = new AnyPolygone(finalX,finalY,width,nbCotes);
        this.d.addForme(f);
        //System.out.println("dernière pièce: "+d.getFormes().get(d.getFormes().size()-1));
        
        interaction.setEtat(interaction.deplacer);
        
        return;
    }

}