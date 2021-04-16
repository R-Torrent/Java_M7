package M7_Milestone3Fase1.vehicles.application;

import javax.swing.JOptionPane;

import M7_Milestone3Fase1.vehicles.domain.Elemento;

public class PanelSE<E extends Elemento>  { // Show element (person/driver/owner, vehicle, …)
	
	private E element;
	private String title;
		
	PanelSE(E element, String title) {
		this.element = element;
		this.title = title;
	}
	
	public void showElement() {
		JOptionPane.showMessageDialog(null, element.showElement(), title, JOptionPane.INFORMATION_MESSAGE);
	}

}
