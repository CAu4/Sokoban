package Sokoban;

/**
 * @file Coordonnees.java
 * @brief Contient la déclaration de la classe Coordonnees
 * @details Cette classe permet décrire les emplacements graphiques des objets
 * @author Justine Moulin
 */
public class Coordonnees {
	
	// liste des attributs
	/**
     * @brief m_x : entier correspondant à l'abscisse
     * @detail vaut 0 par défaut
     */
	protected int m_x = 0;
	
	/**
     * @brief m_y : entier correspondant à l'ordonnée
     * @detail vaut 0 par défaut
     */
	protected int m_y = 0;
	
	// liste des méthodes
	/**
     * @brief Coordonnees : constructeur
     * @param x : entier correspondant à l'abscisse
     * @param y : entier correspondant à l'ordonnée
     */
	public Coordonnees(int x, int y)
	{
	    m_x = x;
	    m_y = y;
	}
	
	/**
     * @brief setX : mutateur de x
     * @param x : entier
     */
	public void setX(int x)
	{
		m_x = x;
	}
	
	/**
     * @brief setY : mutateur de y
     * @param y : entier
     */
	public void setY(int y)
	{
		m_y = y;
	}
	
	/**
     * @brief getX : ascesseur de x
     * @return m_x : entier 
     */
	public int getX()
	{
		return m_x;
	}
	
	/**
     * @brief getY : ascesseur de y
     * @return m_y : entier 
     */
	public int getY()
	{
		return m_y;
	}

}
