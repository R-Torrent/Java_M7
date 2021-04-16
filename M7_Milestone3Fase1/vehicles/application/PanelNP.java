package M7_Milestone3Fase1.vehicles.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import M7_Milestone3Fase1.vehicles.domain.Conductor;
import M7_Milestone3Fase1.vehicles.domain.Llicencia;
import M7_Milestone3Fase1.vehicles.domain.Titular;
import M7_Milestone3Fase1.vehicles.domain.Vehicle;

public class PanelNP { // New driver's permit

	public static Llicencia newPermit(Conductor driver) throws Exception {
		if(driver == null) throw new Exception("Can't create a new license: Driver missing.");
		
		final JPanel panelNP = new JPanel();
		panelNP.setLayout(new BoxLayout(panelNP, BoxLayout.Y_AXIS));

		final Box box1 = Box.createHorizontalBox();

		box1.add(new JLabel("  " + driver.toString() + "  "));
		box1.setBorder(BorderFactory.createEtchedBorder());
		
		panelNP.add(box1);
		panelNP.add(Box.createVerticalStrut(10));
		
	    DateSelector op2 = new DateSelector("Data de caducitat:", 10); // 10 años de validez
	    panelNP.add(op2);
	    
		final Box box3 = Box.createHorizontalBox();
		final Box box4 = Box.createHorizontalBox();
		    
		box3.add(new JLabel("Llicència/es:"));
		box3.add(Box.createHorizontalStrut(175));
		
		final List<JRadioButton> vehicleCategoryList = new ArrayList<>();
	    for(String vC : Vehicle.vehicleNameList) {
	    	JRadioButton op4 = new JRadioButton(vC);
	    	vehicleCategoryList.add(op4);
	 
	    	box4.add(op4);
	    }	
		
	    panelNP.add(box3);
	    panelNP.add(box4);
	    
	    
	    JOptionPane pane = new JOptionPane(panelNP,
	    		JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
	    
	    JDialog dialog = pane.createDialog("INTRODUEXI DADES DEL PERMÍS DE CONDUIR");
	    
	    final JButton buttonOK = pane.getRootPane().getDefaultButton();
		buttonOK.setEnabled(false);
	       
		vehicleCategoryList.forEach(member -> member.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		boolean selected = false;
	    		for(JRadioButton jRB : vehicleCategoryList)
	    			if(jRB.isSelected())
	    				selected = true;
	    		buttonOK.setEnabled(selected);
	    	}
	    	
	    }));
	    
		dialog.setVisible(true);
		
		Object selectedValue = pane.getValue();
		if(selectedValue == null || ((Integer)selectedValue).intValue() != JOptionPane.OK_OPTION)
			return null;
	    
		int numPermits = vehicleCategoryList.size();
	    final Boolean[] permisos = new Boolean[numPermits];
	    for(int i = 0; i < numPermits ; i++) {
	    	JRadioButton jRB = vehicleCategoryList.get(i);
	    	permisos[i] = jRB.isSelected();
	    	if(!jRB.isSelected()) { // Verifica que el nuevo permiso sea compatible con los vehículos conducidos/poseídos
	    		for(Vehicle dr : driver.getDrivenVehicles())
	    			if(jRB.getText().equals(dr.getClass().getSimpleName()))
	    				throw new Exception("New license is incompatible with declared driven vehicle(s).");
	    		if(driver instanceof Titular) for(Vehicle ow : ((Titular)driver).getOwnedVehicles())
	    			if(jRB.getText().equals(ow.getClass().getSimpleName()))
	    				throw new Exception("New license is incompatible with declared owned vehicle(s).");
	    	}
	    }
	    
		return new Llicencia(driver, op2.getLocalDate(), permisos);
	}
	
}
