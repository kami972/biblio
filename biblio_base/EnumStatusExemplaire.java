//Source file: C:\\Users\\Cedric\\Desktop\\UML-SQL\\Bibliotheque_V2\\Schemas\\EnumStatusExemplaire.java

package Schemas;


public enum EnumStatusExemplaire 
{
   PRETE,DISPONIBLE,SUPPRIME;
	private boolean etat;
   
   private EnumStatusExemplaire(boolean etat) 
   {
    setEtat(etat);
   }
   
   private void setEtat(etat) {
	   this.etat=etat;
   }
}
