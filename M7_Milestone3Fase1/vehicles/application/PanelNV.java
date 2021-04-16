package M7_Milestone3Fase1.vehicles.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import M7_Milestone3Fase1.vehicles.domain.AxleTypes;
import M7_Milestone3Fase1.vehicles.domain.Conductor;
import M7_Milestone3Fase1.vehicles.domain.Persona;
import M7_Milestone3Fase1.vehicles.domain.Titular;
import M7_Milestone3Fase1.vehicles.domain.Vehicle;

public class PanelNV { // New vehicle
	
	// Modificar este valor si se necesitan vehículos con más ejes:
	private static final int maxMAXAXLES = 6;
	
	public static Vehicle newVehicle(VehicleController controlador) throws Exception {
		final JPanel panelNV = new JPanel();
	    panelNV.setLayout(new BoxLayout(panelNV, BoxLayout.Y_AXIS));
		
	    final Box box1 = Box.createHorizontalBox();

	    final List<JRadioButton> vehicleCategoryList = new ArrayList<>();
	    final ButtonGroup opVehicle = new ButtonGroup();
	    for(String vC : Vehicle.vehicleNameList) {
	    	JRadioButton op1 = new JRadioButton(vC);
	    	vehicleCategoryList.add(op1);
	    	opVehicle.add(op1);
	    	
	    	op1.setActionCommand(vC);
	    	
	    	box1.add(op1);
	    }
	    
	    panelNV.add(box1);
	    panelNV.add(Box.createVerticalStrut(10));
		
	    final Box box2 = Box.createHorizontalBox();
	    final Box box2a = Box.createVerticalBox();
	    final Box box2b = Box.createVerticalBox();
	    
	    box2a.add(new JLabel("nombre de eixos:"));
	    
	    final List<JRadioButton> numberedRadioButtonList = new ArrayList<>();
	    final ButtonGroup nAxlesBG = new ButtonGroup();
	    for(int i = 1; i <= maxMAXAXLES; i++) {
	    	JRadioButton op2a = new JRadioButton(Integer.toString(i));
	    	numberedRadioButtonList.add(op2a);
	    	nAxlesBG.add(op2a);
	    	
	    	op2a.setEnabled(false);

	    	box2a.add(op2a);
	    }
	    
	    box2b.add(new JLabel("nombre de rodes:"));
	        
	    AxleTypes[] aT = AxleTypes.values(); // Carga automáticamente el array { 1, 2, 4 }, ya que pudiera haberse ampliado
	    Integer[] wheelsPerAxleArray = new Integer[aT.length];
	    for(int i = 0; i < aT.length; i++)
	    	wheelsPerAxleArray[i] = aT[i].number(); 

	    final List<Box> box2bList = new ArrayList<>();
	    final List<Box> numberedBoxList = new ArrayList<>();
	    final List<JComboBox<Integer>> jComboBoxList = new ArrayList<>();
	    for(int i = 1; i <= maxMAXAXLES; i++) {
	    	Box box2ba = Box.createHorizontalBox();
	    	Box box2bb = new NumberedBox(i);
	    	JComboBox<Integer> op2bb = new JComboBox<>(wheelsPerAxleArray);
	    	box2bList.add(box2ba);
	    	numberedBoxList.add(box2bb);
	    	jComboBoxList.add(op2bb);
	    	
	    	op2bb.setEnabled(false);
	    	op2bb.setSelectedItem(null);
	    	
	    	box2bb.add(new JLabel(String.format("eix%d: ", i)));
	    	box2bb.add(op2bb);
	    	
	    	box2ba.add(new JLabel(" "));
	    	box2ba.add(box2bb);
	    	box2b.add(box2ba);
	    }
	    
	    for(JRadioButton jRB : numberedRadioButtonList)
	    	numberedBoxList.forEach(member -> jRB.addActionListener((NumberedBox)member)); 
	    
	    final CategoryListener categoryListener = new CategoryListener(numberedRadioButtonList, jComboBoxList);
	    vehicleCategoryList.forEach(member -> member.addActionListener(categoryListener));
	    
	    box2.add(box2a);
	    box2.add(Box.createHorizontalStrut(20));
	    box2.add(box2b);
	    
	    panelNV.add(box2);
	    panelNV.add(Box.createVerticalStrut(20));
	    
	    final Box box3 = Box.createHorizontalBox();
	    final Box box5 = Box.createHorizontalBox();
	    final Box box7 = Box.createHorizontalBox();
	    	    
	    final JTextField field4Pl = new HintTextField("1234AB o 1234ABC");
	    final JTextField field6Br = new HintTextField("<marca>");
	    final JTextField field8Cl = new HintTextField("<color>");

	    box3.add(new JLabel("matrícula:"));
	    box3.add(Box.createHorizontalStrut(290));
	    panelNV.add(box3);
	    panelNV.add(field4Pl);
	    
	    box5.add(new JLabel("marca:"));
	    box5.add(Box.createHorizontalStrut(310));
	    panelNV.add(box5);	    
	    panelNV.add(field6Br);
	    
	    box7.add(new JLabel("color:"));
	    box7.add(Box.createHorizontalStrut(320));
	    panelNV.add(box7);
	    panelNV.add(field8Cl);
	    panelNV.add(Box.createVerticalStrut(20));
	    
	    final Box box9 = Box.createHorizontalBox();
	    final Box box10 = Box.createHorizontalBox();
	    final Box box10b = Box.createVerticalBox();
	    final Box box11 = Box.createHorizontalBox();
	    final Box box12 = Box.createHorizontalBox();
	    final Box box12b = Box.createVerticalBox();
	    
	    class JListE<E> extends JList<E> implements ActionListener {
	    	
			private static final long serialVersionUID = 1L;
	    	
	    	JListE(DefaultListModel<E> dataModel) {
	    		super(dataModel);
	    	}

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {			
				((DefaultListModel<Persona>)getModel()).remove(super.getSelectedIndex());
			}
	    	
	    }
	    
	    DefaultListModel<Persona> listModel10Ow = new DefaultListModel<>();
	    DefaultListModel<Persona> listModel12Dr = new DefaultListModel<>();
	    final JListE<Persona> list10Ow = new JListE<>(listModel10Ow);
	    final JListE<Persona> list12Dr = new JListE<>(listModel12Dr);
	    
	    list10Ow.setFocusable(false);
	    
	    final JScrollPane scrollPane10Ow = new JScrollPane(list10Ow,
	    		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    final JScrollPane scrollPane12Dr = new JScrollPane(list12Dr,
	    		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    class JButtonE extends JButton implements ListSelectionListener {
	    	
			private static final long serialVersionUID = 1L;
			
			final JListE<Persona> jL;

			JButtonE(String s, JListE<Persona> dLM) {
	    		super(s);
	    		super.setEnabled(false);
	    		this.jL = dLM;
	    		
	    		dLM.addListSelectionListener(this);
	    		this.addActionListener(dLM);
	    	}

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(jL.isSelectionEmpty())
					super.setEnabled(false);
				else
					super.setEnabled(true);				
			}
	    	
	    }
	    
	    final JButton button10OwA = new JButton("Assignar");
	    final JButton button12DrA = new JButton("Assignar");
	    final JButton button10OwE = new JButtonE("Esborrar", list10Ow);
	    final JButton button12DrE = new JButtonE("Esborrar", list12Dr);
	    
	    button10OwA.setEnabled(false);
	    button12DrA.setEnabled(false);
	    	    
	    vehicleCategoryList.forEach(member -> member.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		button10OwA.setEnabled(true);
	    	    button12DrA.setEnabled(true);
	    	    listModel10Ow.clear();
	    	    listModel12Dr.clear();
	    	}
	    	
	    }));
	    
	    class OwnersNDrivers {
			
	    	final DefaultListModel<Persona> dLM;
			
			OwnersNDrivers(DefaultListModel<Persona> dLM) {
				this.dLM = dLM;
			}
			
	    	public Conductor findCandidate() {
	    		String tipusV = opVehicle.getSelection().getActionCommand();
	    		int idxPermit = Vehicle.vehicleNameList.indexOf(tipusV);
	     		final Conductor candidate = controlador.findDriver();
	    		
	    		if(candidate == null || candidate.getLlicencia() == null || !candidate.getLlicencia().getPermits()[idxPermit]) {
	    			JOptionPane.showMessageDialog(null, "Llicencia no vàlida per conduir vehicle '" + tipusV + "'",
	    					"ERROR", JOptionPane.ERROR_MESSAGE);
	    			return null;
	    		}
	    		
	    		return candidate;
	    	}
			
		}
	    
	    class Owners extends OwnersNDrivers implements ActionListener {
	    	
	    	Owners(DefaultListModel<Persona> dLM) {
				super(dLM);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				final Conductor candidate = findCandidate();

				if(candidate == null)
					;
				else if(!(candidate instanceof Titular))
					JOptionPane.showMessageDialog(null, "El conductor no és titular", "ERROR", JOptionPane.ERROR_MESSAGE);
				else {
					dLM.clear();
					dLM.addElement(candidate);
					list10Ow.setSelectedIndex(0);
					if(listModel12Dr.isEmpty())
						listModel12Dr.addElement(candidate);
				}			
			}
	    			
	    }
	    
	    class Drvers extends OwnersNDrivers implements ActionListener {
	    	
	    	Drvers(DefaultListModel<Persona> dLM) {
				super(dLM);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				final Conductor candidate = findCandidate();
				
				if(candidate != null && !dLM.contains(candidate))
					dLM.addElement(candidate);
			}
	    			
	    }
	    
	    Owners driver10Ow = new Owners(listModel10Ow);
	    Drvers driver12Dr = new Drvers(listModel12Dr);
	    
	    button10OwA.addActionListener(driver10Ow);
	    button12DrA.addActionListener(driver12Dr);
	    
	    box9.add(new JLabel("titular:"));
	    box9.add(Box.createHorizontalStrut(315));
	    
	    box10.add(scrollPane10Ow);
	    box10.add(Box.createHorizontalStrut(10));  
	    box10b.add(button10OwA);
	    box10b.add(Box.createVerticalStrut(10));
	    box10b.add(button10OwE);
	    box10.add(box10b);
	    
	    box11.add(new JLabel("conductor(s):"));
	    box11.add(Box.createHorizontalStrut(270));
	    
	    box12.add(scrollPane12Dr);
	    box12.add(Box.createHorizontalStrut(10));   
	    box12b.add(button12DrA);
	    box12b.add(Box.createVerticalStrut(10));
	    box12b.add(button12DrE);
	    box12.add(box12b);
	    
	    panelNV.add(box9);
	    panelNV.add(box10);   
	    panelNV.add(box11);
	    panelNV.add(box12);
	    
	    int userOp = JOptionPane.showConfirmDialog(null, panelNV, 
	    		"INTRODUEXI DADES DEL VEHICLE", JOptionPane.OK_CANCEL_OPTION);
	    
	    if(userOp != JOptionPane.OK_OPTION) return null;
	    
	    if(!(Pattern.matches("\\d{4}[a-zA-Z]{2,3}", field4Pl.getText())))
	    	throw new Exception("Unknown license plate format.");
	    
	    List<Persona> driverList = new ArrayList<>();
	    for(Enumeration<Persona> e = listModel12Dr.elements(); e.hasMoreElements(); )
	    	driverList.add(e.nextElement());	    
	    
	    int nAxles = 0;
	    Iterator<JRadioButton> it1 = numberedRadioButtonList.iterator();
	    while(it1.hasNext()) {
	    	JRadioButton nrb = it1.next();
	    	if(nrb.isSelected()) {
	    		nAxles = Integer.parseInt(nrb.getText());
	    		break;
	    	}
	    }

	    Iterator<JComboBox<Integer>> it2 = jComboBoxList.iterator();
	    List<Integer> wheelsPerAxle = new ArrayList<>();
	    while(nAxles-- > 0) {
	    	JComboBox<Integer> jcb = it2.next();
	    	wheelsPerAxle.add((Integer)jcb.getSelectedItem());
	    }
	    
	    if(opVehicle.getSelection() != null) { // Nueva instancia del vehículo por métodos 'Java Reflection'
	    	String vehicleName = Vehicle.class.getPackageName() + "." + opVehicle.getSelection().getActionCommand();
	    	Class<?> vehicleClass = Class.forName(vehicleName);
	    	Class<?>[] parameters = { String.class, String.class, String.class, List.class, Persona.class, List.class };
	    	Constructor<?> constructor = vehicleClass.getConstructor(parameters);
	    	Object arguments[] = {
	    		field4Pl.getText().toUpperCase(),
	    		field6Br.getText(),
	    		field8Cl.getText(),
	    		wheelsPerAxle,
	    		list10Ow.getSelectedValue(),
	    		driverList
	    	};
	    	try {
	    		return (Vehicle)constructor.newInstance(arguments); // Llamada a la clase 'Vehicle' apropiada
	    	} catch(InvocationTargetException e) { throw new Exception(e.getCause().getMessage()); }
	    }
	    else throw new Exception("Vehicle type not selected.");
	}
	
	private static class NumberedBox extends Box implements ActionListener {
		
		private static final long serialVersionUID = 1L;
		
		private final int boxNumber;
		
		public NumberedBox(int boxNumber) {
			super(BoxLayout.X_AXIS);
			this.boxNumber = boxNumber;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int radioButtonNumber = Integer.parseInt(e.getActionCommand());
			if(boxNumber <= radioButtonNumber)
				super.setVisible(true);
			else
				super.setVisible(false);
		}

	}
	
	private static class CategoryListener implements ActionListener {
		
		private final List<JRadioButton> jRBList;
		private final List<JComboBox<Integer>> jCBList;
		
		public CategoryListener(List<JRadioButton> jRBList, List<JComboBox<Integer>> jCBList ) {
			this.jRBList = jRBList;		
			this.jCBList = jCBList;
		}
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			jRBList.forEach(member -> member.setEnabled(false));
			jCBList.forEach(member -> member.setEnabled(true));
			try {
				Class<?> vehicleClass = Class.forName(Vehicle.class.getPackageName() + "." + ev.getActionCommand());
				int minAxles = vehicleClass.getField("MINAXLES").getInt(vehicleClass);
				int maxAxles = vehicleClass.getField("MAXAXLES").getInt(vehicleClass);
				@SuppressWarnings("unchecked")
				List<Integer> defaultAxles = (List<Integer>)vehicleClass.getField("DEFAULTAXLES").get(vehicleClass);
				int numDefaultAxles= defaultAxles.size();
				for(JRadioButton jRB : jRBList) {
					int numJRB = Integer.parseInt(jRB.getText());
					if(numJRB >= minAxles && (maxAxles == 0 || numJRB <= maxAxles))
						jRB.setEnabled(true);
					if(numJRB == numDefaultAxles)
						jRB.doClick();
				}
				Iterator<Integer> it = defaultAxles.iterator();
				for(JComboBox<Integer> jCB : jCBList)
					if(it.hasNext())
						jCB.setSelectedItem(it.next());
					else
						jCB.setSelectedItem(null);
			} catch(Exception ex) { ex.printStackTrace(); }
		}
		
	}
	
}
