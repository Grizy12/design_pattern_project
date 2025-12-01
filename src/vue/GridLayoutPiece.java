package vue;

import java.awt.*;

public class GridLayoutPiece implements LayoutManager{
    private final static int DIM = 300;
    private int ncol = 2;
    private int nrow = 3;
    private int margin = 10;
    @Override
    public void layoutContainer(Container parent){
        int w = parent.getWidth();
        int h = parent.getHeight();
        Component[] c = parent.getComponents();
        int wCase = (w-(ncol+1)*margin)/ncol;
        int hCase = (h-(nrow+1)*margin)/nrow;
        int index = 0;
        for(int i =0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                
                
                Component c2 = c[index];
                c2.setBounds(j*(margin+wCase)+margin,i*(margin+hCase)+margin,wCase,hCase);
                index++;
                if(index>= c.length){
                    return;
                }

            }
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(DIM,DIM);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(DIM,DIM);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        
    }

    @Override
    public void addLayoutComponent(String arg0, Component arg1) {
        
        
    }

}