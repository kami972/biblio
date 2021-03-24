package Control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.EmpruntEncoursDao;
import DAO.ExemplairesDAO;
import DAO.PingJDBC;
import DAO.UtilisateurDao;
import Schemas.EmpruntEnCours;
import Schemas.EmpruntEnCoursDb;

public class EmprunterCtl {
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {

		
		System.out.println("\n-------------Test 2.3 :Création d'un emprunt en cours pour un Employé ou un Adhérent avec règles métier-----------------------");
		
		
		int z=0;
		
		while(z==0) {
		
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous faire l'emprunt d'un exemplaire ? ", "Emprunt d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
			
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
		

	}
	
	public static String creaemprunt(String b,String a) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if (!(b.isBlank() || a.isBlank())) {
		UtilisateurDao utilisateur2 = new UtilisateurDao(PingJDBC.getConnectionByProperties());
		ExemplairesDAO exemplaire2 = new ExemplairesDAO(PingJDBC.getConnectionByProperties());
		EmpruntEncoursDao eecd = new EmpruntEncoursDao(PingJDBC.getConnectionByProperties());
		eecd.insertEmpruntEnCours(new EmpruntEnCours(utilisateur2.findByKey(Integer.parseInt(a)),exemplaire2.findByKey(Integer.parseInt(b))));
		
		UtilisateurDao utilisateur6 = new UtilisateurDao(PingJDBC.getConnectionByProperties());
		EmpruntEncoursDao eecd3 = new EmpruntEncoursDao(PingJDBC.getConnectionByProperties());
		String resultat = "\nListe des emprunts en cours de l'emprunteur "+a+" : \n";
		for(EmpruntEnCoursDb v : eecd3.findByUtilisateur(utilisateur6.findByKey(Integer.parseInt(a)))) {
			resultat = resultat +"Exemplaire id : "+v.getIdUtil()+"\n";
		}
		return resultat;
		}
		return "Renseignez les champs ID de l'exemplaire et de l'ID de l'utilisateur s.v.p. !";
	}

}
