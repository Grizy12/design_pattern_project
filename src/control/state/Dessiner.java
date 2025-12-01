package control.state;

import vue.GridLayoutPiece;
import javax.swing.*;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
//c'est peut etre pas claire mais dessiner signifie "Ajouter une forme"
/**
 * Dessiner est une classe qui va donner à l'utilisateurs différents choix de forme
 */
public class Dessiner extends AbsEtatInteraction implements ActionListener,KeyListener{
    private JButton bRect, bCercle ,  bTrian,bPoly; 
    private JLabel labelPoly;
    private JTextField taillePoly;
    private JFrame jf;
    /**
     * 
     * @param i L'interaction que l'on utilise
     */
    public Dessiner(Interaction i){
        super(i,"Mode_Dessin");
    }

    
    /**
     * genere un Jframe avec un GridLayoutPiece avec différents boutons correspondant à une forme chacune
     */
    @Override
    public void dessiner_Forme(){
        

        jf = new JFrame("Choix piece");
        jf.setBounds(100,100,500,200);
        jf.setPreferredSize(new Dimension(500,200));
        Container cp = jf.getContentPane();
        cp.setLayout(new GridLayoutPiece());

        this.bRect = new JButton("Rectangle");
        this.bRect.addActionListener(this);
        cp.add(this.bRect);

        this.bCercle = new JButton("Cercle");
        this.bCercle.addActionListener(this);
        cp.add(this.bCercle);


        this.bTrian = new JButton("Triangle");
        this.bTrian.addActionListener(this);
        cp.add(this.bTrian);

        this.bPoly = new JButton("Polygone régulier");
        this.bPoly.addActionListener(this);
        cp.add(this.bPoly);

        this.labelPoly = new JLabel("Cotés du Polygone (de 5 à 10)");
        cp.add(this.labelPoly);

        this.taillePoly = new JTextField("5");
        this.taillePoly.addKeyListener(this);
        cp.add(this.taillePoly);

        jf.pack();
        jf.setVisible(true);
        interaction.update();//declenche un fireChange() 

    }
    
    /**
     * Fait une certaines action selon le bouton pressé
     */
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==bRect){
            jf.dispose();
            interaction.setEtat(interaction.dessinerRect);
            System.out.println(interaction.getEtatActuel());
        }else if(e.getSource()==bCercle){
            jf.dispose();
            interaction.setEtat(interaction.dessinerCercle);
            System.out.println(interaction.getEtatActuel());
        }else if(e.getSource()==bTrian){
            jf.dispose();
            interaction.setEtat(interaction.DessinerTriangle);
            System.out.println(interaction.getEtatActuel());
        }else if(e.getSource()==bPoly){
            jf.dispose();
            int nbCotes=Integer.parseInt(taillePoly.getText());
            if(nbCotes>=5 && nbCotes<=10){
                interaction.setEtat(interaction.DessinerAnyPolygone);
                interaction.setNbCotesPoly(nbCotes);
                System.out.println(interaction.getEtatActuel()+","+nbCotes);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e){};
    @Override
    public void keyReleased(KeyEvent e){};
    /**
     * Existe Pour s'assurer que les entrées soient numériques
     */
    @Override
    public void keyTyped(KeyEvent e){
        char touche = e.getKeyChar();
        if (((touche < '0') || (touche > '9')) && (touche != KeyEvent.VK_BACK_SPACE))
            e.consume();  // ignorer l'événement
    }

    

    
}