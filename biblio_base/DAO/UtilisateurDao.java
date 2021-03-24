package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import Schemas.Adherent;
import Schemas.Employe;
import Schemas.EnumCategorieEmploye;
import Schemas.Utilisateur;

public class UtilisateurDao 
{
	 Connection con = null;
	
	public UtilisateurDao(Connection con)
	{
		this.con=con;
	}
	
	public UtilisateurDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	
//	public Utilisateur [] utilisateurDB =	
//		{
//				new Adherent("Moris", "Eléanor", "20/01/2021", "Femme", 1,"123quatre","Premier1", "0643 55 73 29"),
//				new Adherent("Thomson", "Solan", "30/03/2019", "Homme", 2,"45ji","Solan77", "0589 65 04 13"),
//				new Employe("Miral", "Paul", "12/04/1999", "Homme", 3,"emc2","Enstein555", "BU_Paris13_01 1", EnumCategorieEmploye.BIBLIOTHECAIRE),
//				new Employe("Liran", "Marcella", "15/04/2018", "Femme", 4,"super4","Khara El", "BU_Paris13_01 2", EnumCategorieEmploye.RESPONSABLE)
//				
//		};
	
	public Utilisateur findByKey(int idUser) {
		PreparedStatement pstm,pstm2;
		Utilisateur user = null;
		int id = 0;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		String pseudo = "";
		String dn;
		String sex ="";
		try {
			
			pstm2 = con.prepareStatement("SELECT * FROM ADHERENTGENERAL");
			ResultSet ag = pstm2.executeQuery();
			ag.next();
			Adherent.nbMaxPrets= ag.getInt(1);
			Adherent.dureeMaxPrets = ag.getInt(2);
			pstm = con
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, utilisateur.pseudonyme, utilisateur.datenaissance, utilisateur.sexe, categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
			pstm.setInt(1, idUser);
			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(8);
				pseudo = result.getString(5);
				dn = result.getDate(6).toString();
				sex = result.getString(7);
				
				if (cat.equals("ADHERENT")) {
					tel = result.getString(9);
					
					user = new Adherent(id,nom, prenom, pwd,pseudo,dn,sex,cat, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(10);
					cat_employe = result.getString(11);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe);
					user = new Employe(id,nom, prenom, dn,sex, pwd, pseudo,code, cat2);
				}
				return user;
			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Utilisateur> findAll() {
		ArrayList<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		int id = 0;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String pseudo = "";
		String dn;
		String sex ="";
		String code = "";
		String cat_employe = "";
		try {
			Statement stm = con.createStatement();
			ResultSet result = stm
					.executeQuery("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, utilisateur.pseudonyme, utilisateur.datenaissance, utilisateur.sexe, categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+)");
			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(8);
				pseudo = result.getString(5);
				dn = result.getDate(6).toString();
				sex = result.getString(7);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(9);
					user = new Adherent(id,nom, prenom,  pwd,pseudo,dn,sex,cat, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(10);
					cat_employe = result.getString(11);					
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe);
					user = new Employe(id,nom, prenom, dn,sex, pwd, pseudo,code, cat2);
				}

				listUtilisateur.add(user);
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUtilisateur;
	}

	
//	public Utilisateur findByKey( int id){
//	    for (Utilisateur utilisateur : utilisateurDB) {
//	        if (utilisateur.getidUtilisateur()==id)
//	        	return utilisateur;
//	    }
//	    return null;
//	}
//
//	public List <Utilisateur> findAll() {
//		List <Utilisateur> userTrouves = new ArrayList <Utilisateur> ();
//		for (Utilisateur util : utilisateurDB) {
//			userTrouves.add(util);
//		}
//		return userTrouves;	
//	}



	 public static void main(String[] args) {
		
            
//			UtilisateurDao udao = new UtilisateurDao(con);
//			 
//			Iterator <Utilisateur> it;
//			 
//			System.out.println("-------------------------");
//			System.out.println("\"1\" trouvé:\n\t" + udao.findByKey(1) );
//			System.out.println("-------------------------");
//			
//			System.out.println("-------------------------");
//			System.out.println("\"1\" trouvé:\n\t" + udao.findByKey(3) );
//			System.out.println("-------------------------");
//			
//			System.out.println("-------------------------");
//			System.out.println("Voici tous les Utilisateurs");
//			System.out.println("-------------------------");
//			it = udao.findAll().iterator();
//			while( it.hasNext() ) 
//			{
//				System.out.println("\t" + it.next());
//			}
	}
}