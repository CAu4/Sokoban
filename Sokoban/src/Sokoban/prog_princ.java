package Sokoban;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * @file prog_princ.java
 * @brief correspond au programme principal
 * @author Justine Moulin
 */
public class prog_princ {
	public static void main(String[] args) {
		try {
			// lancement de la fenêtre d'accueil
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DialogueAccueil menu = new DialogueAccueil();
						
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
