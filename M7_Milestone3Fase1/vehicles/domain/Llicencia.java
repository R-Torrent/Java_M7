package M7_Milestone3Fase1.vehicles.domain;

import java.time.LocalDate;

import java.util.StringJoiner;

public class Llicencia extends Elemento {
	
	private static int COUNTER = 0;
	
	protected Conductor titular;
	protected LocalDate caducitat;
	protected Boolean[] permits;
	
	public Llicencia(Conductor titular, LocalDate caducitat, Boolean[] tipus) throws Exception {
		super("");
		if(titular == null) throw new Exception("Driver missing.");
		if(caducitat == null) throw new Exception("Data of expiry missing.");
		if(tipus == null) throw new Exception("Vehicle type array missing.");
		
		this.titular = titular;
		this.caducitat = caducitat;
		this.permits = tipus;
		idElemento = COUNTER++;
	}
	
	@Override
	public String toString() {
		return permitsString(" ", "[ ", " ]");
	}
	
	private String permitsString(CharSequence ...parameters) {
		int numPermits = Vehicle.vehicleNameList.size();
		
		StringJoiner permitsString;
		switch(parameters.length) {
		case(1): default:
			permitsString = new StringJoiner(parameters[0]);
			break;
		case(3):
			permitsString = new StringJoiner(parameters[0], parameters[1], parameters[2]);
			break;
		}
		
	    for(int i = 0; i < numPermits ; i++)
	    	if(permits[i])
	    		permitsString.add(Vehicle.vehicleNameList.get(i));
	    
	    return permitsString.toString();
	}
	
	@Override
	public String showElement() {
		StringJoiner text = new StringJoiner("\n");
		text.add("** Permís de conduir (#" + String.format("%05d", idElemento) + ") **");
		text.add("Data de caducitat: " + caducitat.toString());
		text.add("Llicència/es: " + permitsString(", "));
		
		return text.toString();
	}
	
	public Boolean[] getPermits() {
		return permits;
	}

}
