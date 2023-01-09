package Sokoban;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @file MonPanel.java
 * @brief Contient la déclaration de la classe MonPanel qui hérite de la classe JPanel
 * @details Permet de personnaliser le contentPanel
 * @author Justine Moulin
 */
public class MonPanel extends JPanel {
	
	/**
	 * @brief maFenetrePrincipale : FenetrePrincipale
	 * @details Référence sur la fenetre principale
	 */
    private FenetrePrincipale maFenetrePrincipale;
    
    /**
     * @brief MonPanel()
     * @details constructeur de la classe MonPanel
     */
    public MonPanel(FenetrePrincipale fenetrePrincipale) {
        maFenetrePrincipale = fenetrePrincipale;
   
    }
   
    /**
     * @brief paintComponent()
     * @param g : Graphics
     * @details Gestionnaire d'événement
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // appel de la méthode dessiner de la fenêtre principale...
        maFenetrePrincipale.dessinerGrille(g);
    }
}
