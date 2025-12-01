package vue;

import javax.swing.*;
import java.awt.*;
import model.*;

public class VueMemorisation extends JPanel implements VueComponent{
    private static VueMemorisation instance;
    private Dessin dessin;
    private final static int WIDTH = 1080;
    private final static int HEIGHT = 500;
    boolean canDraw;

    //private JMenuBar barreMenu = new JMenuBar();

    private VueMemorisation(){
        this.dessin = Dessin.getInstance();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public static VueMemorisation getInstance(Dessin dessin){
        if(instance == null){
            instance = new VueMemorisation();
        }        
        return instance;
    }
    

    public void setDessin(Dessin d){
        this.dessin = d;
    }
    


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);
        for (Forme f : dessin.getFormesAReproduire()){ // a changer pour prendre en compte formes / formesAReproduire (classe Dessin)
            if (f instanceof FormeOvale){
                FormeOvale o = (FormeOvale) f;
                g.drawOval(o.getX(), o.getY(), o.getWidth(), o.getHeight()); //a vérifier
            }else if(f instanceof FormePolygone){
                
                FormePolygone p = (FormePolygone) f;
                g.drawPolygon(p.getXList(), p.getYList(), p.getNbPoints());
                //g.fillPolygon(p.getXList(), p.getYList(), p.getNbPoints());
            }
        }
    }
}
