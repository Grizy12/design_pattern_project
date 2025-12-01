package control.maillon;


import model.*;
import vue.VueMemorisation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Fenetre qui correspond à l'étape de mémorisation , on en a jamais besoin de deux d'affilé donc on en a fait un singleton.
 */
public class FenetreMemorisation extends MaillonJeu implements ActionListener{

    static private FenetreMemorisation instance=null;
    private JFrame jf;
    private JLabel titre, consigne, indication;
    private JButton bValider;
    private VueMemorisation vueMemorisation;
    private final static int WIDTH = 1080;
    private final static int HEIGHT = 720;
    private int tempsMillisecondes;
    Boolean skip;
    /**
     * Initialise une jframe car cette classe extends un MaillonJeu
     * Le parametre isVisible est mis à faux
     */
    private FenetreMemorisation(){
        this.skip = true;
        this.vueMemorisation = VueMemorisation.getInstance(Dessin.getInstance());

        this.jf = new JFrame("Pixel perfect");
        Container cp = jf.getContentPane();
        cp.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        //Titre
        this.titre = new JLabel("Pixel perfect");
        this.titre.setFont(new Font("Arial", Font.BOLD, 24));
        this.titre.setHorizontalAlignment(SwingConstants.CENTER);
        this.titre.setForeground(Color.WHITE);
        //Consigne
        this.consigne = new JLabel("Mémorisez les formes à reproduire");
        this.consigne.setFont(new Font("Arial", Font.BOLD, 14));
        this.consigne.setHorizontalAlignment(SwingConstants.CENTER);
        this.consigne.setForeground(Color.WHITE);
        //Dernière action effectuée
        this.indication = new JLabel("Vous avez");
        this.indication.setFont(new Font("Arial", Font.TRUETYPE_FONT, 18));
        this.indication.setHorizontalAlignment(SwingConstants.CENTER);
        this.indication.setForeground(Color.WHITE);
        //Bouton Valider
        this.bValider = new JButton("Commencer maintenant");
        this.bValider.setFont(new Font("Arial", Font.PLAIN, 18));
        this.bValider.setBackground(Color.LIGHT_GRAY);
        this.bValider.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.ipady = 50;
        cp.add(this.titre, gbc); 
        //Consigne
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.ipady = 30;
        cp.add(this.consigne, gbc); 
        //JPanel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        gbc.ipady = 250;
        cp.add(this.vueMemorisation, gbc);

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
     * Methodes static de singleton
     * @return L'instance de FenetreMemorisation
     * A chaque fois qu'on refait appel à cette fenetre on remet la posibilité de skip les 10s à true
     */
    public static FenetreMemorisation getInstance(){
        if(instance == null){
            instance = new FenetreMemorisation();
        }else{
            instance.setBooleanTrue();
        }
        instance.setTempsMillisecondes(10000); //temps d'attente 10000 :=> 10 secondes
        return instance;
    }
    /**
     * Boolean qui permet de skipper le temps d'attente
     */
    public void setBooleanTrue(){
        this.skip=true;
    }

    public void setTempsMillisecondes(int ms){
        this.tempsMillisecondes = ms;
        this.indication.setText("Vous avez "+this.tempsMillisecondes/1000 + " secondes.");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource()==bValider){
            this.skip = false;

        }
    }


    /**
     * fait partie de la chaine de maillon de chain of responsability
     * Differencie l'utilisation jcj et jcO (joueur contre ordi)
     * il est possible de skipper le temps d'attente , les formes sont aussi nettoyé à chaque utilisation pour quel soit toujours nouvelle dans le cas d'une generation aléatoire
     */
    @Override
    public void actionPropre(Jeu jeu) {
        this.jf.setVisible(true);
        if(jeu.getJoueurEnCours()==0){
            GenererFormesAleatoires gfa = GenererFormesAleatoires.getInstance();
            gfa.clearFormes();
            gfa.genererFormes();
        }
        
        int attente = 0;
        //Dessin d = Dessin.getInstance();
        //System.out.println(d.getFormesAReproduire().size());
        while (this.skip && attente < this.tempsMillisecondes/10) {
            try{
                Thread.sleep(this.tempsMillisecondes/1000);
                attente += 1;
                    
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        this.skip = true;
        this.jf.setVisible(false);
        
    }

    

   
}
