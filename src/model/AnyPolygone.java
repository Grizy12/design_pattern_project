package model;

public class AnyPolygone extends FormePolygone{
    
    public AnyPolygone(int x,int y,int width,int nbCote){
        super(x,y);
        
        
        double decalageAngle=2*Math.PI/nbCote;
        double tmpAngle=decalageAngle;
        double sommeAngle=1;
        while(tmpAngle<Math.PI*0.5){
            sommeAngle+=2*Math.cos(tmpAngle);
            tmpAngle+=decalageAngle;
        }
        int longueur=(int)Math.round(width/sommeAngle);
        int tmpX=x+(width-longueur)/2;
        int tmpY=y;
        tmpAngle=0;
        addPoint(tmpX,tmpY);
        for(int i=1;i<nbCote;i++){
            tmpX+=(int)Math.round(Math.cos(tmpAngle)*longueur);
            tmpY-=(int)Math.round(Math.sin(tmpAngle)*longueur);
            tmpAngle-=decalageAngle;
            if(tmpAngle<0) tmpAngle+=Math.PI*2;
            addPoint(tmpX,tmpY);
        }
    }
}
