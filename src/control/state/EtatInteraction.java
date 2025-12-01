package control.state;

/**
 * Interface qui contient toutes les methodes hors de ceux contenu dans le mouseAdapter
 */
public interface EtatInteraction{
    /**
     * Methode quand il faut choisir une forme à dessiner
     */
    void dessiner_Forme();
    /**
     * 
     * @return L'etat actuel de l'interaction
     */
    String getEtatActuel();


}