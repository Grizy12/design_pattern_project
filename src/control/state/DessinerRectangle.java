package control.state;
import java.awt.event.*;

import model.*;

import vue.VueDessin;

/**
 * Classe pour dessiner un rectangle
 */
public class DessinerRectangle extends AbsEtatInteraction{
    private int x,y,width,length;
    private Dessin d;
    private VueDessin vd;
    /**
     * 
     * @param i L'interaction que l'on utilise
     * @param d Le dessin pour communiquer le rectangle qui est ajouté
     * @param vd La vue Dessin pour savoir avec quel composant on interagit 
     */
    public DessinerRectangle(Interaction i ,Dessin d,VueDessin vd){
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
     * Ici on utilise des setters , mais ces derniers vont modifié la position en faisant un "shift" afin que les points ne soit pas décallés
     */
    @Override
    public void mouseDragged(MouseEvent e){
        int widthPreviewed = Math.abs(e.getX()-x);
        int heightPreviewed = Math.abs(e.getY()-y);
        int previewX = Math.min(e.getX(), x);
        int previewY = Math.min(e.getY(), y);
        Rectangle tmp = (Rectangle)d.getFormeTmp();
        tmp.setX(previewX);
        tmp.setY(previewY);
        tmp.setWidth(widthPreviewed);
        tmp.setHeight(heightPreviewed);
        
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
        this.d.setFormeTmp(new Rectangle(x,y,0,0));
        return;
    }
    /**
     * Ajoute un rectangle avec les coordonnées dans les formes de la classe Dessin
     * La position x,y du rectangle est seléctionné de maniere à ce que la longeur et la largeur du rectangle soit positif.
     * Puis change l'etat de l'interaction pour revenir sur celui par défaut
     */
    @Override
    public void mouseReleased(MouseEvent e){
        this.d.removeFormeTmp();//on arrete de prévisualiser
        
        width = Math.abs(e.getX()-x);
        length = Math.abs(e.getY()-y);
        int finalX = Math.min(e.getX(), x);
        int finalY = Math.min(e.getY(), y);
        Forme f = new Rectangle(finalX,finalY,width,length);
        this.d.addForme(f);
        //System.out.println("dernière pièce: "+d.getFormes().get(d.getFormes().size()-1));
        
        interaction.setEtat(interaction.deplacer);
        
        return;
    }

}