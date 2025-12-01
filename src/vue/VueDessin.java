package vue;

import javax.swing.*;


import java.awt.*;


import model.Rectangle;
import model.*;

public class VueDessin extends JPanel implements EcouteurModele, VueComponent{
    private Dessin dessin;
    private final static int WIDTH = 1080;
    private final static int HEIGHT = 500;


    //private JMenuBar barreMenu = new JMenuBar();

    public VueDessin(Dessin dessin){
        this.dessin = dessin;
        this.dessin.addListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void setDessin(Dessin d){
        this.dessin = d;
    }

    

    public void previewRectangle(Graphics g, Rectangle r){
        g.setColor(new Color(47, 49, 57, 50));
        g.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    public void previewCercle(Graphics g, Cercle c){
        g.setColor(new Color(24, 12, 92, 50));
        g.drawOval(c.getX(), c.getY(), c.getWidth(), c.getWidth());
        g.fillOval(c.getX(), c.getY(), c.getWidth(), c.getWidth());
    }

    public void previewTriangle(Graphics g, Triangle t){
        g.setColor(new Color(47, 49, 57, 50));
        g.drawPolygon(t.getXList(),t.getYList(),t.getNbPoints());
        g.fillPolygon(t.getXList(),t.getYList(),t.getNbPoints());
    }
    public void previewPolygone(Graphics g, AnyPolygone p){
        g.setColor(new Color(47, 49, 57, 50));
        g.drawPolygon(p.getXList(),p.getYList(),p.getNbPoints());
        g.fillPolygon(p.getXList(),p.getYList(),p.getNbPoints());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);
        for (Forme f : dessin.getFormes()){ 
            if (f instanceof FormeOvale){
                FormeOvale o = (FormeOvale) f;
                g.drawOval(o.getX(), o.getY(), o.getWidth(), o.getHeight()); //a vérifier
            }else if(f instanceof FormePolygone){
                
                FormePolygone p = (FormePolygone) f;
                g.drawPolygon(p.getXList(), p.getYList(), p.getNbPoints());
                //g.fillPolygon(p.getXList(), p.getYList(), p.getNbPoints());
            }
        }
        if(dessin.getFormeTmp()!=null){//pour la preview
            
            if(dessin.getFormeTmp() instanceof Rectangle){
                Rectangle r =(Rectangle)dessin.getFormeTmp();
                this.previewRectangle(g,r);
            }else if(dessin.getFormeTmp() instanceof Cercle){
                Cercle c =(Cercle)dessin.getFormeTmp();
                this.previewCercle(g,c);
            }else if(dessin.getFormeTmp() instanceof Triangle){
                Triangle t =(Triangle)dessin.getFormeTmp();
                this.previewTriangle(g,t);
            }else if(dessin.getFormeTmp() instanceof AnyPolygone){
                AnyPolygone p =(AnyPolygone)dessin.getFormeTmp();
                this.previewPolygone(g,p);
            }
        }
    }

    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }
    
}
