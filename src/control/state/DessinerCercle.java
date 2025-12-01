package control.state;
import java.awt.event.*;

import model.*;

import vue.VueDessin;

/**
 * Classe pour dessiner un rectangle
 */
public class DessinerCercle extends AbsEtatInteraction {
    private int x,y;
    private Dessin d;
    private VueDessin vd;
    /**
     * 
     * @param i L'interaction que l'on utilise
     * @param d Le dessin pour communiquer le cercle qu'on ajoute
     * @param vd La vue Dessin pour savoir avec quel composant on interagit 
     */
    public DessinerCercle(Interaction i ,Dessin d,VueDessin vd){
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
     * 
     */
    @Override
    public void mouseDragged(MouseEvent e){
        int previewX = Math.abs(x-e.getX());
        int previewY = Math.abs(y-e.getY());
        int radiusPreviewed = Math.max(previewX, previewY);
        //System.out.println("radius: " + radiusPreviewed);
        Cercle tmp = (Cercle)d.getFormeTmp();
        tmp.setWidth(radiusPreviewed*2);
        tmp.setX(x-radiusPreviewed);
        tmp.setY(y-radiusPreviewed);
        vd.repaint();
        //System.out.println("preview: " + radiusPreviewed);
        return;
    }
    /**
     * Enregistre la coordonnées x,y  et initialise la preview qui sera tout le temps update par mouseDragged. 
     */
    @Override
    public void mousePressed(MouseEvent e){
       
        x=e.getX();
        y=e.getY();
        d.setFormeTmp(new Cercle(x,y,0,0));
        return;
    }

    /**
     * Ajoute un cercle avec les coordonnées dans les formes de la classe Dessin
     * 
     * Puis change l'etat de l'interaction pour revenir sur celui par défaut
     */
    @Override
    public void mouseReleased(MouseEvent e){
        d.removeFormeTmp();//on arrete de prévisualiser
        /* if(e.getX()<x || e.getY()<y){
            interaction.setEtat(interaction.deplacer);
            return;
        } */
        int finalX = Math.abs(x-e.getX());
        int finalY = Math.abs(y-e.getY());
        int finalRadius = Math.max(finalX, finalY);
        //System.out.println("final: " + finalRadius);
        Forme f = new Cercle(x-finalRadius,y-finalRadius,finalRadius*2,finalRadius*2);
        d.addForme(f);
        //System.out.println("dernière pièce: "+d.getFormes().get(d.getFormes().size()-1));
        
        interaction.setEtat(interaction.deplacer);
        
        return;
    }

}