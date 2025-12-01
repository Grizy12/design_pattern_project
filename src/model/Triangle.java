package model;



public class Triangle extends FormePolygone {
    public int x,y,width,height;
    public Triangle(int x, int y,int width,int height){
        super(x,y);
        addPoint(x+width/2, y);
        //int hauteur=(int)Math.round(longueur*Math.sqrt(3)/2);
        addPoint(x+width, y+height);
        addPoint(x, y+height);
    }
    public Triangle(int x,int y,double angle1,int long1,double angle2,int long2){
        super(x,y);
        addPoint(x, y);
        addPoint(x+(int)Math.round(Math.cos(angle1)*long1),y-(int)Math.round(Math.sin(angle1)*long1));
        addPoint(x+(int)Math.round(Math.cos(angle2)*long2),y-(int)Math.round(Math.sin(angle2)*long2));
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setWidth(int w){
        this.width = w;
    }

    public void setHeight(int h){
        this.height = h;
    }
}
