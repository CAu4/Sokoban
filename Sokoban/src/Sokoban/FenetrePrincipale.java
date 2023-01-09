package Sokoban;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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

	/**
	 * @brief contentPane : MonPanel
	 * @details initialisation du contentPane personnalisé
	 */
	private MonPanel contentPane;
	
	/**
	 * @brief plateauCourant : Plateau
	 * @details correspond au plateau de jeu
	 */
	private Plateau plateauCourant;
	
	/**
	 * @brief plateauAnnulation : Plateau
	 * @details copie du plateau de jeu, utilisé quand annulation d'un mouvement
	 */
	private Plateau plateauAnnulation;
	
	/**
	 * @brief m_annulation : entier
	 * @details vaut 0 si l'annulation est possible et 1 sinon
	 */
	private int m_annulation = 0;
	
	/**
	 * @brief m_pseudo : chaîne de caractères
	 * @details contient le pseudo choisi par l'utilisateur
	 */
	private String m_pseudo = "";

	/**
	 * @brief FenetrePrinciaple()
	 * @param niv : entier correspondant au niveau à afficher
	 * @param username : chaine de caractères correspondant au pseudo de l'utilisateur
	 * @details constructeur de la classe FenetrePrincipale
	 */
	public FenetrePrincipale(int niv, String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// emplacement et dimension
		setBounds(100,300,700,700);
		
		// contentPane personnalisé
		contentPane = new MonPanel(this);
		this.setContentPane(contentPane);
		
		// initialisation des plateaux de jeu
		plateauCourant = new Plateau(20, 10, niv);
		plateauAnnulation = new Plateau(20, 10, niv);
		
		// initialisation du username
		m_pseudo = username;
		
		// label pour le numéro du niveau
		JLabel labelNumNiveau = new JLabel("NIVEAU : " + plateauCourant.getNiveau());
		labelNumNiveau.setBounds(300,10,120,40);
		contentPane.add(labelNumNiveau);
		
		// label pour le nombre de déplacement
		JLabel labelMvt = new JLabel("Mouvements : " + plateauCourant.getMvt());
		labelMvt.setBounds(545,60,120,40);
		contentPane.add(labelMvt);
		
		// lorsqu'une touche est actionnée
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// gère les événements liés aux touches
				formKeyPressed(e);
				// mise à jour du label pour le numéro de mouvement
				labelMvt.setText("Mouvements : " + plateauCourant.getMvt());
			}
		});
		
		// bouton annuler : annule le déplacement précédent uniquement
		JButton annulerButton = new JButton("Annuler");
		annulerButton.setActionCommand("Annuler");
		// emplacement du bouton du la fenetre
		annulerButton.setBounds(550,100,100,40);
		contentPane.add(annulerButton);
		// enlève le focus du bouton
		annulerButton.setFocusable(false);
		annulerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // si l'annulation est possible
				if (m_annulation == 0)
			    {
					// recopie de plateauAnnulation dans plateauCourant
			    	plateauCourant.setGrillePlateau(plateauAnnulation.getGrillePlateau());
				    plateauCourant.setPersonnage(plateauAnnulation.getPersonnage());
				    plateauCourant.setListeCasesMarquees(plateauAnnulation.getListeCaisses());
				    int move = plateauCourant.getMvt();
				    int s = plateauCourant.getScore();
			        // mise à jour du nombre de mouvement
				    if (move > 0)
			            move = (move - 1);
			        else
			            move = 0;
			        plateauCourant.setMvt(move);
			        // mise à jour du score : pénalisation de 10
			        plateauCourant.setScore(s-10);
			    }
				// l'annulation ne sera pas possible avant un autre déplacement
			    m_annulation = 1;
			    // dessiner la grille de jeu
			    dessinerGrille(getContentPane().getGraphics());
			}
		});
		
		// Bouton recommencer
		JButton recommencerButton = new JButton("Recommencer");
		recommencerButton.setActionCommand("Recommencer");
		// emplacement du bouton du l'écran
		recommencerButton.setBounds(550,150,100,40);
		contentPane.add(recommencerButton);
		// focusable à false
		recommencerButton.setFocusable(false);
		recommencerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// récupère le score
				int s = plateauCourant.getScore();
				// nouveau plateau de jeu courant
				plateauCourant = new Plateau(20, 10, niv);
				// mise à jour du score avec pénalisation
				plateauCourant.setScore(s-50);
				// nouveau plateau pour annulation
				plateauAnnulation = new Plateau(20, 10, niv);
				// dessiner la grille de jeu
				dessinerGrille(getContentPane().getGraphics());
			}
		});
		
		// bouton aide
		JButton aideButton = new JButton("Aide");
		aideButton.setActionCommand("Aide");
		// emplacement du bouton 
		aideButton.setBounds(550, 200, 100, 40);
		contentPane.add(aideButton);
		// focusable à false
		aideButton.setFocusable(false);
		aideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ouverture de la fenêtre de dialogue
				DialogueAide aide = new DialogueAide();
			}
		});
		
		// bouton quitter
		JButton quitterButton = new JButton("Quitter");
		quitterButton.setActionCommand("Quitter");
		// emplacement du bouton
		quitterButton.setBounds(550, 250, 100, 40);
		contentPane.add(quitterButton);
		// focusable à false
		quitterButton.setFocusable(false);
		quitterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ouverture de la fenêtre de dialogue
				DialogueFin fin = new DialogueFin();
				// fermeture de la fenêtre actuelle
				dispose();
			}
		});
		
		// focuable à false
		contentPane.setFocusable(false);	
	}
	
	/**
	 * @brief dessinerGrille()
	 * @param g : Graphics
	 * @details permet d'afficher la grille de jeu
	 */
	public void dessinerGrille (Graphics g) {
		
		// initialisation du buffer et de l'image offsreen
		Graphics bufferGraphics;
	    Image offscreen;
		
	    // initialisation liste d'Image
		ArrayList<ImageGrille> listeImageGrille = new ArrayList<ImageGrille>();
		int type;
	    int nbCaseLargeur;
	    int nbCaseHauteur;
	    
	    // On crée une image en mémoire de la taille du ContentPane
	    offscreen = createImage(plateauCourant.getLargeur(),plateauCourant.getLongueur());
	    // On récupère l'objet de type Graphics permettant de dessiner dans cette image
	    bufferGraphics = offscreen.getGraphics();
	    
	    // dimension de la grille de jeu
	    nbCaseLargeur = plateauCourant.getLargeur()/60;
	    nbCaseHauteur = plateauCourant.getLongueur()/60;
	    // grille de jeu
	    Case[][] grille;
	    grille = new Case [nbCaseHauteur][nbCaseLargeur];
	    grille = this.plateauCourant.getGrillePlateau();
	    // liste de caisses
	    Caisse[] listeCaisse = this.plateauCourant.getListeCaisses();
		
	    // initialisation de la grille de jeu
		for(int i=0; i<nbCaseHauteur; i++)
	    {
	        for(int j=0; j<nbCaseLargeur; j++)
	        {
	            type = grille[i][j].getType();
	            switch(type) {
	                // case vide = chemin
	                case 0 :
	                	ImageGrille icone0 = new ImageGrille((j*60), (i*60),  60, 60, "images/ground_03.png");
	                	listeImageGrille.add(icone0);
	                break;
	                // case mur
	                case 1 :
	                	ImageGrille icone1 = new ImageGrille((j*60), (i*60),  60, 60, "images/crate_01.png");
	                	listeImageGrille.add(icone1);
	                break;
	                // case marquée
	                case 2 :
	                	ImageGrille icone2 = new ImageGrille((j*60), (i*60),  60, 60, "images/environment.png");
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
	                    	ImageGrille icone31 = new ImageGrille((j*60), (i*60),  60, 60, "images/crate_42.png");
		                	listeImageGrille.add(icone31);
	                    }
	                    // si elle est bien placée, on affiche la caisse sombre
	                    else
	                    {
	                    	ImageGrille icone32 = new ImageGrille((j*60), (i*60),  60, 60, "images/crate_07.png");
		                	listeImageGrille.add(icone32);
	                    }
	                break;
	                // personnage
	                case 4 :
	                	ImageGrille icone4 = new ImageGrille((j*60), (i*60),  60, 60, "images/player.png");
	                	listeImageGrille.add(icone4);
	                break;
	            }
	        }
	    }
		// on dessine toutes les images
	    for (ImageGrille img : listeImageGrille)
	    {
	    	img.dessiner(bufferGraphics);
	    }
	    // On afficher l'image mémoire à l'écran
	    g.drawImage(offscreen, 30, 60, null);
		
	}

	/**
	 * @brief formKeyPressed()
	 * @param evt : java.awt.event.KeyEvent
	 * @details permet de gérer les événements liés aux touches du claviers
	 */
	private void formKeyPressed(java.awt.event.KeyEvent evt) {
		// coordonnées actuelles du personnages
		Coordonnees coordP = new Coordonnees((plateauCourant.getPersonnage()).getX(), (plateauCourant.getPersonnage()).getY());
		// enregistrement des données dans plateauAnnulation
		plateauAnnulation.setGrillePlateau(plateauCourant.getGrillePlateau());
		plateauAnnulation.setPersonnage(plateauCourant.getPersonnage());
	    plateauAnnulation.setListeCasesMarquees(plateauCourant.getListeCaisses());
		
	    // en fonction de la touche appuyée
		switch (evt.getKeyCode())
	    {
	        
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
	    
		// dessiner la grille
		dessinerGrille(this.getContentPane().getGraphics());
	    
	    // doit-on passer au niveau suivant ?
	    niveauSuivant();
	    // l'annulation est possible
	    m_annulation = 0;
	}
	
	/**
     * @brief NiveauSuivant : Permet de passer au niveau suivant
     * @details Permet de passer au niveau suivant
     */
    public void niveauSuivant() {
    	int n = plateauCourant.getNiveau();
    	int s = plateauCourant.getScore();
    	
    	// si le niveau est terminé
        if (plateauCourant.niveauTermine() == true)
        {
        	DialogueNiveaux boiteNiv = new DialogueNiveaux(n,s, m_pseudo);
        	// ouvrir la fenetre de dialogue
        	boiteNiv.setVisible(true);
            dispose(); // fermer la fenetre actuelle
        }
        
    }
		    
}
