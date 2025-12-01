package control.state;
import model.*;
import vue.VueDessin;
import java.awt.event.*;

/**
 * Classe pour déplacer une forme
 */
public class Deplacer extends AbsEtatInteraction{
    private DetecterForme df;
    Forme forme;
    int posX,posY,oldx,oldy;
    private Dessin d;
    private VueDessin vd;
    /**
     * 
     * @param i L'interaction que l'on utilise
     * @param d Le dessin pour communiquer la forme qui a bougée
     * @param vd La vue Dessin pour savoir quel composant on interagit 
     */
    public Deplacer(Interaction i,Dessin d,VueDessin vd){
        super(i,"Mode_Deplacer");
        this.d=d;
        this.vd=vd;
        this.df = DetecterForme.getInstance(d);
    }
    
    @Override
    public void dessiner_Forme(){
        return;

    }
    /**
     * Change les coordonnées x,y en temps réel de la forme qu'on a séléctionné.
     * Cette forme est stocké dans la forme preview de dessin.
     */
    @Override
    public void mouseDragged(MouseEvent e){

        if(d.getFormeTmp()!=null){
            d.getFormeTmp().setX(e.getX()-posX);
            d.getFormeTmp().setY(e.getY()-posY);
            vd.repaint();
        }
        
        
        
        return;
    }
    /**
     * Permet de detecter si la souris passe pres d'une forme 
     * Elle prend en compte les coordonnée d'une forme avec une grosse marge correspondant aux hover de detecterForme
     * Cela mets donc la forme dans la liste des formes temporaire qui remplira la forme sur le paintComponent afin de l'indiquer
     */
    @Override
    public void mouseMoved(MouseEvent e){
        d.removeFormeTmp();
        this.forme = this.df.hover(e.getX(),e.getY());
        if(this.forme != null){
            d.setFormeTmp(this.forme);
        }
        vd.repaint();
       
        return;
    }
    /**
     * Permet de prendre en compte l'ancienne position d'une forme que l'on déplace , cela sert pour annuler un déplacement via le CommandHandler
     */
    @Override
    public void mousePressed(MouseEvent e){
        
        if(d.getFormeTmp()!=null){//on est sur une forme
            this.oldx = d.getFormeTmp().getX();
            this.oldy = d.getFormeTmp().getY();
            this.posX = e.getX()-d.getFormeTmp().getX();
            this.posY = e.getY()-d.getFormeTmp().getY();
            this.d.getFormes().remove(d.getFormeTmp());//ne déclanche aucun composite mais permet d'arreter de l'afficher
            
        }

        return;
    } 
    /**
     * Modifie la position d'une forme via le CommandHandler .
     */
    @Override
    public void mouseReleased(MouseEvent e){
        if(d.getFormeTmp() !=null){            
            d.getFormes().add(d.getFormeTmp());//ne déclanche aussi aucun composite
            d.deplacement(d.getFormeTmp(),e.getX()-posX,e.getY()-posY,this.oldx,this.oldy);
        }
        
        this.posX =0;
        this.posY =0;
        
        
        return;
    }

    
    
}