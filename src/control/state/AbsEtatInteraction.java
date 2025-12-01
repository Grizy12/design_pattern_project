package control.state;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

/**
 * Classe Abstraite qui permet de regrouper et les méthodes de la souris (mouseAdapter) et les autres actions d'EtatInteraction
 */
public abstract class AbsEtatInteraction extends MouseAdapter implements EtatInteraction{
    protected Interaction interaction;
    private String etat;
    /**
     * 
     * @param interaction 
     * @param etat
     */
    public AbsEtatInteraction(Interaction interaction,String etat){
        this.interaction = interaction;
        this.etat = etat;
    }

    public String getEtatActuel(){
        return this.etat;

    }

    //Ici je mets les fonctions qui ne seront jamais utilisé par la souris
    @Override
    public void mouseClicked(MouseEvent e){
        
        return;
    } 
    @Override
    public void mouseEntered(MouseEvent e){
        return;
    }
    
    @Override 
    public void mouseExited(MouseEvent e){
        return;
    }

    @Override
    public void mousePressed(MouseEvent e){
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        return;
    }
    @Override 
    public void mouseWheelMoved(MouseWheelEvent e){
        return;
    }
    @Override
    public void mouseMoved(MouseEvent e){
        return;
    }
    @Override 
    public void mouseDragged(MouseEvent e){
        return;
    }

}