package control.state;
import model.Dessin;
import model.Forme;
import vue.VueDessin;
import java.awt.event.*;

/**
 * Etat Interaction quand il faut supprimer une forme
 */
public class Effacer extends AbsEtatInteraction{
    private Dessin d;
    private DetecterForme df;//a changer en singleton
    private Forme formeAEffacer;
    private VueDessin vd;
    private Forme forme;
    /**
     * 
     * @param i L'interaction que l'on utilise
     * @param d Le dessin pour communiquer la forme qui doit etre retirée
     * @param vd La vue Dessin pour savoir avec quel composant on interagit 
     */
    public Effacer(Interaction i, Dessin d,VueDessin vd){
        super(i,"Mode_Effacer");
        this.d = d;
        this.vd = vd;
        this.df = DetecterForme.getInstance(d);
    }

    
    @Override
    public void dessiner_Forme(){
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
     * Efface la forme qui à été détecter par le df -> detecterForme.java
     * La forme est retiré de la liste des formes dans dessin via le commandHandler
     * L'état n'est pas modifié il est donc possible d'effacer plusieurs formes d'affilé
     */
    @Override
    public void mouseClicked(MouseEvent e){
        //System.out.println(e.getSource());
        
        this.formeAEffacer = this.df.hover(e.getX(), e.getY());
        if(this.formeAEffacer != null){
            this.d.removeForme(this.formeAEffacer);
        }
        //interaction.setEtat(interaction.deplacer);
        return;
    } 
    
    
    
}