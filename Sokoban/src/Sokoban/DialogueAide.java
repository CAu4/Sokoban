package Sokoban;

import java.awt.BorderLayout;
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
 * @file DialogueAide.java
 * @brief Contient la déclaration de la classe DialogueAide qui hérite de la classe JDialog
 * @details Contient les consignes du jeu
 * @author Justine Moulin
 */
public class DialogueAide extends JDialog {

	/**
     * @brief contantPane : JPanel
     * @detail initialisation du contentPanel
     */
	private final JPanel contentPanel = new JPanel();

	/**
     * @brief DialogueAide()
     * @detail constructeur de la classe DialogueAide
     */
	public DialogueAide() {
		// emplacement dans l'écran et dimension de la fenêtre
		setBounds(100,100, 1000, 630);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// image de fond
		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon("images/aide.png");
		// redimensionnement
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(800, 550,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon imageFinale = new ImageIcon(newimg);
		label.setIcon(imageFinale);
        label.setBounds(0, 0, 800, 550);
        contentPanel.add(label);
        
        this.setVisible(true);
		
        getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			// déclaration du bouton quitter
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton quitterButton = new JButton("Quitter");
				quitterButton.setActionCommand("Quitter");
				buttonPane.add(quitterButton);
				quitterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose(); // ferme la fenêtre de dialogue
					}
				});
				
			}
			
		}
	}
}
