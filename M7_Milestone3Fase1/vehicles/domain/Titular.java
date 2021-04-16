package M7_Milestone3Fase1.vehicles.domain;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Titular extends Conductor {
	
	protected boolean insurance;
	protected boolean parkingLot;
	protected List<Vehicle> ownedVehicles;
	
	public Titular(String nom, String cognom, LocalDate naixement) throws Exception {
		super(nom, cognom, naixement);
		ownedVehicles = new ArrayList<>();
	}
	
	public Titular(String nom, String cognom, LocalDate naixement, boolean insurance, boolean parkingLot) throws Exception {			
		this(nom, cognom, naixement);
		this.insurance = insurance;
		this.parkingLot = parkingLot;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
	public void setParkingLot(boolean parkingLot) {
		this.parkingLot = parkingLot;
	}
	
	public List<Vehicle> getOwnedVehicles(){
		return ownedVehicles;
	}
	
	@Override
	public String toString() {
		return super.toString() + " <Titular>";
	}
	
	@Override
	public String showElement() {
		StringJoiner text = new StringJoiner("\n");
		text.add(super.showElement());
		text.add("\n** Titular de vehicle **");
		text.add("Assegurança: " + insurance);
		text.add("Garatge propi: " + parkingLot);
		
		if(!ownedVehicles.isEmpty()) {
			text.add("Propietari de " + ownedVehicles.size() + " vehicle/s:");
			ownedVehicles.forEach(v -> text.add("    " + v.toString()));
		}
		
		return text.toString();
	}
	
}
