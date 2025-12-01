package model;

import java.util.ArrayList;



public abstract class AbsModeleEcoutable {
    private ArrayList<EcouteurModele> listeners;
    public AbsModeleEcoutable(){
        this.listeners=new ArrayList<>();
    }
    public void addListener(EcouteurModele l){
        listeners.add(l);
    }
    public void removeListener(EcouteurModele l){
        listeners.remove(l);
    }
    protected void fireChange(){
        for(EcouteurModele l : listeners){
            l.modeleMisAJour(this);
        }
    }
}
