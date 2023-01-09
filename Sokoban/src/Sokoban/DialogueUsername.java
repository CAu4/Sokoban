package Sokoban;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @file DialogueUsername.java
 * @brief Contient la déclaration de la classe DialogueUsername qui hérite de la classe JDialog
 * @details Permet à l'utilisateur de renseigner un pseudo
 * @author Justine Moulin
 */
public class DialogueUsername extends JDialog {

	/**
	 * @brief contentPanel : JPanel
	 * @details initialisation du contentPane
	 */
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * @brief usernameField : JTextField
	 * @details champs textuel où écrie le username
	 */
	private JTextField usernameField;

	/**
	 * @brief DialogueUsername()
	 * @details contructeur de la classe DialogueUsername
	 */
	public DialogueUsername() {
		// emplacement sur l'écran et dimension de la fenêtre
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.setVisible(true);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			// Label Entrez un pseudo
			JLabel usernameLabel = new JLabel("Entrez un pseudo :");
			contentPanel.add(usernameLabel);
		}
		{
			// initialisation du JTextField
			usernameField = new JTextField();
			contentPanel.add(usernameField);
			usernameField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// déclaration du bouton valider
				JButton validerButton = new JButton("Valider");
				validerButton.setActionCommand("Valider");
				buttonPane.add(validerButton);
				validerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// déclaration d'une fenetrePrincipale qui prend en paramètre le pseudo 
						// donné par l'utilisateur
						FenetrePrincipale frame = new FenetrePrincipale(0, usernameField.getText());
						frame.getContentPane().setLayout(null);
						frame.setVisible(true); // affichage de la fenêtre principale
						dispose(); // fermeture de la fenêtre de dialogue
					}
				});

			}

		}
	}

}
