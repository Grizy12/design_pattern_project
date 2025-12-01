package control.maillon;

import control.command.*;
import control.state.*;
import model.*;
import vue.VueDessin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Fenetre qui correspond à l'étape de dessin.
 */
public class FenetrePrincipale extends MaillonJeu implements ActionListener{
    private final static int WIDTH = 1080;
    private final static int HEIGHT = 720;
    private JLabel titre, indication;
    private JButton bAjoutForme, bRetraitForme, bDeplacerForme, bUndo, bRedo, bValider;
    private VueDessin vueDessin;
    public Interaction i;
    public CommandHandler c;
    private JFrame jf;
    private Jeu jeu;

    /**
     * 
     * @param vueDessin La vue dessin que l'on traite 
     * @param jeu Le jeu actuel 
     * On extends le maillonJeu il faut donc crée la Jframe dans le constructeur
     */
    public FenetrePrincipale(VueDessin vueDessin, Jeu jeu){
        this.jeu=jeu;
        this.vueDessin = vueDessin;
        this.i = new Interaction(vueDessin);
        this.c = CommandHandler.getInstance();
        this.jf = new JFrame("Pixel perfect");
        Container cp = jf.getContentPane();
        cp.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
       
        //Titre
        this.titre = new JLabel("Pixel perfect");
        this.titre.setFont(new Font("Arial", Font.BOLD, 24));
        this.titre.setHorizontalAlignment(SwingConstants.CENTER);
        this.titre.setForeground(Color.WHITE);
        //Bouton Ajouter forme
        this.bAjoutForme = new JButton("Ajouter forme");
        this.bAjoutForme.setFont(new Font("Arial", Font.PLAIN, 18));
        this.bAjoutForme.setBackground(Color.LIGHT_GRAY);
        this.bAjoutForme.addActionListener(this);
        //Bouton Supprimer forme
        this.bRetraitForme = new JButton("Supprimer forme");
        this.bRetraitForme.setFont(new Font("Arial", Font.PLAIN, 18));
        this.bRetraitForme.setBackground(Color.LIGHT_GRAY);
        this.bRetraitForme.addActionListener(this);
        //Bouton Déplacer forme
        this.bDeplacerForme = new JButton("Déplacer");
        this.bDeplacerForme.setFont(new Font("Arial", Font.PLAIN, 18));
        this.bDeplacerForme.setBackground(Color.LIGHT_GRAY);
        this.bDeplacerForme.addActionListener(this);
        //Bouton "undo"
        this.bUndo = new JButton("<-");
        this.bUndo.setBackground(Color.LIGHT_GRAY);
        this.bUndo.setFont(new Font("Arial", Font.PLAIN, 18));
        this.bUndo.addActionListener(this);
        //Bouton "redo"
        this.bRedo = new JButton("->");
        this.bRedo.setBackground(Color.LIGHT_GRAY);
        this.bRedo.setFont(new Font("Arial", Font.PLAIN, 18));
        this.bRedo.addActionListener(this);
        //Dernière action effectuée
        this.indication = new JLabel("Joueur A");
        this.indication.setFont(new Font("Arial", Font.TRUETYPE_FONT, 18));
        this.indication.setHorizontalAlignment(SwingConstants.CENTER);
        this.indication.setForeground(Color.WHITE);
        //Bouton Valider
        this.bValider = new JButton("Valider");
        this.bValider.setFont(new Font("Arial", Font.BOLD, 18));
        this.bValider.setBackground(Color.LIGHT_GRAY);
        this.bValider.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.ipady = 50;
        cp.add(this.titre, gbc); 

        gbc.gridy = 1;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.ipady = 30;
        cp.add(this.bAjoutForme, gbc); 

        gbc.gridx = 1;
        cp.add(this.bRetraitForme, gbc); 

        gbc.gridx = 2;
        cp.add(this.bDeplacerForme, gbc); 

        gbc.gridx = 3;
        cp.add(this.bUndo, gbc); 

        gbc.gridx = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        cp.add(this.bRedo, gbc); 
        //JPanel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        gbc.ipady = 250;
        cp.add(this.vueDessin, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.ipady = 30;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        cp.add(this.indication, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        cp.add(this.bValider, gbc); 

        cp.setBackground(new Color(47, 49, 57));
        jf.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);
        jf.setVisible(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    /**
     * Lors d'un click sur l'un des boutons plus haut , cela permet de changer les états des interactions
     * Cela permet aussi de modifier le pattern command avec undo et redo 
     * cela permet enfin de passer au maillon suivant (bvalider)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource()==bAjoutForme){
            i.setEtat(i.dessiner);
            i.dessiner_Forme();
            System.out.println(i.getEtatActuel());

        }else if(arg0.getSource()==bDeplacerForme){
            i.setEtat(i.deplacer);
            System.out.println(i.getEtatActuel());

        }else if(arg0.getSource()==bRetraitForme){
            i.setEtat(i.effacer);
            System.out.println(i.getEtatActuel());
        }else if(arg0.getSource()==bUndo){
            c.undo();
            System.out.println("Undo");
        }else if(arg0.getSource()==bRedo){
            c.redo();
            System.out.println("Redo");
        }else if(arg0.getSource()==bValider){

            jeu.actionJoueur("fin du dessin");
            synchronized(jeu){
                jeu.notifyAll();
            }
        }
    }


    /**
     * Dans le cas ou 2 joueurs joue on transverse les positions des formes qui ont été crée vers les formes à retenir , par la suite le joueurs suivant,
     * va devoir refaire les formes sauf que cette fois ci les formes ne seront pas transverser vers les formes à retenir permettant à la fonction de score de calculer le résultat
     */
    @Override
    public void actionPropre(Jeu jeu) {
        if(jeu.getJoueurEnCours()<1){
            this.indication.setText("Joueur A");
        }else{
            this.indication.setText("Joueur B");
        }
        
        jf.setVisible(true);
        synchronized(jeu){
            try{
                jeu.wait();
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        if((jeu.getTourActuel()%2==0 && jeu.getJoueurEnCours()==-1)||(jeu.getTourActuel()%2==1 && jeu.getJoueurEnCours()==1)){ 
            
            Dessin.getInstance().loadFormesAReproduire();
            
        }
        //System.out.println("Joueur fenetre: " + jeu.getJoueurEnCours());
        jeu.setJoueurEnCours(jeu.getJoueurEnCours()*-1);
        jf.setVisible(false);
    }

    

   
}
