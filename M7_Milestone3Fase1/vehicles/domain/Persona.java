package M7_Milestone3Fase1.vehicles.domain;

import java.time.LocalDate;

import java.util.StringJoiner;

public abstract class Persona extends Elemento {
	
	private static int COUNTER = 0;
	
	protected String nom;
	protected LocalDate naixement;

	public Persona(String nom, String cognom, LocalDate naixement) throws Exception {
		super(cognom); // buscadorStr = cognom
		
		if(nom == null || nom.isEmpty()) throw new Exception("Forename missing.");
		if(cognom == null || cognom.isEmpty()) throw new Exception("Surname missing.");
		if(naixement == null) throw new Exception("Birthdate missing.");
		
		this.nom = nom;
		this.naixement = naixement;
		idElemento = COUNTER++;
	}
	
	@Override
	public String toString() {
		return buscadorStr + ", " + nom + " (#" + String.format("%05d", idElemento) + ")";
	}
	
	public String showElement() {
		StringJoiner text = new StringJoiner("\n");
		text.add("Nom: " + nom);
		text.add("Cognom: " + buscadorStr);
		text.add("Data de naixement: " + naixement.toString());
		
		return text.toString();
	}
	
}
