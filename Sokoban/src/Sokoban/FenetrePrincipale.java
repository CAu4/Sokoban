package Sokoban;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

/**
 * @file FenetrePrincipale.java
 * @brief Contient la déclaration de la classe FenetrePrincipale qui hérite de JFrame
 * @details Cette classe correspond à la fenêtre de jeu principale
 * @author Justine Moulin
 */

public class FenetrePrincipale extends JFrame {

	private MonPanel contentPane;
	
	private Plateau plateauCourant;

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				formKeyPressed(e);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 1000);
		
		contentPane = new MonPanel(this);
		this.setContentPane(contentPane);
		// rendre non focusable
		contentPane.setFocusable(false);
		
		plateauCourant = new Plateau(20, 10, 0);
		
	}
	
	public void dessinerGrille (Graphics g) {
		
		Graphics bufferGraphics;
	    Image offscreen;
	    
	    // On crée une image en mémoire de la taille du ContentPane
	    offscreen = createImage(this.getContentPane().getWidth(),this.getContentPane().getHeight());
	    // On récupère l'objet de type Graphics permettant de dessiner dans cette image
	    bufferGraphics = offscreen.getGraphics();
		
		ArrayList<ImageGrille> listeImageGrille = new ArrayList<ImageGrille>();
		int type;
	    int nbCaseLargeur;
	    int nbCaseHauteur;
	    nbCaseLargeur = plateauCourant.getLargeur()/60;
	    nbCaseHauteur = plateauCourant.getLongueur()/60;
	    Case[][] grille;
	    grille = new Case [nbCaseHauteur][nbCaseLargeur];
	    grille = this.plateauCourant.getGrillePlateau();
	    Caisse[] listeCaisse = this.plateauCourant.getListeCaisses();
		
		for(int i=0; i<nbCaseHauteur; i++)
	    {
	        for(int j=0; j<nbCaseLargeur; j++)
	        {
	            type = grille[i][j].getType();
	            switch(type) {
	                // case vide = chemin
	                case 0 :
	                	ImageGrille icone0 = new ImageGrille((35 + j*60), (75 + i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/ground_03.png");
	                	//icone0 = icone0.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  

	                	listeImageGrille.add(icone0);
	                break;
	                // case mur
	                case 1 :
	                	ImageGrille icone1 = new ImageGrille((35 + j*60), (75 + i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/crate_01.png");
	                	listeImageGrille.add(icone1);
	                break;
	                // case marquée
	                case 2 :
	                	ImageGrille icone2 = new ImageGrille((35 + j*60), (75 + i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/environment.png");
	                	listeImageGrille.add(icone2);
	                break;
	                // caisse
	                case 3 :
	                    int k = 0;
	                    // on parcours la liste de caisse pour trouver celle ci correspond à la position
	                    while ((listeCaisse[k].getX() != i)||(listeCaisse[k].getY() != j))
	                        k = k + 1;
	                    // si elle n'est pas bien placée, on affiche une caisse classique
	                    if (listeCaisse[k].getEtatCaisse() == false)
	                    {
	                    	ImageGrille icone31 = new ImageGrille((35 + j*60), (75 + i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/crate_42.png");
		                	listeImageGrille.add(icone31);
	                    }
	                    // si elle est bien placée, on affiche la caisse sombre
	                    else
	                    {
	                    	ImageGrille icone32 = new ImageGrille((35 + j*60), (75 + i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/crate_07.png");
		                	listeImageGrille.add(icone32);
	                    }
	                break;
	                // personnage
	                case 4 :
	                	ImageGrille icone4 = new ImageGrille((35 + j*60), (75 + i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/player_05.png");
	                	listeImageGrille.add(icone4);
	                break;
	            }
	        }
	    }
	    for (ImageGrille img : listeImageGrille)
	    {
	    	img.dessiner(bufferGraphics);
	    }
	    // On afficher l'image mémoire à l'écran
	    g.drawImage(offscreen,10,10,null);
	}

	private void formKeyPressed(java.awt.event.KeyEvent evt) {
		  //if (evt.getKeyCode() == KeyEvent.VK_UP ) {}
		Coordonnees coordP = new Coordonnees((plateauCourant.getPersonnage()).getX(), (plateauCourant.getPersonnage()).getY());
	    switch (evt.getKeyCode())
	    {
	        // déplacement avec mes flèches du clavier
	        case KeyEvent.VK_UP:
	        	plateauCourant.deplacerPersonnage(coordP, 1);
	        break;
	        case KeyEvent.VK_DOWN :
	            plateauCourant.deplacerPersonnage(coordP, 2);
	        break;
	        case KeyEvent.VK_RIGHT:
	        	plateauCourant.deplacerPersonnage(coordP, 3);
	        break;
	        case KeyEvent.VK_LEFT :
	        	plateauCourant.deplacerPersonnage(coordP, 4);
	        break;
	    }
	    dessinerGrille(this.getContentPane().getGraphics());
	    // doit-on passer au niveau suivant ?
	    niveauSuivant();
	}
	
	/**
     * @brief NiveauSuivant : Permet de passer au niveau suivant
     */
    public void niveauSuivant() {
    	int n = plateauCourant.getNiveau();
    	// Création de la boite en mémoire
        DialogueNiveaux boiteNiv = new DialogueNiveaux();
        
        // on regarde si le niveau est fini
        if (plateauCourant.niveauTermine() == true)
        {
            boiteNiv.setVisible(true);
        }
    }
		    
}
