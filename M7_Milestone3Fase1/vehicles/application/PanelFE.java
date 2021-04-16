package M7_Milestone3Fase1.vehicles.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import M7_Milestone3Fase1.vehicles.domain.Elemento;

import M7_Milestone3Fase1.vehicles.persistence.Repository;

public class PanelFE<E extends Elemento> { // Find element (person/driver/owner, vehicle, …)
	
	private Repository<E> repository;
	private String title;
	private String search;
	
	private JPanel panelFE;
	private Box box1, box1Id, box1Cg;
	private JTextField field1Id, field1Cg;
	private JButton button1Id, button1Cg, buttonOK;
	private DefaultListModel<E> listModel2P;
	private JList<E> list2P;
	buttonListener bL1Id, bL1Cg;
	private JScrollPane scrollPane2;
	private JOptionPane pane;
	
	public PanelFE(Repository<E> repository, String title, String search) throws Exception {
		if(repository.size() == 0) throw new Exception("Source database empty.");
		
		this.repository = repository;
		this.title = title;
		this.search = search;
	    
		panelFE = new JPanel();
	}

	public E findElement() {
		panelFE = new JPanel();
		panelFE.setLayout(new BoxLayout(panelFE, BoxLayout.Y_AXIS));
		
		box1 = Box.createVerticalBox();
		box1.add(Box.createHorizontalStrut(180));
		
		box1Id = Box.createHorizontalBox();
		box1Cg = Box.createHorizontalBox();
		
		field1Id = new HintTextField("<#id>");
	    field1Cg = new HintTextField("<" + search + ">");
	    
	    button1Id = new JButton("Cerca");
	    button1Cg = new JButton("Cerca");
	    
	    button1Id.setActionCommand("Id");
	    button1Cg.setActionCommand("St");
	    
	    box1Id.add(field1Id); box1Id.add(button1Id);
	    box1Cg.add(field1Cg); box1Cg.add(button1Cg);  
	    
		box1.add(box1Id);
		box1.add(box1Cg);
		panelFE.add(box1);
	    
	    listModel2P = new DefaultListModel<>();
	    listModel2P.ensureCapacity(repository.size());
		for(E e : repository.findElement(null))
			listModel2P.addElement(e);
		list2P = new JList<>(listModel2P);
	    
		bL1Id = new buttonListener(field1Id, listModel2P);
	    bL1Cg = new buttonListener(field1Cg, listModel2P);
	    
	    button1Id.addActionListener(bL1Id);
	    button1Cg.addActionListener(bL1Cg);
	       
	    scrollPane2 = new JScrollPane(list2P, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    panelFE.add(scrollPane2);
	    
	    pane = new JOptionPane(panelFE, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
	    
		JDialog dialog = pane.createDialog(title);
		
		buttonOK = pane.getRootPane().getDefaultButton();
		buttonOK.setEnabled(false);
		
		list2P.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting() && list2P.getSelectedValue() != null)
					buttonOK.setEnabled(true);
				else
					buttonOK.setEnabled(false);
			}
			
		});
		
		dialog.setVisible(true);
		
		Object selectedValue = pane.getValue();
		if(selectedValue == null || ((Integer)selectedValue).intValue() != JOptionPane.OK_OPTION)
			return null;
	    
		return list2P.getSelectedValue();
	}
	
	private class buttonListener implements ActionListener {
		
		private final JTextField jTF;
		private final DefaultListModel<E> dLM;
		
		buttonListener(JTextField jTF, DefaultListModel<E> dLM) {
			this.jTF = jTF;
			this.dLM = dLM;
		}
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			dLM.clear();
			switch(ev.getActionCommand()) {
			case("Id"):
				try {
					dLM.addElement(repository.findElement(Integer.parseInt(jTF.getText())));
					break;
				} catch(Exception ex) { if(!jTF.getText().isEmpty()) break; }
			// Falls through if JTF is empty
			case("St"):
				for(E e : repository.findElement(jTF.getText()))
					dLM.addElement(e);
			}
		}
		
	}
	
}
