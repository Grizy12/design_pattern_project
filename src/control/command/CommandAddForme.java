package control.command;
import model.Dessin;
import model.Forme;
/**
 * classe qui contient l'ajout et son inverse
 */
public class CommandAddForme implements OperationCommand{
    private Dessin d;
    private Forme f;
    /**
     * 
     * @param d Le dessin ou sont stocké les formes
     * @param f La forme que l'on souhaite ajouté ou retiré
     */
    public CommandAddForme(Dessin d,Forme f){
        this.d=d;
        this.f=f;
    }
    /**
     * Ajoute une forme et mets à jour la vue via d.update()
     */
    @Override
    public void operate() {

        d.getFormes().add(f);
        d.update();
        
    }
    /**
     * Inverse d'un ajout : on retire une forme et on mets à jour la vue
     */
    @Override
    public void compensate() {
        d.getFormes().remove(f);
        d.update();
        
    }
    
}
