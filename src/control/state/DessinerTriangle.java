package control.state;

import java.awt.event.*;

import model.*;

import vue.VueDessin;

/**
 * Classe pour dessiner un Triangle 
 */
public class DessinerTriangle extends AbsEtatInteraction{
    private int x,y,width,length;
    private Dessin d;
    private VueDessin vd;
    /**
     * 
     * @param i L'interaction que l'on utilise
     * @param d Le dessin pour communiquer l'ajout du triangle
     * @param vd La vue Dessin pour savoir avec quel composant on interagit 
     */
    public DessinerTriangle(Interaction i ,Dessin d,VueDessin vd){
        super(i,"Mode_Dessin");
        this.d = d;
        this.vd = vd;
    }

    

    @Override
    public void dessiner_Forme() {
        return;
    }


    /**
     * Lorsque la souris est dragged , la forme temporaire qui correspond à la preview est tout le temps update avec les coordonnées de la souris.
     * Puis la vue est mise à jour afin d'update le dessin
     * Un nouveau triangle est fait car c'est plus simple que de changer la position de l'ancien Triangle.
     */
    @Override
    public void mouseDragged(MouseEvent e){
        int widthPreviewed = Math.abs(e.getX()-x);
        int heightPreviewed = Math.abs(e.getY()-y);
        int previewX = Math.min(e.getX(), x);
        int previewY = Math.min(e.getY(), y);
        
        Forme ftmp = new Triangle(previewX,previewY,widthPreviewed,heightPreviewed);
        d.setFormeTmp(ftmp);
        
        vd.repaint();
        //System.out.println(r.getWidth());
        //System.out.println(r.getHeight());
        return;
    }
    /**
     * Enregistre la coordonnées x,y et initialise la preview qui sera tout le temps update par mouseDragged.
     */
    @Override
    public void mousePressed(MouseEvent e){
       
        x=e.getX();
        y=e.getY();
        this.d.setFormeTmp(new Triangle(x,y,0,0));
        return;
    }
    /**
     * Ajoute un triangle avec les coordonnées dans les formes de la classe Dessin
     * La position x,y du triangle est seléctionné de maniere à ce que la longeur et la largeur du triangle soit positif.
     * Puis change l'etat de l'interaction pour revenir sur celui par défaut
     */
    @Override
    public void mouseReleased(MouseEvent e){
        this.d.removeFormeTmp();//on arrete de prévisualiser
        
        width = Math.abs(e.getX()-x);
        length = Math.abs(e.getY()-y);
        int finalX = Math.min(e.getX(), x);
        int finalY = Math.min(e.getY(), y);
        Forme f = new Triangle(finalX,finalY,width,length);
        this.d.addForme(f);
        //System.out.println("dernière pièce: "+d.getFormes().get(d.getFormes().size()-1));
        
        interaction.setEtat(interaction.deplacer);
        
        return;
    }

}