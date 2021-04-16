package M7_Milestone3Fase1.vehicles.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import M7_Milestone3Fase1.vehicles.persistence.WheelDistribution;

public abstract class Vehicle extends Elemento {
	
	private static int COUNTER = 0;
	
	// Configuració per defecte de 'axles' (seqüència ordenada de 1, 2, 4):
	public static final List<Integer> DEFAULTAXLES = List.of(2, 2);
	// Se admiten vehículos desde 'minAxles' ejes:
	public static final int MINAXLES = 1;
	// Se admiten vehículos con un máximo de 'maxAxles' ejes (0 = sin límite):
	public static final int MAXAXLES = 0;
	// Incorporar las nuevas clases de vehículos a esta lista:
	public static final List<String> vehicleNameList = List.of("Car", "Bike", "Truck");
	
	protected final String brand;
	protected final String color;
	protected final List<Integer> wheelsPerAxle;
	protected final Persona owner;
	protected final List<Persona> drivers;
	
	protected WheelDistribution completeWheelSet;

	public Vehicle(String plate, String brand, String color, List<Integer> wheelsPerAxle, Persona owner, List<Persona> drivers) throws Exception {
		super(plate); // buscadorStr = plate
		if(plate == null || plate.isBlank()) throw new Exception("Plate number missing.");
		if(brand == null || brand.isBlank()) throw new Exception("Car brand missing.");
		if(color == null || color.isBlank()) throw new Exception("Car color missing.");
		if(wheelsPerAxle == null || wheelsPerAxle.isEmpty()) throw new Exception("Wheel-to-axle configuration missing.");
		for(Integer axle : wheelsPerAxle)
			if(axle == null)  throw new Exception("Axle missing wheels.");
		if(owner == null) throw new Exception("Vehicle owner missing.");
		if(drivers == null || drivers.isEmpty()) throw new Exception("Driver list missing.");
		
		this.brand = brand;
		this.color = color;
		this.wheelsPerAxle = wheelsPerAxle;
		this.owner = owner;
		this.drivers = drivers;
		idElemento = COUNTER++;
		
		((Titular)owner).ownedVehicles.add(this);
		drivers.forEach(d -> ((Conductor)d).drivenVehicles.add(this));
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.getClass().getSimpleName();
	}
	
	public String showElement() {
		StringJoiner text = new StringJoiner("\n");
		text.add("Categoria: " + this.getClass().getSimpleName());
		text.add("Matrícula: " + buscadorStr);
		text.add("Marca: " + brand);
		text.add("Color: " + color);
		text.add("Rodes per eix: " + wheelsPerAxle);
		
		text.add("\nPropietari: " + owner.toString());
		text.add("Conduït per " + drivers.size() + " persona/es:");
		drivers.forEach(d -> text.add("    " + d.toString()));
		
		if(completeWheelSet != null) {
			text.add("\n** Rodes montades **");
			for(int i = 0; i < wheelsPerAxle.size(); i++) {
				text.add("Eix " + (i+1) + ":");
				AxleTypes axleType = null;
				for(AxleTypes aT : AxleTypes.values())
					if(Integer.valueOf(aT.number()).equals(wheelsPerAxle.get(i))) {
						axleType = aT;
						break;
					}
				ArrayList<Wheel> wheels = completeWheelSet.getCompleteAxleConfig().get(i);
				
				for(int j = 0; j < axleType.number(); j++) {
					text.add("    " + axleType.wheelLabels.get(j) + ": " + wheels.get(j).showElement());
				}
			}				
		}
		
		return text.toString();
	}
	
	public List<Integer> getWheelsPerAxle() {
		return wheelsPerAxle;
	}
	
	public void setWheelDistribution(WheelDistribution completeWheelSet) {
		this.completeWheelSet = completeWheelSet;
	}
		
}
