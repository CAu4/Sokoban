package Sokoban;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.EventQueue;

public class prog_princ {
	public static void main(String[] args) {
		try {
			// lancement de la fenêtre principale
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FenetrePrincipale frame = new FenetrePrincipale();
						frame.setVisible(true);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
