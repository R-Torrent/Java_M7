package M7_Milestone3Fase1.vehicles.application;

import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.Document;

import M7_Milestone3Fase1.vehicles.domain.AxleTypes;
import M7_Milestone3Fase1.vehicles.domain.Wheel;

public class PanelNW { // New wheels

	public static List<Wheel> newWheels(List<Integer> wheelsPerAxle) throws Exception {
		if(wheelsPerAxle == null || wheelsPerAxle.isEmpty()) throw new Exception("Wheels-per-axle list missing.");
		
		int nAxles = wheelsPerAxle.size();
		final JPanel panelNW = new JPanel();
		panelNW.setLayout(new BoxLayout(panelNW, BoxLayout.Y_AXIS));
		
		final Box box1 = Box.createHorizontalBox();
		
		final JCheckBox op1I = new JCheckBox("Identical wheels in each axle");
		final JCheckBox op1S = new JCheckBox("Symmetrical wheel distribution");

		final CheckBoxListener firstListener = new CheckBoxListener(op1I, op1S); 
		op1I.addActionListener(firstListener);
		
		box1.add(op1I);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(op1S);
		panelNW.add(box1);
		panelNW.add(Box.createVerticalStrut(10));
		
		final JPanel innerGrid = new JPanel(new GridLayout(0, nAxles));
		
		AxleTypes[] axleTypes = AxleTypes.values(); // Carga el array de ejes del enum 'AxleTypes': { 1, 2, 4 }
		List<Integer> axleNumbers = new ArrayList<>();
		for(int i = 0; i < axleTypes.length; i++)
			axleNumbers.add(axleTypes[i].number());
		
		List<WheelBox> wheelBoxList = new ArrayList<>();

		for(int i = 0; i < nAxles; i++) {
			Box boxAxle = Box.createVerticalBox();
			boxAxle.add(new JLabel("Axle #" + (i+1)));
			
			if(wheelsPerAxle.get(i) == null) throw new Exception("Axle missing wheels.");
			int wheels = wheelsPerAxle.get(i).intValue();
			List<WheelBox> inverted = new ArrayList<>(); // Las ruedas se deben almacenar de izda. a dcha., pero se generan de dcha. a izda. 
			for(int j = wheels - 1; j >= 0; j--) {
				WheelBox wheelBox = new WheelBox(axleTypes[axleNumbers.indexOf(wheels)], j);
				inverted.add(wheelBox);
		
				boxAxle.add(wheelBox);
			}
			for(int j = 1; j < wheels; j++) { // Establecer el control de eventos
				new WheelBoxListener(inverted.get(0), inverted.get(j), op1I);
				if(j >= wheels/2.0) new WheelBoxListener(inverted.get(wheels-1-j), inverted.get(j), op1S);
			}
			while(!inverted.isEmpty()) {
				int last = inverted.size() - 1;
				wheelBoxList.add(inverted.get(last));
				inverted.remove(last);
			}
				
			innerGrid.add(boxAxle);
		}
		
		panelNW.add(innerGrid);
		
		int userOp = JOptionPane.showConfirmDialog(null, panelNW, 
	    		"INTRODUEXI DADES DE LES RODES", JOptionPane.OK_CANCEL_OPTION);
	    
	    if(userOp != JOptionPane.OK_OPTION) return null;
	    
	    List<Wheel> wheelList = new ArrayList<>();
	    for(WheelBox wB : wheelBoxList) {
	    	double d = wB.getWheelDiamt();
	    	if(d <= 0.4 || d >= 4) throw new Exception("Diàmetre de roda fora de mida.");
	    	wheelList.add(new Wheel(wB.getWheelBrand(), d));
	    }
	    return wheelList;
	}

    private static class WheelBox extends Box {
    	
    	private static final long serialVersionUID = 1L;
    	
		private final HintTextField wheelBrand = new HintTextField("<marca>");
	    private final HintTextField wheelDiamt = new HintTextField("0.4 < Ø < 4.0");
    	
    	public WheelBox(AxleTypes axleType, int wheelInAxle) {
    		super(BoxLayout.Y_AXIS);
    		
    		wheelBrand.setMaximumSize(new Dimension(Integer.MAX_VALUE, wheelBrand.getMinimumSize().height));
    		wheelDiamt.setMaximumSize(new Dimension(Integer.MAX_VALUE, wheelDiamt.getMinimumSize().height));
    		this.add(WheelBox.createVerticalGlue());
    		Box innerWheelBox = Box.createVerticalBox();
    		innerWheelBox.add(new JLabel(axleType.labels().get(wheelInAxle)));
    		innerWheelBox.add(wheelBrand);
    		innerWheelBox.add(wheelDiamt);
    		innerWheelBox.setBorder(BorderFactory.createEtchedBorder());
    		this.add(innerWheelBox);
    		this.add(WheelBox.createVerticalGlue());
    	}
    	
    	public String getWheelBrand() {
    		return wheelBrand.getText();
    	}
    	
    	public double getWheelDiamt() {
    		double d = 0.0;
    		try { d = Double.parseDouble(wheelDiamt.getText());
    		} catch(NumberFormatException e) { System.err.println("Non-numerical diameter converted to 0.0"); }
    		return d;
    	}
    	
    }
 
	private static class CheckBoxListener implements ActionListener {
		
		private final JCheckBox speaker;
		private final JCheckBox listener;
		
		public CheckBoxListener(JCheckBox speaker, JCheckBox listener) {
			this.speaker = speaker;
			this.listener = listener;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(speaker.isSelected()) {
				listener.setSelected(true);
				listener.setEnabled(false);
			}
			else {
				listener.setSelected(false);
				listener.setEnabled(true);
			}			
		}
		
	}
	
	private static class WheelBoxListener implements ActionListener {
		
		private final WheelBox speaker;
		private final WheelBox listener;
		private final Document listenerWheelBrandDoc;
		private final Document listenerWheelDiamtDoc;
		
		public WheelBoxListener(WheelBox speaker, WheelBox listener, JCheckBox checkBox) {
			this.speaker = speaker;
			this.listener = listener;
			checkBox.addActionListener(this);
			listenerWheelBrandDoc = listener.wheelBrand.getDocument();
			listenerWheelDiamtDoc = listener.wheelDiamt.getDocument();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			listener.wheelBrand.setEnabled(!((JCheckBox)e.getSource()).isSelected());
			listener.wheelDiamt.setEnabled(!((JCheckBox)e.getSource()).isSelected());
			if(!listener.wheelBrand.isEnabled()) {
				listener.wheelBrand.setDocument(speaker.wheelBrand.getDocument());
				listener.wheelDiamt.setDocument(speaker.wheelDiamt.getDocument());
				listener.wheelBrand.enableHint(false);
				listener.wheelDiamt.enableHint(false);
			}
			else {
				listener.wheelBrand.setDocument(listenerWheelBrandDoc);
				listener.wheelDiamt.setDocument(listenerWheelDiamtDoc);
				listener.wheelBrand.enableHint(true);
				listener.wheelDiamt.enableHint(true);
			}	
		}
		
	}

}
