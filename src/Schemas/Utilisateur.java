package Schemas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Utilisateur extends Personne
{
	
	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;	
	public static ArrayList<EmpruntEnCours> emprunt = new ArrayList<>();
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy",Locale.FRANCE);
	public EmpruntArchive stock;
	private EnumCategorieEmploye categorieEmploye;
	private String catUtil = " EMPLOYE";




//	public Utilisateur(String nom, String prenom, String dateNaissance, String sexe,int idUtilisateur, String pwd, String pseudonyme)
//	{
//		super(nom,prenom, dateNaissance, sexe);
//		this.idUtilisateur = idUtilisateur;
//		this.pwd = pwd;
//		this.pseudonyme = pseudonyme;
//
//	}
//	
public Utilisateur(int idUtilisateur,String nom, String prenom, String dateNaissance, String sexe, String pwd, String pseudonyme)
	{
		super(nom,prenom, dateNaissance, sexe);
		setidUtilisateur(idUtilisateur);
		setpwd(pwd);
		setpseudonyme(pseudonyme);

	}
	


	public Utilisateur() {
		
	}
	

	@Override
	public String toString() {
		return "Nom = " + getNom() + ", Prenom = " + getPrenom() +  ", genre = " + getSexe()+", identifiant de l'utilisateur = " + idUtilisateur + ", mot de passe= " + pwd + ", pseudo = " + pseudonyme;
	}


    public static ArrayList<EmpruntEnCours> getEmprunt() {
		return emprunt;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return super.getNom();
	}


	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		super.setNom(nom);
	}


	@Override
	public String getPrenom() {
		// TODO Auto-generated method stub
		return super.getPrenom();
	}


	@Override
	public void setPrenom(String prenom) {
		// TODO Auto-generated method stub
		super.setPrenom(prenom);
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	public void addEmpruntEnCours (EmpruntEnCours ep) throws Exception
	{
		
			//System.out.println("je passe par utilisateur");
			 emprunt.add(ep);
			 
	}

	
	private Exception BiblioException(String string) {
		// TODO Auto-generated method stub
		return null;
	}




	public ArrayList<EmpruntEnCours> getEmpruntenCours()
	{
		return emprunt;
	}

	public int getNbEmpruntsEnCours()
	{
		int j=0;
		for (int i=0; i<emprunt.size(); i++)
		{
		if(getEmpruntenCours().get(i).getUtilisateur().getidUtilisateur()==(this.getidUtilisateur()))j++;
		}
	 return j;
	}
	
	public static void setEmprunt(ArrayList<EmpruntEnCours> emprunt) {
		Utilisateur.emprunt = emprunt;
	}

	public void removeEmprunt(int id) {
		int j= 0;
		for(EmpruntEnCours o : emprunt) {
            if(o.getExemplaire().getIdExemplaire()==id) {
            	j =emprunt.indexOf(o);
            	o.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
            	 stock = new EmpruntArchive(emprunt.get(j), LocalDate.now().format(df));
            }
            
        }
		
        System.out.println("Archive cr�e : "+stock.getExemplaire().getIdExemplaire()+" a �t� emprunt� le : "+stock.getDateEmprunt().format(df)+ " , et restitu� le :"+stock.getDateRestitutionEff().format(df));
       
        emprunt.remove(j);
        
    }


	public int getidUtilisateur() {return idUtilisateur;}
	public String getpwd() {return pwd;}
	public String getpseudonyme() {return pseudonyme;}
	
	public void setidUtilisateur(int idUtilisateur) {this.idUtilisateur = idUtilisateur;}
	public void setpwd(String pwd) {this.pwd = pwd;}
	public void setpseudonyme(String pseudonyme) {this.pseudonyme = pseudonyme;}
	public EnumCategorieEmploye getCategorieEmploye() {return categorieEmploye;}
	
	
	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}



	public String getCategorieUtilisateur() {
		return catUtil;
	}



	public void setCategorieUtilisateur(String categorieUtilisateur) {
		this.catUtil = categorieUtilisateur;
	}
	public static void main(String[] args) {
		
	}




}