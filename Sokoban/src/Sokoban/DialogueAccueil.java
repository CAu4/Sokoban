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

public class DialogueAccueil extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public DialogueAccueil() {
		setBounds(100,100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label = new JLabel(); //JLabel Creation
		ImageIcon img = new ImageIcon("/Users/justinemoulin/Sokoban2/images/menu.jpg");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(700, 500,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon imageFinale = new ImageIcon(newimg);
		
		label.setIcon(imageFinale);
        label.setBounds(0, 0, 700, 500);
 
        contentPanel.add(label);
        this.setVisible(true);
		
        getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton jouerButton = new JButton("Jouer");
				jouerButton.setActionCommand("Jouer");
				buttonPane.add(jouerButton);
				jouerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DialogueUsername username = new DialogueUsername();
						dispose();
					}
				});
				
			}
			
		}
	}

}
