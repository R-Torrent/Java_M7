package M7_Milestone3Fase1.vehicles.domain;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Conductor extends Persona {
	
	protected Llicencia license;
	protected List<Vehicle> drivenVehicles;
	
	public Conductor(String nom, String cognom, LocalDate naixement) throws Exception {
		super(nom, cognom, naixement);
		this.license = null;
		drivenVehicles = new ArrayList<>();
	}
	
	public void setLlicencia(Llicencia license) throws Exception {
		if(license == null) throw new Exception("Driver's license missing.");
		
		this.license = license;
	}
	
	public Llicencia getLlicencia() {
		return license;
	}
	
	public List<Vehicle> getDrivenVehicles(){
		return drivenVehicles;
	}

	@Override
	public String toString() {
		return super.toString() + (license == null ? "" : " " + license.toString());
	}
	
	@Override
	public String showElement() {
		if(license == null)
			return super.showElement();

		StringJoiner text = new StringJoiner("\n");
		text.add(super.showElement());
		text.add("\n" + license.showElement());
		
		if(!drivenVehicles.isEmpty()) {
			text.add("Condueix " + drivenVehicles.size() + " vehicle/s:");
			drivenVehicles.forEach(d -> text.add("    " + d.toString()));
		}
		
		return text.toString();
	}
	
}
