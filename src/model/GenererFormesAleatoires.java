package model;


import java.util.*;

public class GenererFormesAleatoires implements GenererFormes{
    static private GenererFormesAleatoires instance = null;
    protected int nbFormes;
    private Dessin dessin;
    private Random random;


    private GenererFormesAleatoires(int nbFormes){
        this.dessin = Dessin.getInstance();
        this.nbFormes = nbFormes;
        this.random = new Random();
    }

    private GenererFormesAleatoires(){
        this(10);
    }

    public static GenererFormesAleatoires getInstance(int nbFormes){
        if(instance==null){
            instance = new GenererFormesAleatoires(nbFormes);
        }else{
            instance.setNbFormes(nbFormes);    
        }
        return instance;

    }

    public static GenererFormesAleatoires getInstance(){
        if(instance==null){
            instance = new GenererFormesAleatoires();
        }
        
        return instance;

    }

    public void clearFormes(){
        this.dessin.clearFormesAReproduire();
    }

    public void setNbFormes(int nb){
        this.nbFormes = nb;
    }

    public Dessin getDessin(){
        return this.dessin;

    }

    private int randomBetween(int a, int b){
		return a + this.random.nextInt(b - a);
	}

    @Override
    public void genererFormes() {
        for(int i=0; i<nbFormes; i++){
            int n = this.random.nextInt(4);
            switch ((n)) {
                case 0: //Cercle
                    int radius = this.randomBetween(30, 100);
                    int cx = this.randomBetween(0, 1080-(radius*2));
                    int cy = this.randomBetween(0, 500-(radius*2));
                    //System.out.println("radius: "+ radius);
                    Cercle c = new Cercle(cx, cy, radius*2, radius*2);
                    this.dessin.addFormeAReproduire(c);
                    break;
                case 1:
                    int rwidth = this.randomBetween(30, 200);
                    int rheight = this.randomBetween(30, 200);
                    int rx = this.randomBetween(0, 1080-rwidth);
                    int ry = this.randomBetween(0, 500-rheight);
                    Rectangle r = new Rectangle(rx, ry, rwidth, rheight);
                    this.dessin.addFormeAReproduire(r);
                    break;

                case 2:
                    int twidth = this.randomBetween(30, 200);
                    int theight = this.randomBetween(30, 200);
                    int tx = this.randomBetween(0, 1080-twidth);
                    int ty = this.randomBetween(0, 500-theight);
                    Triangle t = new Triangle(tx,ty,twidth,theight);
                    this.dessin.addFormeAReproduire(t);
                    break;
                case 3:
                    int pwidth = this.randomBetween(50, 200);
                    int px = this.randomBetween(0, 1080-pwidth);
                    int py = this.randomBetween(0, 500-pwidth);
                    int nbCotes=5;
                    while(nbCotes<10 && this.random.nextInt(2)==0) nbCotes+=1;
                    AnyPolygone p = new AnyPolygone(px, py, pwidth,nbCotes);
                    this.dessin.addFormeAReproduire(p);
                    break;
                default:
                    break;
            }
        }
    }
    
}
