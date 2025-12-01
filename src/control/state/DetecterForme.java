package control.state;



import model.Forme;


import model.Dessin;
/**
 * Une classe pour detecter si la souris est sur une forme
 */
public class DetecterForme {
    private final static int BUFFER = 4;
    private Dessin dessin;
    static private DetecterForme instance =null;
    private DetecterForme(Dessin dessin){
        this.dessin = dessin;
    }

    public static DetecterForme getInstance(Dessin d){
        if(instance==null){
            instance = new DetecterForme(d);
        }
        return instance;
    }
    
    /**
     * 
     * @param mx Position x de la souris
     * @param my Position y de la souris
     * @return renvoie la forme qui est situé au position mx et my en prenant compte le buffer
     */
    public Forme hover(int mx, int my){
        for(Forme f : this.dessin.getFormes()){
            if(f.closeEnough(mx,my,BUFFER)) return f;
            
        }
        
        return null;
    }

}
