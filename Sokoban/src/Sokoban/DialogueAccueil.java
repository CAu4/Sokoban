package Sokoban;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @file DialogueAccueil.java
 * @brief Contient la déclaration de la classe DialogueAcceuil qui hérite de la classe JDialog
 * @details Correspond à la page d'accueil du jeu
 * @author Justine Moulin
 */
public class DialogueAccueil extends JDialog {

	/**
     * @brief contantPane : JPanel
     * @detail initialisation du contentPanel
     */
	private final JPanel contentPanel = new JPanel();

	/**
     * @brief DialogueAccueil()
     * @detail constructeur de la classe DialogueAccueil
     */
	public DialogueAccueil() {
		// emplacement sur l'écran et dimenion de la fenêtre
		setBounds(100,100, 700, 500);
		// initialisation du contentPane
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// Image de fond 
		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon("images/menu.jpg");
		// redimensionnement de l'image
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(700, 500,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon imageFinale = new ImageIcon(newimg);
		label.setIcon(imageFinale);
        label.setBounds(0, 0, 700, 500);
        contentPanel.add(label);
        
        this.setVisible(true);
		
        getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			// déclaration du bouton Jouer
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton jouerButton = new JButton("Jouer");
				jouerButton.setActionCommand("Jouer");
				buttonPane.add(jouerButton);
				jouerButton.addActionListener(new ActionListener() {
					// ouverture de DialogueUsername au clic sur le bouton
					public void actionPerformed(ActionEvent e) {
						DialogueUsername username = new DialogueUsername();
						dispose(); // fermeture de la fenêtre de dialogue
					}
				});
				
			}
			
		}
	}

}
