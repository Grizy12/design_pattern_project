package model;

import java.util.ArrayList;

public class FormePolygone extends Forme{
    public ArrayList<Integer> XList;
    public ArrayList<Integer> YList;
    public int nbPoints;
    public FormePolygone(int x,int y){
        super(x,y);
        XList=new ArrayList<Integer>();
        YList=new ArrayList<Integer>();
        nbPoints=0;
    }
    public void addPoint(int x,int y){
        XList.add(x);
        YList.add(y);
        nbPoints+=1;
    }

    public int[] getXList(){
        int[] x=XList.stream().mapToInt(Integer::intValue).toArray();
        return x;
    }
    public int[] getYList(){
        int[] y=YList.stream().mapToInt(Integer::intValue).toArray();
        return y;
    }
    public int getNbPoints(){
        return nbPoints;
    }
    public void update(){

    }
    @Override
    public void setX(int x){
        int shift=x-this.x;
        this.x=x;
        shiftX(shift);
    }

    @Override
    public void setY(int y){
        int shift=y-this.y;
        this.y=y;
        shiftY(shift);
    }
    public void shiftX(int x){
        for(int i =0;i<nbPoints;i++){
            XList.set(i,XList.get(i)+x);
        }
    }

    public void shiftY(int y){
        for(int i =0;i<nbPoints;i++){
            YList.set(i,YList.get(i)+y);
        }
    }

    @Override
    public boolean closeEnough(int xp,int yp,int buffer){
        //System.out.println("test");
        int x1=XList.get(0);
        int y1=YList.get(0);
        int x2;
        int y2;
        for(int i=1;i<=nbPoints;i++){
            x2=XList.get(i%nbPoints);
            y2=YList.get(i%nbPoints);
            double dist1=Math.sqrt(Math.pow(xp-x1,2)+Math.pow(yp-y1,2));
            //System.out.println("dist de ("+xp+","+yp+")"+","+"("+x1+","+y1+") est"+dist1);
            double dist2=Math.sqrt(Math.pow(xp-x2,2)+Math.pow(yp-y2,2));
            //System.out.println("dist de ("+xp+","+yp+")"+","+"("+x2+","+y2+") est"+dist2);
            double dist12=Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
            //System.out.println("dist de ("+x1+","+y1+")"+","+"("+x2+","+y2+") est"+dist12);
            //System.out.println(dist12+buffer-dist1-dist2);
            if(dist12+(buffer/2)>=dist1+dist2) return true;
            x1=x2;
            y1=y2;
        }
        return false;
    }
}
