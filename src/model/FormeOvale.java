package model;



public class FormeOvale extends Forme{
    public int x;
    public int y;
    public int width;
    public int height;
    public FormeOvale(int x,int y,int width,int height){
        super(x,y);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    //Ne marche pas pour un oval qui n'est pas un cercle
    @Override
    public boolean closeEnough(int xp,int yp,int buffer){
        double rayon= width/2;
        double distance=Math.sqrt(Math.pow(x+rayon-xp,2)+Math.pow(y+rayon-yp,2));
        //System.out.println(rayon+","+distance);
        if(distance>=rayon-buffer*2 && distance<=rayon+buffer*2) return true;
        return false;
    }
}
