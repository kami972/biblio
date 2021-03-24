package Schemas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Exemplaire {
	private int idExemplaire;
	private LocalDate dateAchat;
	private EnumStatusExemplaire status;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String isbn;
	
	public Exemplaire(int idExemplaire,String dateAchat,EnumStatusExemplaire status,String isbn) {
		this.isbn=isbn;
		this.setIdExemplaire(idExemplaire);
		this.setDateAchat(dateAchat);
		this.setStatus(status);
		
	}

	@Override
	public String toString() {
		return "Exemplaire [idExemplaire = " + idExemplaire + ", dateAchat=" + dateAchat + ", status=" + status + ", isbn=" + isbn + "]";
	}

	public String getIsbn() {
		
		return isbn;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = LocalDate.parse(dateAchat,df);
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public EnumStatusExemplaire getStatus() {
		return status;
	}

	public void setStatus(EnumStatusExemplaire status) {
		this.status=status;
	}
	

	
	
}