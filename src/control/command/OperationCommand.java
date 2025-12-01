package control.command;


/**
 * Interface avec les 2 Opérations
 */
public interface OperationCommand {
    /**
     * Applique une action
     */
    void operate();
    /**
     * Applique l'inverse d'une action précedente
     */
    void compensate();
}
