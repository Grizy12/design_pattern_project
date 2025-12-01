package control.maillon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.*;

import vue.VueDessin;


import javax.swing.*;
import java.awt.*;



//Interface à exécuter en premier pour lancer le jeu (= menu du jeu)
public class Menu extends MaillonJeu implements ActionListener{
    private final int DIM_L = 500;
    private final int DIM_H = 300;
    private JButton bJcJ, bJcO;
    private JLabel titre;
    private Dessin d;
    private JFrame jf;
    private Jeu jeu ;


    public Menu(Jeu jeu ,boolean firstOne){
        this.jeu = jeu ;
        this.d = Dessin.getInstance();

        this.jf = new JFrame();
        this.titre = new JLabel("Pixel perfect");
        this.titre.setFont(new Font("Arial", Font.BOLD, 24));
        this.titre.setHorizontalAlignment(SwingConstants.CENTER);
        this.titre.setForeground(Color.WHITE);

        this.bJcJ = new JButton("Joueur contre joueur");
        this.bJcJ.addActionListener(this);

        this.bJcO = new JButton("Joueur contre ordi");
        this.bJcO.addActionListener(this);

        Container cp = jf.getContentPane();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        cp.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 20;
        gbc.weightx = 1.0;
        gbc.ipady = DIM_H / 6;
        cp.add(this.titre, gbc);
        
        gbc.gridy = 1;
        cp.add(bJcJ, gbc);

        gbc.gridy = 2;
        cp.add(bJcO, gbc);

        cp.setBackground(new Color(47, 49, 57));
        jf.setSize(DIM_L, DIM_H);
        jf.setPreferredSize(new Dimension(DIM_L, DIM_H));
        jf.setLocationRelativeTo(null);
        jf.setVisible(firstOne);//permet de ne pas afficher le menu final des le debut à la genération des maillons dans les fonctions plus bas
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(!firstOne){//Il faut arreter la boucle de maillon

        }
    }

    public void partieJcJ(){
        Jeu jeux = new Jeu();
        jeux.setJoueurEnCours(-1);
        MaillonJeu fenetreDessin = new FenetrePrincipale(new VueDessin(d), jeux);
        MaillonJeu memorisation = FenetreMemorisation.getInstance();
        MaillonJeu joueur = new FenetrePrincipale(new VueDessin(d), jeux);
        MaillonJeu score = Score.getInstance(d,jeux);
        MaillonJeu menu = new Menu(jeux, false);

        fenetreDessin.setSuivant(memorisation);
        memorisation.setSuivant(joueur);
        joueur.setSuivant(score);
        score.setSuivant(menu);
    
        jeux.demarrer(fenetreDessin);
    }

    public void partieJoueurContreOrdi(){
        Jeu jeux = new Jeu();
        jeux.setJoueurEnCours(0);
        MaillonJeu memorisation = FenetreMemorisation.getInstance();
        MaillonJeu joueur = new FenetrePrincipale(new VueDessin(d),jeux);
        MaillonJeu score = Score.getInstance(d,jeux);
        MaillonJeu menu = new Menu(jeux,false);

        memorisation.setSuivant(joueur);
        joueur.setSuivant(score);
        score.setSuivant(menu);

        jeux.demarrer(memorisation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.bJcJ){
            synchronized(jeu){
                jeu.notifyAll();
            }
            this.jeu.setBoolean(false);//arrete le chain of pattern precedent 
            this.partieJcJ();
            jf.setVisible(false);
        }else if (e.getSource()==this.bJcO){
            synchronized(jeu){
                jeu.notifyAll();
            }
            this.jeu.setBoolean(false);//arrete le chain of pattern precedent 
            this.partieJoueurContreOrdi();
            jf.setVisible(false);
            //this.dispose();
            
        }
    }

    @Override
    public void actionPropre(Jeu jeu) {
        if(jeu.getJoueurEnCours()==0 || jeu.getTourActuel()>= jeu.getNbTours()){
            jf.setVisible(true);
            jeu.resetScores();
            synchronized(jeu){
                try{
                    jeu.wait();
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
        
        
        
    }
}
