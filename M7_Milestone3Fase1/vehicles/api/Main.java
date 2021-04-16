/** Back-end Java
*** M7 - Milestone 3 - Fase 1
*** Roger Torrent */

package M7_Milestone3Fase1.vehicles.api;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import M7_Milestone3Fase1.vehicles.application.VehicleController;

public class Main {
	
	public static VehicleController m7 = new VehicleController();
		
	public static void main(String[] args) {			
		userInterface(); // Menu principal
	}
	
	public static void userInterface() {
		final JPanel panelMenu = new JPanel(new GridLayout(0, 2));
		
		final Box boxDr = Box.createVerticalBox(); // Menu conductors
	
		final Box boxDr1 = Box.createHorizontalBox();
		final JButton buttonDr1 = new JButton("Nou conductor");
		final JCheckBox checkBoxDr1 = new JCheckBox("amb llicència");
		boxDr1.add(buttonDr1);
		boxDr1.add(checkBoxDr1);
		boxDr1.add(Box.createHorizontalGlue());
		
		boxDr.add(boxDr1);
		boxDr.add(Box.createVerticalStrut(10));
		
		final Box boxDr2 = Box.createHorizontalBox();
		final JButton buttonDr2 = new JButton("Nova llicència");
		boxDr2.add(buttonDr2);
		boxDr2.add(Box.createHorizontalGlue());
		
		boxDr.add(boxDr2);
		boxDr.add(Box.createVerticalStrut(10));
		
		final Box boxDr3 = Box.createHorizontalBox();
		final JButton buttonDr3 = new JButton("Examinar conductor");
		boxDr3.add(buttonDr3);
		boxDr3.add(Box.createHorizontalGlue());
		boxDr.add(boxDr3);
		
		final Box boxVe = Box.createVerticalBox(); // Menu vehicles
		
		final Box boxVe1 = Box.createHorizontalBox();
		final JButton buttonVe1 = new JButton("Nou vehicle");
		final JCheckBox checkBoxVe1 = new JCheckBox("amb rodes");
		boxVe1.add(buttonVe1);
		boxVe1.add(checkBoxVe1);
		boxVe1.add(Box.createHorizontalGlue());
		
		boxVe.add(boxVe1);
		boxVe.add(Box.createVerticalStrut(10));
		
		final Box boxVe2 = Box.createHorizontalBox();
		final JButton buttonVe2 = new JButton("Posar rodes");
		boxVe2.add(buttonVe2);	
		boxVe2.add(Box.createHorizontalGlue());
		boxVe2.add(Box.createVerticalStrut(10));
		
		boxVe.add(boxVe2);
		boxVe.add(Box.createVerticalStrut(10));
		
		final Box boxVe3 = Box.createHorizontalBox();
		final JButton buttonVe3 = new JButton("Examinar vehicle");
		boxVe3.add(buttonVe3);
		boxVe3.add(Box.createHorizontalGlue());
		boxVe.add(boxVe3);
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titleDr, titleVe;
		titleDr = BorderFactory.createTitledBorder(loweredetched, "CONDUCTORS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);		
		titleVe = BorderFactory.createTitledBorder(loweredetched, "VEHICLES", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
		
		boxDr.setBorder(titleDr);
		boxVe.setBorder(titleVe);
		panelMenu.add(boxDr);
		panelMenu.add(boxVe);
		
		JFrame frame = new JFrame("MÒDUL 7 - MILESTONE 3 FASE 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		buttonDr1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoxDr1.isSelected()) m7.createDriverWithPermit();
				else m7.createDriver();		
			}
			
		});
		
		buttonDr2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m7.createPermit(m7.findDriver());	
			}
			
		});
		
		buttonDr3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m7.showDriver(m7.findDriver());
			}
			
		});
		
		buttonVe1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoxVe1.isSelected()) m7.createVehicleWithWheels();
				else m7.createVehicle();		
			}
			
		});
		
		buttonVe2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m7.addWheelsToVehicle(m7.findVehicle());	
			}
			
		});
		
		buttonVe3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m7.showVehicle(m7.findVehicle());
			}
			
		});
	}

}
