package M7_Milestone3Fase1.vehicles.domain;

import java.util.List;

public class Truck extends Vehicle {
	
	public static final List<Integer> DEFAULTAXLES = List.of(2, 4, 4);
	public static final int MINAXLES = 2;
	public static final int MAXAXLES = 0;
	
	public Truck(String plate, String brand, String color,  List<Integer> wheelsPerAxle, Persona owner, List<Persona> drivers) throws Exception {
		super(plate, brand, color, wheelsPerAxle, owner, drivers);
	}

}
