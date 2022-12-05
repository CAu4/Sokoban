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

public class DialogueUsername extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernameField;

	/**
	 * Create the dialog.
	 */
	public DialogueUsername() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setVisible(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel usernameLabel = new JLabel("Entrez un pseudo :");
			contentPanel.add(usernameLabel);
		}
		{
			usernameField = new JTextField();
			contentPanel.add(usernameField);
			usernameField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton validerButton = new JButton("Valider");
				validerButton.setActionCommand("Valider");
				buttonPane.add(validerButton);
				validerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FenetrePrincipale frame = new FenetrePrincipale(0, usernameField.getText());
						frame.getContentPane().setLayout(null);
						frame.setVisible(true);
						dispose();
					}
				});

			}

		}
	}

}
