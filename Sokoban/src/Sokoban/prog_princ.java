package Sokoban;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class prog_princ {
	public static void main(String[] args) {
		try {
			// lancement de la fenêtre principale
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
		
		/*
		 * public static void main(String[] args) { try { // create our mysql database
		 * connection String myDriver = "com.mysql.cj.jdbc.Driver"; String myUrl =
		 * "jdbc:mysql://localhost:8889/desamiantageMazet"; Class.forName(myDriver);
		 * Connection conn = DriverManager.getConnection(myUrl, "root", "root");
		 * 
		 * // our SQL SELECT query. // if you only need a few columns, specify them by
		 * name instead of using "*" String query = "SELECT * FROM poste";
		 * 
		 * // create the java statement Statement st = conn.createStatement();
		 * 
		 * // execute the query, and get a java resultset ResultSet rs =
		 * st.executeQuery(query);
		 * 
		 * // iterate through the java resultset while (rs.next()) { int id =
		 * rs.getInt("IdPoste"); String intitule = rs.getString("IntitulePoste");
		 * System.out.format("%s, %s\n", id, intitule); } st.close(); } catch (Exception
		 * e) { System.err.println("Got an exception! ");
		 * System.err.println(e.getMessage()); } }
		 */
		
	}

}
