package M7_Milestone3Fase1.vehicles.domain;

import java.util.Locale;

public class Wheel extends Elemento {
	
	private static int COUNTER = 0;
	
	protected final String brand;
	protected final double diameter;

	public Wheel(String brand, double diameter) throws Exception {
		super("");
		if(brand == null || brand.isBlank()) throw new Exception("Wheel brand missing.");
		if(diameter <= 0.0) throw new Exception("Nonpositive wheel diameter.");
		
		this.brand = brand;
		this.diameter = diameter;
		idElemento = COUNTER++;
	}
	
	public String getWheelBrand() {
		return brand;
	}
	
	public double getWheelDiameter() {
		return diameter;
	}
	
	public String showElement() {
		return String.format(Locale.ROOT, "%s [ Ø%.3f ]", brand, diameter);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Wheel that = (Wheel)o;
		return brand.equals(that.brand) && diameter == that.diameter;
	}
	
}
