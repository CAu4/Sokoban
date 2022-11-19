package Sokoban;

/**
 * @file Caisse.java
 * @brief Contient la déclaration de la classe Caisse qui hérite de la classe Coordonnees
 * @details Cette classe modélise les caisses pouvant être pousée par le joueur
 * @author Justine Moulin
 */

public class Caisse extends Coordonnees {
	
	// liste des attributes
	/**
     * @brief m_etatCaisse : booléen qui vaut vrai si la caisse est bien placée et faux sinon
     * @detail vaut false par défaut (= non marquée)
     */
	private boolean m_etatCaisse = false;
	
	// liste des méthodes 
	/**
     * @brief Caisse : constructeur
     * @param x : entier correspondant à l'abscisse
     * @param y : entier correspondant à l'ordonnée
     */
    public Caisse(int x, int y, boolean etat)
    {
    	super(x,y); // appel au constructeur de coordonnées
    	m_etatCaisse = etat;
    }
    /**
     * @brief getEtatCaisse : ascesseur de m_etatCaisse
     * @return m_etatCaisse
     */
    public boolean getEtatCaisse() {
    	return m_etatCaisse;
    }
    /**
     * @brief setEtatCaisse : mutateur de m_etatCaisse
     * @param etat : booléen 
     */
    public void setEtatCaisse(boolean etat) {
    	m_etatCaisse = etat;
    }

}
