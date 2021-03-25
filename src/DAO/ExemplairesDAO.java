package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Schemas.EnumStatusExemplaire;
import Schemas.Exemplaire;
//Cette Classe permet l'accès aux données (dao)
//version "Mock object" : objet bouchon pour les tests
public class ExemplairesDAO {
	static Connection cnx1 = null;
	
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public ExemplairesDAO (Connection cnx1)
	{
		this.cnx1 =cnx1;
	}
	
	
	
//	
//	public static Exemplaire[] exemplaireDB = {
//				new Exemplaire (1,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az023l"),
//				new Exemplaire (2,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az025p"),
//				new Exemplaire (3,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"az026a"),
//				new Exemplaire (4,"11/08/2010",EnumStatusExemplaire.DISPONIBLE,"ab457i")
//			};
//	private static Exemplaire[] requete = new Exemplaire[2];

	
	public static Exemplaire findByKey(int idExemplaire) throws SQLException 
	{
		Statement stmt1 = cnx1.createStatement();
		ResultSet rs2 = stmt1.executeQuery(
				"select *  FROM EXEMPLAIRE WHERE idexemplaire = " + idExemplaire);			
		Exemplaire ex = null;
		
		boolean next = rs2.next();
	

		if( next ) {
			int idexemplaire = rs2.getInt("idexemplaire");
			String status = rs2.getString("status");
			String dateachat = rs2.getDate("dateachat").toString(); 
			String isbn = rs2.getString("isbn");
			EnumStatusExemplaire enumStatus = EnumStatusExemplaire.valueOf(status);
			//Livre livre = null; // Lazy-loading //chargement tardif
			ex = new Exemplaire( idexemplaire, dateachat, enumStatus, isbn); 
		}
		else {
			ex = null;
		}
		
		
		return ex;
	}
		
	public ArrayList<Exemplaire> findAll() throws SQLException
	{
		Statement stmt1 = cnx1.createStatement();
		ArrayList <Exemplaire> listeExemplaire= new ArrayList<Exemplaire>();
		ResultSet rs3 = stmt1.executeQuery("select * FROM exemplaire");			
		while( rs3.next()){
			
			int idexemplaire = rs3.getInt(1); //  corrigé
			String dateachat=rs3.getDate(2).toString(); // corrigé !!!
			String isbn = rs3.getString(4);
			String status = rs3.getString(3);
			EnumStatusExemplaire enstex = EnumStatusExemplaire.valueOf(status);
			
			//System.out.println("ID Exemplaire : " + idexemplaire +" Status : "+ status );
			//Livre livre=new Livre();
			Exemplaire ex = new Exemplaire(idexemplaire,dateachat,enstex,isbn);//mapping Objet Relationel
			listeExemplaire.add(ex);
			
		}
		
		return listeExemplaire;
	}
	
}