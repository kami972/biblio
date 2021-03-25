package Schemas;

import java.util.Date;

public class Employe extends Utilisateur
{
	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;
	
	
	public Employe(int idUtilisateur,String nom, String prenom,String dateNaissance,String sexe, String pwd, String pseudonyme, String codeMatricule, EnumCategorieEmploye categorieEmploye)
	{
		super(idUtilisateur,nom, prenom, dateNaissance,sexe,pwd, pseudonyme);
		setCodeMatricule(codeMatricule);
		setCategorieEmploye(categorieEmploye);
	}


	@Override
	public String toString() {
		return super.toString()+"  Matricule = " + codeMatricule + ", categorie de l'employe = " + categorieEmploye + ".";
	}


	public String getCodeMatricule() {
		return codeMatricule;
	}


	public void setCodeMatricule(String codeMatricule) {
		this.codeMatricule = codeMatricule;
	}


	public EnumCategorieEmploye getCategorieEmploye() {
		return categorieEmploye;
	}


	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}
}