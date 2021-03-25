package Test;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import DAO.EmpruntEncoursDao;
import DAO.ExemplairesDAO;
import DAO.PingJDBC;
import DAO.UtilisateurDao;
import Schemas.EmpruntEnCours;
import Schemas.EmpruntEnCoursDb;
import Schemas.Exemplaire;

public class TestDeBase {

public static void main(String[] args) throws HeadlessException, IOException, NumberFormatException, SQLException {
		
		System.out.println("\n\n-------------Test 2.1 : Connection à la base avec le user Bibliothecaire-----------------------\n\n");
		int z=0, y=0;
		Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
		if(PingJDBC.getConnectionByProperties() != null) 
		{
			JOptionPane.showMessageDialog(null, "Vous êtes connecté(e) à la base de données de la bibliothèque avec l'utilisateur : "+properties.getProperty("user"),
			"Etat de la connection à la base de données" , 
			JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		
		System.out.println("\n\n-------------Test 2.2 : Demande des objets aux DAO-----------------------\n\n");
		
		z=0;
		y=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous consulter un exemplaire ? ", "Consultation d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
		
		ExemplairesDAO exemplaire1 = new ExemplairesDAO(PingJDBC.getConnectionByProperties());
		
		String h = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire ( de 1 à 8 ): ","Recherche d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("\nDemande d'exemplaire n°"+(++y)+" : "+exemplaire1.findByKey(Integer.parseInt(h)));
		
		}
		
		z=0;
		
		y=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous consulter un utilisateur ? ", "Consultation d'utilisateur", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
			
			UtilisateurDao utilisateur1 = new UtilisateurDao(PingJDBC.getConnectionByProperties());
			String k = JOptionPane.showInputDialog(null, "Entrez l'ID de l'utilisateur que vous souhaitez consulter (ex Employe = 2,3,6 ou Adherent = 1,4,5,7,8,9) : ","Recherche d'un utilisateur", JOptionPane.INFORMATION_MESSAGE); 
			System.out.println("\nDemande d'utilisateur n°"+(++y)+" : "+utilisateur1.findByKey(Integer.parseInt(k)).toString());
		
		
		}
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bientôt !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);
		
		
	
		
System.out.println("\n-------------Test 2.3 :Création d'un emprunt en cours pour un Employé ou un Adhérent avec règles métier-----------------------");
		
		
		int z2=0;
		
		while(z2==0) {
		
			z2=JOptionPane.showConfirmDialog(null, "Voulez-vous faire l'emprunt d'un exemplaire ? ", "Emprunt d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z2==1 ) continue;
			
		UtilisateurDao utilisateur2 = new UtilisateurDao(PingJDBC.getConnectionByProperties());
		ExemplairesDAO exemplaire2 = new ExemplairesDAO(PingJDBC.getConnectionByProperties());
		String a = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (ex Employe = 2,3,6 ou Adherent = 1,4,5,7,8) : ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		String b = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire emprunté ( de 1 à 8 ): ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEncoursDao eecd = new EmpruntEncoursDao(PingJDBC.getConnectionByProperties());
		eecd.insertEmpruntEnCours(new EmpruntEnCours(utilisateur2.findByKey(Integer.parseInt(a)),exemplaire2.findByKey(Integer.parseInt(b))));
		EmpruntEncoursDao eecd3 = new EmpruntEncoursDao(PingJDBC.getConnectionByProperties());
		System.out.println("\nListe des emprunts en cours de l'emprunteur "+a+" : \n");
		for(EmpruntEnCoursDb v : eecd3.findByUtilisateur(utilisateur2.findByKey(Integer.parseInt(a)))) {
			System.out.println("Exemplaire id : "+v.getIdUtil()+"\n");
		}
		
		ExemplairesDAO eecd6 = new ExemplairesDAO(PingJDBC.getConnectionByProperties());

		System.out.println("\nEtat de l'exemplaire emprunté : " + eecd6.findByKey(Integer.parseInt(b)).toString());
					
		}
		
		
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bientôt !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);

	
		System.out.println("\n-------------Test 2.4 :Retour d'un emprunt en cours pourun Employé ou un Adhérent-----------------------");
		
		int z3=0;
		
		while(z3==0) {
			
			z3=JOptionPane.showConfirmDialog(null, "Voulez-vous faire la restitution d'un exemplaire ? ", "Restitution d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z3==1 ) continue;
		
		new ExemplairesDAO(PingJDBC.getConnectionByProperties());
		String c = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire restitué ( de 1 à 8 ): ","Retour d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEncoursDao eecd4 = new EmpruntEncoursDao(PingJDBC.getConnectionByProperties());
		eecd4.removeEmpruntEnCours(Integer.parseInt(c));
		
		ExemplairesDAO eecd5 = new ExemplairesDAO(PingJDBC.getConnectionByProperties());
		System.out.println("\nListe des exemplaires disponibles : \n");
		for(Exemplaire w : eecd5.findAll()) {
			if ( w.getStatus().toString().equalsIgnoreCase("DISPONIBLE")) System.out.println("Exemplaire id : "+w+"\n");
		}
		
		z=JOptionPane.showConfirmDialog(null, "Voulez-vous continuer la restitution d'exemplaire ? ", "Restitution d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		}
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bientôt !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);
		}
}
