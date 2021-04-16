package M7_Milestone3Fase1.vehicles.domain;

import java.util.List;

public class Bike extends Vehicle {

	public static final List<Integer> DEFAULTAXLES = List.of(1, 1);
	public static final int MINAXLES = 2;
	public static final int MAXAXLES = 3;
	
	public Bike(String plate, String brand, String color,  List<Integer> wheelsPerAxle, Persona owner, List<Persona> drivers) throws Exception {
		super(plate, brand, color, wheelsPerAxle, owner, drivers);
	}

}
