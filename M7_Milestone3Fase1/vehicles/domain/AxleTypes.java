package M7_Milestone3Fase1.vehicles.domain;

import java.util.List;

public enum AxleTypes {

	One(1), Two(2), Four(4);
	
	protected final int n;
	protected final List<String> wheelLabels;
	
	AxleTypes(int n){
		this.n = n;
		switch(n) {
		case(1):
			this.wheelLabels = List.of("central wheel");
			break;
		case(2): default:
			this.wheelLabels = List.of("left wheel", "right wheel");
			break;
		case(4):
			this.wheelLabels = List.of("outer left wheel", "inner left wheel", "inner right wheel", "outer right wheel");
			break;
		}
	}
	
	public int number() { return n; }
	
	public List<String> labels() { return wheelLabels; }
	
}
