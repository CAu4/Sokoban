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
import javax.swing.JTextPane;

public class DialogueNiveaux extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FenetrePrincipale f;

	/**
	 * Create the dialog.
	 */
	public DialogueNiveaux(int niv) {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel labelNivSuivant = new JLabel();
			if (niv == 3)
			{
				JLabel label = new JLabel(); //JLabel Creation
				ImageIcon img = new ImageIcon("/Users/justinemoulin/Sokoban2/images/fin.jpg");
				Image image = img.getImage(); // transform it 
				Image newimg = image.getScaledInstance(700, 500,  java.awt.Image.SCALE_SMOOTH);  
				ImageIcon imageFinale = new ImageIcon(newimg);
				
				label.setIcon(imageFinale);
		        label.setBounds(0, 0, 700, 500);
		 
		        contentPanel.add(label);
		        setBounds(100,100, 700, 500);
			}
			else
			{
				setBounds(100, 100, 250, 200);
				labelNivSuivant.setText("Bravo !\nTu as réussi !\nVeux-tu passer au niveau suivant ?");
				labelNivSuivant.setBounds(52, 24, 61, 16);
			}
				
			
			contentPanel.add(labelNivSuivant);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				if (niv != 3)
				{
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							f = new FenetrePrincipale(niv);
							f.setLayout(null);
							f.setVisible(true);
							dispose();
						}
					});
				}
				
			}
			{
				JButton quitterButton = new JButton("Quitter");
				quitterButton.setActionCommand("Quitter");
				buttonPane.add(quitterButton);
				quitterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

}
