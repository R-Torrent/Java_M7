package M7_Milestone3Fase1.vehicles.application;

import java.util.List;

import javax.swing.JOptionPane;

import M7_Milestone3Fase1.vehicles.domain.Conductor;
import M7_Milestone3Fase1.vehicles.domain.Llicencia;
import M7_Milestone3Fase1.vehicles.domain.Vehicle;

import M7_Milestone3Fase1.vehicles.persistence.Repository;
import M7_Milestone3Fase1.vehicles.persistence.WheelDistribution;

public class VehicleController {

	private Repository<Conductor> repositoryD = new Repository<>();
	private Repository<Llicencia> repositoryP = new Repository<>();
	private Repository<Vehicle> repositoryV = new Repository<>();
	
	public VehicleController() {}
	
	public Conductor createDriver() {
		Conductor driver = null;
		try {
			driver = PanelND.newPersona();
			repositoryD.addElement(driver);
		} catch(Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); }
		return driver;
	}
	
	public Llicencia createPermit(Conductor driver) {
		Llicencia permit = null;
		try {
			permit = PanelNP.newPermit(driver);
			repositoryP.addElement(permit);
			driver.setLlicencia(permit);
		} catch(Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); }
		return permit;
	}
	
	public void createDriverWithPermit() {
		Conductor driver = createDriver();
		if(driver != null) createPermit(driver);
	}
	
	public Conductor findDriver() {
		Conductor driver = null;
		try {
			PanelFE<Conductor> panel = new PanelFE<>(repositoryD, "CERCA DE CONDUCTOR", "cognom");
			driver = panel.findElement();
		} catch(Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); }
		return driver;
	}
	
	public void showDriver(Conductor driver) {
		if(driver == null) return;
		PanelSE<Conductor> panel = new PanelSE<>(driver, driver.toString());
		panel.showElement();
	}
	
	public Vehicle createVehicle() {
		Vehicle vehicle = null;
		try {
			vehicle = PanelNV.newVehicle(this);
			repositoryV.addElement(vehicle);
		} catch(Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); }
		return vehicle;
	}
	
	public void addWheelsToVehicle(Vehicle vehicle) { try {
		if(vehicle == null) throw new Exception("Can't add wheels: Vehicle missing.");

		List<Integer> wPA = vehicle.getWheelsPerAxle();
		vehicle.setWheelDistribution(WheelDistribution.createAxleConfig(wPA, PanelNW.newWheels(wPA)));	
	} catch(Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); } }
	
	public void createVehicleWithWheels() {
		Vehicle vehicle = createVehicle();
		if(vehicle != null)	addWheelsToVehicle(vehicle);
	}
	
	public Vehicle findVehicle() {
		Vehicle vehicle = null;
		try {
			PanelFE<Vehicle> panel = new PanelFE<>(repositoryV, "CERCA DE VEHICLE", "matrícula");
			vehicle = panel.findElement();
		} catch(Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); }
		return vehicle;
	}
	
	public void showVehicle(Vehicle vehicle) {
		if(vehicle == null) return;
		PanelSE<Vehicle> panel = new PanelSE<>(vehicle, vehicle.toString());
		panel.showElement();
	}
	
}
