package Control;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import DAO.EmpruntEncoursDao;
import DAO.ExemplairesDAO;
import DAO.PingJDBC;
import DAO.UtilisateurDao;
import Schemas.EmpruntEnCoursDb;
import Schemas.Exemplaire;
import Schemas.Utilisateur;

public class ConsulterCtl {
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
}
	
	public static String connectbase() throws IOException {
		Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("biblio_base\\DAO\\jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
	      return properties.getProperty("user");
		
	}
	
	public static String consultexemplaire(String h) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if(!h.isBlank()) {
		ExemplairesDAO exemplaire1 = new ExemplairesDAO(PingJDBC.getConnectionByProperties());
		if(exemplaire1.findByKey(Integer.parseInt(h))==null ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
				return exemplaire1.findByKey(Integer.parseInt(h)).toString();
		}
		return "Renseignez le champs ID Exemplaire, s.v.p. !";
	}
	
	public static String consultutilisateur(String k) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if(!k.isBlank()) {
		String o="";
		UtilisateurDao utilisateur1 = new UtilisateurDao(PingJDBC.getConnectionByProperties());
		if(utilisateur1.findByKey(Integer.parseInt(k))==null ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		o=utilisateur1.findByKey(Integer.parseInt(k)).toString();
		if(utilisateur1.findByKey(Integer.parseInt(k)).getCategorieUtilisateur().equalsIgnoreCase("ADHERENT")) {
		
			EmpruntEncoursDao eecd6 = new EmpruntEncoursDao(PingJDBC.getConnectionByProperties());
			for(EmpruntEnCoursDb s : eecd6.findByUtilisateur(utilisateur1.findByKey(Integer.parseInt(k)))){
				
				o=o+"\n\nExemplaire(s) en retard à rendre au plus vite :\n\n ";
				for (Exemplaire g : eecd6.controlRetard(Integer.parseInt(k))){
					o=o+g+"\n";
				}
			}
		}
		return o;
		}
		return "Renseignez le champs ID Utilisateur, s.v.p. !";
		}
	
	public static String nbE() throws IOException {
		String h="";
		UtilisateurDao utilisateur7 = new UtilisateurDao(PingJDBC.getConnectionByProperties());
		if(utilisateur7.findAll()==null) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		for(Utilisateur u : utilisateur7.findAll()){
			if ( u.getCategorieUtilisateur().contains("EMPLOYE")) h=h+u.getidUtilisateur()+" ";
		}
		
		return h;
	}
	public static String nbA() throws IOException {
		String i="";
		UtilisateurDao utilisateur6 = new UtilisateurDao(PingJDBC.getConnectionByProperties());
		if(utilisateur6.findAll()==null) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		for(Utilisateur w : utilisateur6.findAll()){
			if ( w.getCategorieUtilisateur().equalsIgnoreCase("ADHERENT")) i=i+w.getidUtilisateur()+" ";
		}
		if(i.equals("") ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		return i;
	}
	public static String nbL() throws IOException, SQLException {
		String j="";
		ExemplairesDAO ex6 = new ExemplairesDAO(PingJDBC.getConnectionByProperties());
		if(ex6.findAll()==null) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		for(Exemplaire v : ex6.findAll()){
			j=j+v.getIdExemplaire()+" ";
		}
		if(j.equals("") ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		return j;
	}
}
