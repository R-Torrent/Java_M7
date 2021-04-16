package M7_Milestone3Fase1.vehicles.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import M7_Milestone3Fase1.vehicles.domain.Conductor;
import M7_Milestone3Fase1.vehicles.domain.Titular;

public class PanelND { // New driver/owner
	
	public static Conductor newPersona() throws Exception {		
		final JPanel panelND = new JPanel();
		panelND.setLayout(new BoxLayout(panelND, BoxLayout.Y_AXIS));
		
		final Box box1 = Box.createHorizontalBox();
		final Box box2 = Box.createHorizontalBox();
		
		final JTextField field1Nm = new HintTextField("<nom>");
	    final JTextField field2Cg = new HintTextField("<cognom>");
		
	    box1.add(new JLabel("Nom:"));
	    box1.add(Box.createHorizontalStrut(280));
	    panelND.add(box1);
	    panelND.add(field1Nm);
	    
	    box2.add(new JLabel("Cognom:"));
	    box2.add(Box.createHorizontalStrut(260));
	    panelND.add(box2);
	    panelND.add(field2Cg);
	    
	    panelND.add(Box.createVerticalStrut(10));
	    
	    DateSelector op3 = new DateSelector("Data de naixement:");  
	    panelND.add(op3);
	    
	    panelND.add(Box.createVerticalStrut(10));
	    
	    final Box box4 = Box.createHorizontalBox();
	    
	    final JCheckBox box4aTi = new JCheckBox("Titular");
	    final Box box4b = Box.createVerticalBox();
	    
	    final JCheckBox field4b1In = new JCheckBox("Assegurança");
	    final JCheckBox field4b2Pl = new JCheckBox("Garatge propi");
	    
	    field4b1In.setEnabled(false);
	    field4b2Pl.setEnabled(false);
	    
	    box4b.add(field4b1In);
	    box4b.add(field4b2Pl);
	    box4.add(box4aTi);
	    box4.add(box4b);
	    box4.add(Box.createHorizontalStrut(140));
	    panelND.add(box4);
	    
	    box4aTi.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		if(box4aTi.isSelected()) {
	    			field4b1In.setEnabled(true);
	    			field4b2Pl.setEnabled(true);
	    		}
	    		else {
	    			field4b1In.setEnabled(false);
	    			field4b2Pl.setEnabled(false);
	    			field4b1In.setSelected(false);
	    			field4b2Pl.setSelected(false);
	    		}
	    	}
	    	
	    });

	    int userOp = JOptionPane.showConfirmDialog(null, panelND, 
	    		"INTRODUEXI DADES DEL CONDUCTOR", JOptionPane.OK_CANCEL_OPTION);
	    
	    if(userOp != JOptionPane.OK_OPTION) return null;
	    
	    if(box4aTi.isSelected())
	    	return new Titular(field1Nm.getText(), field2Cg.getText(), op3.getLocalDate(),
	    			field4b1In.isSelected(), field4b2Pl.isSelected());
	    else
	    	return new Conductor(field1Nm.getText(), field2Cg.getText(), op3.getLocalDate());
	}

}	
