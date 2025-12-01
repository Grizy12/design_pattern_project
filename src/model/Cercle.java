package model;

public class Cercle extends FormeOvale{
    
    public Cercle(int x,int y,int width){
        this(x,y,width,width);
    }
    public Cercle(int x,int y,int width,int height){
        super(x,y,width,height);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        //utilisée pour le rayon du cercle
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setWidth(int w){
        this.width = w;
    }

    public void setHeight(int h){
        this.setHeight(h);
    }
    //utile pour dessiner à partir du centre
    public void setX(int x){
        this.x = x;
    }
    //utile pour dessiner à partir du centre
    public void setY(int y){
        this.y = y;
    }

}
