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
 * @file DialogueFin.java
 * @brief Contient la déclaration de la classe DialogueFin qui hérite de la classe JDialog
 * @details Correspond à la fenêtre de fermeture
 * @author Justine Moulin
 */
public class DialogueFin extends JDialog {

	/**
     * @brief contantPane : JPanel
     * @detail initialisation du contentPanel
     */
	private final JPanel contentPanel = new JPanel();

	/**
	 * @brief DialogueFin()
	 * @details constructeur de la classe DialogueFin()
	 * @author Justine Moulin
	 */
	public DialogueFin() {
		// Emplacement sur l'écran et dimension de la fenêtre
		setBounds(100,100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// Image de fond
		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon("images/fin.jpg");
		// redimensionnement
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(700, 500,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon imageFinale = new ImageIcon(newimg);
		label.setIcon(imageFinale);
        label.setBounds(0, 0, 700, 500);
        contentPanel.add(label);
        
        this.setVisible(true);
		
        getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			// Déclaration du bouton Quitter
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton quitterButton = new JButton("Quitter");
				quitterButton.setActionCommand("Quitter");
				buttonPane.add(quitterButton);
				quitterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose(); // ferme la fenêtre
					}
				});
				
			}
			
		}
	}
}
