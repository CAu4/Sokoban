package Sokoban;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class DialogueNiveaux extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FenetrePrincipale f;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public DialogueNiveaux(int niv, int score, String pseudo) {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			setBounds(100,100,300,300);
				
			// label bravo
			JLabel labelBravo = new JLabel();
			labelBravo.setText("Bravo ! Tu as réussi le niveau " + niv + " !");
			contentPanel.add(labelBravo);

			// image coupe
			ImageIcon img = new ImageIcon("/Users/justinemoulin/Sokoban2/images/coupe.png");
			Image image = img.getImage(); // transform it 
			Image newimg = image.getScaledInstance(30, 50,  java.awt.Image.SCALE_SMOOTH);  
			ImageIcon imageFinale = new ImageIcon(newimg);

			// label points
			JLabel labelPoint = new JLabel();
			labelPoint.setText("Tu as obtenu " + score + " points !");
			labelPoint.setIcon(imageFinale);
			contentPanel.add(labelPoint);

			// label liste des meilleurs scores
			JLabel labelListe = new JLabel();
			labelListe.setText("Meilleurs socres : ");
			contentPanel.add(labelListe);

			// Connexion à la base de donnée
			try {
				String myDriver = "com.mysql.cj.jdbc.Driver";
				String myUrl ="jdbc:mysql://localhost:8889/sokoban";
				Class.forName(myDriver);
				Connection conn = DriverManager.getConnection(myUrl, "root", "root");
				Statement st = conn.createStatement();

				// Récupération des scores associés au niveau niv
				String query = "SELECT * FROM scores WHERE niveau="+niv+" ORDER BY points DESC";
				ResultSet rs = st.executeQuery(query);
				
				boolean isBetter = false;
				int id = 0;
				while (rs.next()) {
					id = rs.getInt("id");
					int p = rs.getInt("points");
					if ((isBetter == false)&( score>=p )) {
						isBetter = true;
					}
				}
				// compte nombre de scores enregistrés pour niv 1
				String queryCount = "SELECT COUNT(*) FROM scores WHERE niveau="+niv;
				ResultSet rsCount = st.executeQuery(queryCount);
				int nbRows = 0;
				if (rsCount.next())
				{
					nbRows = rsCount.getInt("Count(*)");
				}

				if ((nbRows < 5)||(isBetter == true))
				{
					// insertion nouveau score
					String queryInsert = "INSERT INTO scores (`username`, `points`, `niveau`) VALUES ('"+pseudo+"', " + score +", " + niv + ")";
					st.executeUpdate(queryInsert);
					if (nbRows>=5)
					{
						String queryDelete = "DELETE FROM scores WHERE id = "+ id;
						st.executeUpdate(queryDelete);
					}
				}
				
				// comptage et comparaison
				int compteur = 0;

				String[][] donnees_scores = new String[5][2];
				
				String queryAffiche = "SELECT * FROM scores WHERE niveau="+niv+" ORDER BY points DESC";
				ResultSet rsAffiche = st.executeQuery(queryAffiche);
				while (rsAffiche.next()) {
					id = rsAffiche.getInt("id");
					int p = rsAffiche.getInt("points"); 
					String user = rsAffiche.getString("username");

					String data[] = {user,Integer.toString(p)};
					donnees_scores[compteur]=data;

					compteur = compteur + 1;
				}

				getContentPane().add(contentPanel, BorderLayout.CENTER);
				{

					String[] titreColonnes = {"pseudo", "points"}; 
					JTable table = new JTable(donnees_scores, titreColonnes);
					contentPanel.add(table);
				}
				st.close();

			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton suivantButton = new JButton("Niveau suivant");
				if (niv != 3)
				{
					buttonPane.add(suivantButton);
					getRootPane().setDefaultButton(suivantButton);
					suivantButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							f = new FenetrePrincipale(niv, pseudo);
							f.getContentPane().setLayout(null);
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
						DialogueFin fin = new DialogueFin();
						dispose();
					}
				});
			}
		}
	}

}
