package Sokoban;

/**
 * @file Case.java
 * @brief Contient la déclaration de la classe Case qui hérite de la classe Coordonnees
 * @details Cette classe modélise les cases constituant la grille de jeu
 * @author Justine Moulin
 */

public class Case extends Coordonnees {
	
	// liste des attributs
	/**
     * @brief m_type : entier représentant le type de la case considérée
     * @detail vaut 0 par défaut (= case vide)
     */
    private int m_type = 0;
    
    // liste des méthodes
    /**
     * @brief Case : constructeur
     * @param x : entier correspondant à l'abscisse
     * @param y : entier correspondant à l'ordonnée
     * @param type : entier correspondant au type
     */
    public Case(int x, int y, int type) {
    	super(x, y); // appel au constructeur de Coordonnées
    	m_type = type;
    }
    /**
     * @brief getType : ascesseur du type
     * @return type : entier correspondant au type de la case considérée
     */
    public int getType() {
    	return m_type;
    }
    /**
     * @brief setType : mutateur du type
     * @param type : entier
     */
    public void setType(int type) {
    	m_type = type;
    }

}
