package Sokoban;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageGrille extends Coordonnees{
	
	private ImageIcon monImageIcon;
    private int largeur=60;
    private int hauteur=60;
    
    public ImageGrille(int x, int y,  int largeur, int hauteur, String nom) {
        super(x, y);
        // On charge l'image
        monImageIcon = new ImageIcon(nom);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    
    public void dessiner(Graphics g) {
        // On affiche l'image à l'intérieur du rectangle englobant
        g.drawImage(monImageIcon.getImage(),this.getX(),this.getY(),largeur, hauteur, null);
        
    }
    
    public Image getImage() {
        return monImageIcon.getImage();
    }
    

}
