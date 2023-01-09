package Sokoban;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @file ImageGrille.java
 * @brief Contient la déclaration de la classe ImageGrille qui hérite de la classe Coordonnees
 * @details Gère les images pour l'affichage de la grille de jeu
 * @author Justine Moulin
 */
public class ImageGrille extends Coordonnees{
	
	/**
	 * @brief monImageIcon : ImageIcon
	 */
	private ImageIcon monImageIcon;
    
	/**
	 * @brief largeur : int
	 * @details vaut 60 par défaut
	 */
	private int largeur=60; 
	
	/**
	 * @brief hauteur : int
	 * @details vaut 60 par défaut
	 */
    private int hauteur=60;
    
    /**
	 * @brief ImageGrille
	 * @param x: entier (abscisse coin gauche)
	 * @param y : entier (ordonnée coin gauche)
	 * @param largeur : entier
	 * @param hauteur : entier
	 * @param nom : chaine de caractère (chemin vers le fichier)
	 * @details constructeur de la classe ImageGrille
	 */
    public ImageGrille(int x, int y,  int largeur, int hauteur, String nom) {
        // constructeur de la classe Coordonnées
    	super(x, y);
        // On charge l'image
        monImageIcon = new ImageIcon(nom);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    
    /**
	 * @brief dessiner()
	 * @param g : Graphics
	 * @details permet d'afficher l'image à l'écran
	 */
    public void dessiner(Graphics g) {
        // On affiche l'image à l'intérieur du rectangle englobant
        g.drawImage(monImageIcon.getImage(),this.getX(),this.getY(),largeur, hauteur, null);
        
    }
    
    /**
	 * @brief getImage()
	 * @return Image
	 * @details getter pour l'Image issue de monImageIcon
	 */
    public Image getImage() {
        return monImageIcon.getImage();
    }
    

}
