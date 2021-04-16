package M7_Milestone3Fase1.vehicles.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import M7_Milestone3Fase1.vehicles.domain.Wheel;

public class WheelDistribution {
	
	private List<ArrayList<Wheel>> completeAxleConfig = new ArrayList<ArrayList<Wheel>>();
	
	public WheelDistribution() {}
	
	public List<ArrayList<Wheel>> getCompleteAxleConfig(){
		return completeAxleConfig;
	}
	
	/*
	public static List<Integer> constructWheelsPerAxle(WheelDistribution config) throws Exception {
		if(config == null || config.getCompleteAxleConfig().isEmpty())
			throw new Exception("Complete wheel distribution nonexistent.");
		
		List<Integer> wheelsPerAxle = new ArrayList<>();
		for(List<Wheel> axle : config.getCompleteAxleConfig())
			wheelsPerAxle.add(axle.size());
		return wheelsPerAxle;
	}
	*/

	public static WheelDistribution createAxleConfig(List<Integer> wheelsPerAxle, List<Wheel> wheelList) throws Exception {
		final WheelDistribution dist = new WheelDistribution();
		if(wheelsPerAxle == null || wheelsPerAxle.isEmpty())
			throw new Exception("Wheel-to-axle configuration missing.");
		if(wheelList == null || wheelList.isEmpty())
			throw new Exception("List of mounted wheels missing.");
		
		Iterator<Wheel> it = wheelList.iterator();
		for(Integer wheels : wheelsPerAxle) {
			ArrayList<Wheel> axle = new ArrayList<>();
			switch(wheels) {
			case(1): // Eix de una roda
				if(it.hasNext()) axle.add(it.next());
				else throw new Exception ("Insuffcient wheels in the source list.");
				break;
			case(2): // Eix de dos rodes
				Wheel ltWheel;
				Wheel rtWheel;
				
				if(it.hasNext()) ltWheel = it.next();
				else throw new Exception ("Insuffcient wheels in the source list.");
				if(it.hasNext()) rtWheel = it.next();
				else throw new Exception ("Insuffcient wheels in the source list.");
				
				if(rtWheel.getWheelDiameter() != ltWheel.getWheelDiameter())
					throw new Exception("Rodes dreta i esquerra no coincideixen en diàmetre.");
				
				axle.add(ltWheel);
				axle.add(rtWheel);
				break;
			case(4): // Eix de quatre rodes
				Wheel outerLtWheel;
				Wheel innerLtWheel;
				Wheel innerRtWheel;
				Wheel outerRtWheel;				
				
				if(it.hasNext()) outerLtWheel = it.next();
				else throw new Exception ("Insuffcient wheels in the source list.");
				if(it.hasNext()) innerLtWheel = it.next();
				else throw new Exception ("Insuffcient wheels in the source list.");
				if(it.hasNext()) innerRtWheel = it.next();
				else throw new Exception ("Insuffcient wheels in the source list.");
				if(it.hasNext()) outerRtWheel = it.next();
				else throw new Exception ("Insuffcient wheels in the source list.");
				
				if(outerRtWheel.getWheelDiameter() != outerLtWheel.getWheelDiameter())
					throw new Exception("Rodes externes dreta i esquerra no coincideixen en diàmetre.");	
				if(innerRtWheel.getWheelDiameter() != innerLtWheel.getWheelDiameter())
					throw new Exception("Rodes internes dreta i esquerra no coincideixen en diàmetre.");
				if(innerLtWheel.getWheelDiameter() != outerLtWheel.getWheelDiameter())
					throw new Exception("Diàmetres dins el tàndem esquerre no coincideixen.");
				if(innerRtWheel.getWheelDiameter() != outerRtWheel.getWheelDiameter())
					throw new Exception("Diàmetres dins el tàndem dret no coincideixen.");
				
				axle.add(outerLtWheel);
				axle.add(innerLtWheel);
				axle.add(innerRtWheel);
				axle.add(outerRtWheel);
				break;
			default: throw new Exception("Illegal wheel number.");
			}
			dist.getCompleteAxleConfig().add(axle);
		}
		if(it.hasNext())
			throw new Exception("Surplus wheels in the source list.");
		return dist;
	}
	
}
