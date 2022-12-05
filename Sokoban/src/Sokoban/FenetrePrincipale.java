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

	private MonPanel contentPane;
	
	private Plateau plateauCourant;
	private Plateau plateauAnnulation;
	
	private int m_annulation = 0;
	
	private String m_pseudo = "";

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale(int niv, String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,300,700,700);
		
		contentPane = new MonPanel(this);
		this.setContentPane(contentPane);
		
		plateauCourant = new Plateau(20, 10, niv);
		plateauAnnulation = new Plateau(20, 10, niv);
		
		m_pseudo = username;
		
		JLabel labelMvt = new JLabel("Mouvements : " + plateauCourant.getMvt());
		labelMvt.setBounds(545,60,120,40);
		contentPane.add(labelMvt);
		
		JLabel labelNumNiveau = new JLabel("NIVEAU : " + plateauCourant.getNiveau());
		labelNumNiveau.setBounds(300,10,120,40);
		contentPane.add(labelNumNiveau);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				formKeyPressed(e);
				labelMvt.setText("Mouvements : " + plateauCourant.getMvt());
			}
		});
		
		JButton annulerButton = new JButton("Annuler");
		annulerButton.setActionCommand("Annuler");
		annulerButton.setBounds(550,100,100,40);
		contentPane.add(annulerButton);
		annulerButton.setFocusable(false);
		annulerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if (m_annulation == 0)
			    {
			    	plateauCourant.setGrillePlateau(plateauAnnulation.getGrillePlateau());
				    plateauCourant.setPersonnage(plateauAnnulation.getPersonnage());
				    plateauCourant.setListeCasesMarquees(plateauAnnulation.getListeCaisses());
				    int move = plateauCourant.getMvt();
				    int s = plateauCourant.getScore();
			        if (move > 0)
			            move = (move - 1);
			        else
			            move = 0;
			        plateauCourant.setMvt(move);
			        plateauCourant.setScore(s-10);
			    }
			    m_annulation = 1;
			    dessinerGrille(getContentPane().getGraphics());
			}
		});
		
		JButton recommencerButton = new JButton("Recommencer");
		recommencerButton.setActionCommand("Recommencer");
		recommencerButton.setBounds(550,150,100,40);
		contentPane.add(recommencerButton);
		recommencerButton.setFocusable(false);
		recommencerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int s = plateauCourant.getScore();
				
				plateauCourant = new Plateau(20, 10, niv);
				plateauCourant.setScore(s-50);
				plateauAnnulation = new Plateau(20, 10, niv);
				
				dessinerGrille(getContentPane().getGraphics());
			}
		});
		
		JButton quitterButton = new JButton("Quitter");
		quitterButton.setActionCommand("Quitter");
		quitterButton.setBounds(550, 200, 100, 40);
		contentPane.add(quitterButton);
		quitterButton.setFocusable(false);
		quitterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogueFin fin = new DialogueFin();
				dispose();
			}
		});
		
		contentPane.setFocusable(false);	
	}
	
	public void dessinerGrille (Graphics g) {
		
		Graphics bufferGraphics;
	    Image offscreen;
		
		ArrayList<ImageGrille> listeImageGrille = new ArrayList<ImageGrille>();
		int type;
	    int nbCaseLargeur;
	    int nbCaseHauteur;
	    
	    // On crée une image en mémoire de la taille du ContentPane
	    offscreen = createImage(plateauCourant.getLargeur(),plateauCourant.getLongueur());
	    // On récupère l'objet de type Graphics permettant de dessiner dans cette image
	    bufferGraphics = offscreen.getGraphics();
	    
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
	                	ImageGrille icone0 = new ImageGrille((j*60), (i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/ground_03.png");
	                	listeImageGrille.add(icone0);
	                break;
	                // case mur
	                case 1 :
	                	ImageGrille icone1 = new ImageGrille((j*60), (i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/crate_01.png");
	                	listeImageGrille.add(icone1);
	                break;
	                // case marquée
	                case 2 :
	                	ImageGrille icone2 = new ImageGrille((j*60), (i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/environment.png");
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
	                    	ImageGrille icone31 = new ImageGrille((j*60), (i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/crate_42.png");
		                	listeImageGrille.add(icone31);
	                    }
	                    // si elle est bien placée, on affiche la caisse sombre
	                    else
	                    {
	                    	ImageGrille icone32 = new ImageGrille((j*60), (i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/crate_07.png");
		                	listeImageGrille.add(icone32);
	                    }
	                break;
	                // personnage
	                case 4 :
	                	ImageGrille icone4 = new ImageGrille((j*60), (i*60),  60, 60, "/Users/justinemoulin/Sokoban2/images/player.png");
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
	    g.drawImage(offscreen, 30, 60, null);
		
	}

	private void formKeyPressed(java.awt.event.KeyEvent evt) {
		Coordonnees coordP = new Coordonnees((plateauCourant.getPersonnage()).getX(), (plateauCourant.getPersonnage()).getY());
		plateauAnnulation.setGrillePlateau(plateauCourant.getGrillePlateau());
		plateauAnnulation.setPersonnage(plateauCourant.getPersonnage());
	    plateauAnnulation.setListeCasesMarquees(plateauCourant.getListeCaisses());
		
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
	    m_annulation = 0;
	}
	
	/**
     * @brief NiveauSuivant : Permet de passer au niveau suivant
     */
    public void niveauSuivant() {
    	int n = plateauCourant.getNiveau();
    	int s = plateauCourant.getScore();
    	
        if (plateauCourant.niveauTermine() == true)
        {
        	DialogueNiveaux boiteNiv = new DialogueNiveaux(n,s, m_pseudo);
        	boiteNiv.setVisible(true);
            dispose();
        }
        
    }
		    
}
