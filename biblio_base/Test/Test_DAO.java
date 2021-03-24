package Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Iterator;

import DAO.ExemplairesDAO;
import DAO.UtilisateurDao;
import Schemas.Adherent;
import Schemas.ConnectionFactory;
import Schemas.Employe;
import Schemas.EmpruntEnCours;
import Schemas.EnumCategorieEmploye;
import Schemas.EnumStatusExemplaire;
import Schemas.Exemplaire;
import Schemas.Utilisateur;

public class Test_DAO {
	
 private static Connection cnx1;
	public static void main(String[] args) throws Exception {
		
		ExemplairesDAO edao = new ExemplairesDAO(cnx1);
		// TestDeBase 1
		
		
		System.out.println("\t\t Test de Base 1.1");
		System.out.println("********************************************************\n");
		System.out.println("Demande de deux exemplaires par leur id aux Dao :\n");
		System.out.println("\t"+edao.findByKey(1));
		
		// TestDeBase /3
		
		UtilisateurDao udao = new UtilisateurDao(cnx1);
		 
		Iterator <Utilisateur> it;
		 
		System.out.println("********************************************************\n");
		System.out.println();
		System.out.println("Voici tous les Utilisateurs");
		System.out.println();
		it = udao.findAll().iterator();
		while( it.hasNext() ) 
		{
			System.out.println("\t" + it.next());
		}
		System.out.println();
		System.out.println("Demandes d'un adhérent par son id aux Dao : \n");
		System.out.println("\"1\" trouvé:\n\t" + udao.findByKey(1) );
		System.out.println("********************************************************\n");
		
		System.out.println();
		System.out.println("Demandes d'un employe par son id aux Dao : \n");
		System.out.println("\"1\" trouvé:\n\t" + udao.findByKey(3) );
		System.out.println("********************************************************\n");
		
		
		// TestDeBase 4/5
		System.out.println();
		System.out.println("\t\tCREATION D'UN EMPRUNT");
		
		
		Adherent Bob = new Adherent(111,"MO", "Bob", "azerrt", "Bob01", "01/03/2021", "Homme","A","056871369");
		Adherent Mia = new Adherent(112,"MO", "Mia","azert", "Mia01", "01/03/2021", "Femme","A", "056871599");
		System.out.println("\nCréation d'un nouvel Adhérent : "+Mia);
		Employe paul = new Employe(23,"Miral", "Paul", "20/01/1999", "Homme", "emc2","Enstein555", "BU_Paris13_01 1", EnumCategorieEmploye.BIBLIOTHECAIRE);
		System.out.println("\nCréation d'un nouvel Employé : "+paul+"\n");
		
		Exemplaire a = new Exemplaire (1,"09/03/2021",EnumStatusExemplaire.DISPONIBLE,"az027u");
		Exemplaire b = new Exemplaire (5,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az023l");
		Exemplaire c = new Exemplaire (2,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az025p");
		Exemplaire d = new Exemplaire (3,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az026a");
		Exemplaire e = new Exemplaire (4,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"ab457i");
		Exemplaire f = new Exemplaire (6,"09/03/2021",EnumStatusExemplaire.DISPONIBLE,"az098p");
		Exemplaire g = new Exemplaire (8,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az023l");
		Exemplaire h = new Exemplaire (7,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az025p");
		Exemplaire i = new Exemplaire (9,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az026a");
		Exemplaire j = new Exemplaire (10,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"ab457i");
		Exemplaire k = new Exemplaire (11,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"oi457i");
		
		System.out.println("\t\t Test adherent en retard 1.2");
		System.out.println("********************************************************\n");
	
		System.out.println("On ajoute un emprunt a l'adhérent Bob ");
		Bob.addEmpruntEnCours(new EmpruntEnCours(Bob, a));
		System.out.println("emprunt en cours : "+Bob.getNbEmpruntsEnCours());
		System.out.println();
		//System.out.println(a.getStatus());
		System.out.println("maintenant on fait l'ajout d'un emprunt en retard");
		Bob.addEmpruntEnCours(new EmpruntEnCours(Bob, b));
		System.out.println("emprunt en cours : "+Bob.getNbEmpruntsEnCours());
		System.out.println();
		
		System.out.println("Essaie d'un emprunt apres un emprunt en retard");
		Bob.addEmpruntEnCours(new EmpruntEnCours(Bob, c));
		System.out.println("emprunt en cours : "+Bob.getNbEmpruntsEnCours());
		System.out.println(Bob.getEmpruntenCours());
		System.out.println();
		
		
		System.out.println("\t\t Test employe en retard 1.3");
		System.out.println("********************************************************\n");
		
		System.out.println("On fait paul l'employe emprunter deux exemplaire en retard");
		paul.addEmpruntEnCours(new EmpruntEnCours(paul, h));
		paul.addEmpruntEnCours(new EmpruntEnCours(paul, i));	
		System.out.println("emprunt en cours : "+paul.getNbEmpruntsEnCours());
		
		
		System.out.println("\t\t Test 3 emprunt adherent 1.4");
		System.out.println("********************************************************\n");
		System.out.println("***On fait mia une adherente faire 3 autre emprunt ***");
		Mia.addEmpruntEnCours(new EmpruntEnCours(Mia, d));
		System.out.println("emprunt en cours : "+Mia.getNbEmpruntsEnCours());
		System.out.println();
		
		Mia.addEmpruntEnCours(new EmpruntEnCours(Mia, e));
		System.out.println("emprunt en cours : "+Mia.getNbEmpruntsEnCours());
		System.out.println();
		
		Mia.addEmpruntEnCours(new EmpruntEnCours(Mia, f));
		System.out.println("emprunt en cours : "+Mia.getNbEmpruntsEnCours());
		System.out.println();
		
		System.out.println("Ajout d'un 4 eme exemplaire");
		Mia.addEmpruntEnCours(new EmpruntEnCours(Mia, g));
		System.out.println("emprunt en cours : "+Mia.getNbEmpruntsEnCours());
		System.out.println();
		
		System.out.println("\t\t Test employe 3 emprunt 1.5");
		System.out.println("********************************************************\n");
		System.out.println("On fait paul emprunter 2 exemplaires supplementaires");
		paul.addEmpruntEnCours(new EmpruntEnCours(paul, j));
		paul.addEmpruntEnCours(new EmpruntEnCours(paul, k));
		System.out.println("emprunt en cours : "+paul.getNbEmpruntsEnCours());
		
		//test de base 6
		System.out.println("\n\t\t Test retour 1.6");
		System.out.println("********************************************************\n");
		System.out.println("Bob fait un retour de son exemplaire ayant pour id 1");
		
		System.out.println(" pour le moment Bob a emprunt en cours : "+Bob.getNbEmpruntsEnCours());
		System.out.println(" voici le status de l'exemplaire A ayant pour Id 1 : \n"+ a.getStatus());
		Bob.removeEmprunt(1);
		System.out.println("voici le status de l'exemplaire A ayant pour Id "+a.getIdExemplaire()+" apres retour  : \n"+a.getStatus());
		System.out.println("l'exemplaire ayant pour Id n'apparait plus dans les emprunt en cours : \n"+Bob.getEmpruntenCours());
		System.out.println();
		System.out.println("emprunt en cours  de bob : "+Bob.getNbEmpruntsEnCours());
		
	}
}
