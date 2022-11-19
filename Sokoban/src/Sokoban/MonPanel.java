package Sokoban;

import java.awt.Graphics;
import javax.swing.JPanel;

public class MonPanel extends JPanel {
	
	// Réference sur la fenêtre principale
    private FenetrePrincipale maFenetrePrincipale;
    
    public MonPanel(FenetrePrincipale fenetrePrincipale) {
        maFenetrePrincipale = fenetrePrincipale;
    }
    /**
     * Gestionnaire d'evenement associe a l'evenement "paint" du panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // On appel la méthode dessiner de la fenêtre principale...
        maFenetrePrincipale.dessinerGrille(g);
    }
}
