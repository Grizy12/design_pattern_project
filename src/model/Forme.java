package model;




public class Forme extends AbsModeleEcoutable {
    int x,y;
    public Forme(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }
    public boolean closeEnough(int x,int y,int buffer){
        return false;
    }

}
