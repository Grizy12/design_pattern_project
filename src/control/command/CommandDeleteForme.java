package control.command;

import model.Dessin;
import model.Forme;
/**
 * classe qui contient le retrait et son inverse
 */
public class CommandDeleteForme implements OperationCommand {

    private Dessin d;
    private Forme f;
    /**
     * 
     * @param d Le dessin ou sont stocké les formes
     * @param f La forme que l'on souhaite ajouté ou retiré
     */
    public CommandDeleteForme(Dessin d,Forme f){
        this.d=d;
        this.f=f;
    }
    /**
     * Retire une forme et mets à jour la vue via d.update()
     */
    @Override
    public void operate() {
        d.getFormes().remove(f);
        d.update();
    }
    /**
     * Inverse d'un Retrait : on ajoute une forme et on mets à jour la vue
     */
    @Override
    public void compensate() {
        d.getFormes().add(f);
        d.update();
    }
    
}
