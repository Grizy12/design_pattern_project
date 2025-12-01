package control.command;

import model.Dessin;
import model.Forme;

/**
 * classe qui contient le déplacement et son inverse
 */
public class CommandMoveForme implements OperationCommand{
    private Dessin d;
    private Forme f;
    private int newX,newY,oldx,oldy;
    /**
     * 
     * @param d Le dessin ou sont stocké les formes
     * @param f La forme que l'on traite
     * @param newX La nouvelle position X
     * @param newY La nouvelle position Y 
     * @param oldx L'ancienne Position X
     * @param oldy L'ancienne Position Y
     */
    public CommandMoveForme(Dessin d,Forme f,int newX, int newY,int oldx,int oldy){
        this.d=d;
        this.f=f;
        this.newX = newX;
        this.newY = newY;
        this.oldx=oldx;
        this.oldy=oldy;
    }
    /**
     * Modifie la position de la forme avec les nouvelles position x,y
     * Dans le cas ou la forme est un polygone on "shift" la position de tout les points de la forme
     */
    @Override
    public void operate() {
        f.setX(newX);
        f.setY(newY);
        d.update();
        
        return ;
    }
    /**
     * Modifie la position de la forme avec les anciennes position x,y
     * Dans le cas ou la forme est un polygone on "shift" la position de tout les points de la forme
     */
    @Override
    public void compensate() {
        f.setX(oldx);
        f.setY(oldy);
        d.update();
        
    }

}