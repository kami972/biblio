//Source file: C:\\Users\\Cedric\\Desktop\\UML-SQL\\Bibliotheque_V2\\Schemas\\Personne.java

package Schemas;


public class Personne 
{
   private String nom;
   private String prenom;
   private Date dateNaissance;
   private String sexe;
   
   /**
    * @roseuid 604209D101D5
    */
   public Personne(String nom, String prenom,Date dateNaissance,String sexe) 
   {
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaissance;
   }
}
