package model;



public class Rectangle extends FormePolygone{
    public int x,y,width,height;

    public Rectangle(int x,int y,int width,int height){
        this(x,y,width,height,0);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public Rectangle(int x,int y,int width,int height,double angle){
        super(x,y);
        addPoint(x,y);
        addPoint(x+(int)Math.round(Math.cos(angle)*width),y-(int)Math.round(Math.sin(angle)*width));
        addPoint(x+(int)Math.round(Math.cos(angle)*width)+(int)Math.round(Math.sin(angle)*height),y-(int)Math.round(Math.sin(angle)*width)+(int)Math.round(Math.cos(angle)*height));
        addPoint(x+(int)Math.round(Math.sin(angle)*height),y+(int)Math.round(Math.cos(angle)*height));
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
