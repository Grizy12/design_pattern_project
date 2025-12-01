package control.maillon;

import java.util.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Score extends MaillonJeu implements ActionListener{
    Dessin d;
    private Jeu jeu;
    private JFrame jf;
    private Container cp;
    private JButton suivant;
    private JLabel titre, scoreJoueurA, scoreJoueurB, details;
    static private Score instance = null;

    private Score(Dessin d,Jeu j){
        this.jeu = j;
        this.d = d;
        jf = new JFrame("Score");
        cp = jf.getContentPane();
        
        this.titre = new JLabel("Résultats");
        this.titre.setFont(new Font("Arial", Font.BOLD, 32));
        this.titre.setHorizontalAlignment(SwingConstants.CENTER);
        this.titre.setForeground(Color.WHITE);

        this.scoreJoueurA = new JLabel();
        this.scoreJoueurA.setFont(new Font("Arial", Font.ROMAN_BASELINE, 24));
        this.scoreJoueurA.setHorizontalAlignment(SwingConstants.CENTER);
        this.scoreJoueurA.setForeground(Color.WHITE);

        this.scoreJoueurB = new JLabel();
        this.scoreJoueurB.setFont(new Font("Arial", Font.ROMAN_BASELINE, 24));
        this.scoreJoueurB.setHorizontalAlignment(SwingConstants.CENTER);
        this.scoreJoueurB.setForeground(Color.WHITE);

        this.details = new JLabel();
        this.details.setFont(new Font("Arial", Font.ROMAN_BASELINE, 24));
        this.details.setHorizontalAlignment(SwingConstants.CENTER);
        this.details.setForeground(Color.WHITE);
        
        this.suivant = new JButton("Suivant");
        this.suivant.setFont(new Font("Arial", Font.BOLD, 24));
        this.suivant.addActionListener(this);
        this.scoreJoueurA.setForeground(Color.WHITE);

        GridBagLayout gbl = new GridBagLayout();
        cp.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipady = 100;
        cp.add(this.titre, gbc);

        gbc.ipady = 90;
        gbc.gridy = 1;
        cp.add(this.scoreJoueurA, gbc);

        gbc.ipady = 90;
        gbc.gridy = 2;
        cp.add(this.scoreJoueurB, gbc);

        gbc.ipady = 90;
        gbc.gridy = 3;
        cp.add(this.details, gbc);

        gbc.ipady = 65;
        gbc.gridy = 4;
        cp.add(this.suivant, gbc);

        cp.setBackground(new Color(47, 49, 57));
        jf.setSize(600,600);
        jf.setLocationRelativeTo(null);
        jf.setPreferredSize(new Dimension(600, 600));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Score getInstance(Dessin d,Jeu j){
        if(instance==null){
            instance = new Score(d,j);
        }else{
            instance.setDessin(d);
            instance.getScoreB().setText("");
            instance.getDetails().setText("");
        }
        instance.setJeu(j);
        return instance;
    }

    public void setDessin(Dessin dessin){
        d=dessin;
    }

    public JLabel getScoreB(){
        return this.scoreJoueurB;
    }
    public JLabel getDetails(){
        return this.details;
    }

    public void setJeu(Jeu jeu){
        this.jeu = jeu;
    }

    public double valeurScore(){
        ArrayList<Forme> listeF1=new ArrayList<Forme>(d.getFormesAReproduire());
        ArrayList<Forme> listeF2=new ArrayList<Forme>(d.getFormes());
        double totScore=0;
        double diviseurTot=Math.max(listeF1.size(),listeF2.size());
        if(diviseurTot==0) return 100d;
        for(Forme f1:listeF1){
            int maxScore=0;
            Forme formeMaxScore=null;
            for(Forme f2:listeF2){
                int score=0;
                if(f1 instanceof FormePolygone && f2 instanceof FormePolygone){
                    FormePolygone fp1= (FormePolygone)f1;
                    FormePolygone fp2= (FormePolygone)f2;
                    if(fp1.nbPoints==fp2.nbPoints){
                        score=100*fp1.nbPoints;
                        for(int i=0;i<fp1.nbPoints;i++){
                            score-=Math.abs(fp2.getXList()[i]-fp1.getXList()[i])/2; //Comparaison de chaque point sur les x
                            score-=Math.abs(fp2.getYList()[i]-fp1.getYList()[i])/2; //Comparaison de chaque point sur les y
                        }
                            score/=fp1.nbPoints;
                            if(score>95) score=100; //Si c'est presque parfait, on est généreux sur les points
                    }
                }
                else if(f1 instanceof FormeOvale && f2 instanceof FormeOvale){
                    FormeOvale fo1= (FormeOvale)f1;
                    FormeOvale fo2= (FormeOvale)f2;
                    score=100;
                    score-=Math.abs(fo2.x+(fo2.width/2)-(fo1.x+(fo1.width/2)))/2; //Comparaison du centre du cercle sur les x
                    score-=Math.abs(fo2.y+(fo2.height/2)-(fo1.y+(fo1.height/2)))/2; //Comparaison du centre du cercle sur les y
                    score-=(Math.abs(fo2.width-fo1.width)+Math.abs(fo2.height-fo1.height))/8; //Comparaison des rayons
                    //if(score>0) System.out.println(Math.abs(fo2.width-fo1.width)+","+Math.abs(fo2.height-fo1.height));
                    if(score>95) score=100; //Si c'est presque parfait, on est généreux sur les points
                }
                if(score>maxScore){
                    maxScore=score;
                    formeMaxScore=f2;
                }
            }
            totScore+=maxScore;
            if(formeMaxScore!=null) listeF2.remove(formeMaxScore);
        }
        return totScore/diviseurTot;
    }    

    public void calculerScore(){
        
        scoreJoueurA.setText("JoueurA: votre score est de "+ this.jeu.getScoreA());
        if(this.jeu.getJoueurEnCours()!=0){
            scoreJoueurB.setText("JoueurB: votre score est de "+ this.jeu.getScoreB());
            if(this.jeu.getTourActuel()+1==this.jeu.getNbTours()){
                if(this.jeu.getScoreA()>this.jeu.getScoreB()){
                    details.setText("Joueur A a gagné avec un score de " + this.jeu.getScoreA());
                }else if(this.jeu.getScoreA()<this.jeu.getScoreB()){
                    details.setText("Joueur B a gagné avec un score de " + this.jeu.getScoreB());
                }else{
                    details.setText("Egalité avec le score : " + this.jeu.getScoreB());
                }
                
            }
        }
        
        
        
        //jf.pack();
        //interaction.update();//declenche un fireChange()
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==suivant){
            synchronized(jeu){
                jeu.notifyAll();
            }
        }
    }

    @Override
    public void actionPropre(Jeu jeu) {
        
        if(jeu.getJoueurEnCours()*-1<1){ //-1 car dans FenetrePrincipale en JcJ, on change le joueur courant (Mémorisation -> Dessin)
            
            jeu.addScoreA(valeurScore());
        }else{
            
            jeu.addScoreB(valeurScore());
        }
        calculerScore();
        jf.setVisible(true);
        jeu.setTour(jeu.getTourActuel()+1);
        
        synchronized(jeu){
            try{
                jeu.wait(); 
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        d.clearFormes();//on nettoie la partie précédentes
        d.clearFormesAReproduire();
        jeu.setJoueurEnCours(jeu.getJoueurEnCours()*-1);
        jf.setVisible(false);
    }
}
