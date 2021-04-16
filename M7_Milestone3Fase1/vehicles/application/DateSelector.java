package M7_Milestone3Fase1.vehicles.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DateSelector extends Box {
	
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Year> jCBAny;
	private JComboBox<Month> jCBMes;
	private JComboBox<Integer> jCBDia;
	
	public DateSelector(String str) {
		this(str, 0);
	}
	
	public DateSelector(String str, int yearsAhead) { // 'yearsAhead' # años venideros (0 = tomar fechas desde el año 1900)
		super(BoxLayout.X_AXIS);
		super.add(new JLabel(str));
	    super.add(Box.createHorizontalStrut(10));
		
	    LocalDate avui = LocalDate.now();
	    Year aquestAny = Year.from(avui);
	    Year[] anys;
	    if(yearsAhead == 0) {
	    	anys = new Year[aquestAny.getValue() - 1900 + 1];
	    	for(int i = 0; i < aquestAny.getValue() - 1900 + 1; i++)
	    		anys[i] = aquestAny.minusYears((long)i);
	    } else {
	    	anys = new Year[yearsAhead + 1];
	    	for(int i = 0; i < yearsAhead + 1; i++)
	    		anys[i] = aquestAny.plusYears((long)(yearsAhead - i));
	    }
	    JComboBox<Year> jCBAny = new JComboBox<>(anys);
	    
	    Month[] mesos = Month.values();
	    JComboBox<Month> jCBMes = new JComboBox<>(mesos);
	    
	    JComboBox<Integer> jCBDia = new JComboBoxDies<>();
	    
	    this.jCBAny = jCBAny;
	    this.jCBMes = jCBMes;
	    this.jCBDia = jCBDia;
	    
		jCBAny.addActionListener(jCBDia);
		jCBMes.addActionListener(jCBDia);
	    
		jCBMes.setSelectedItem(yearsAhead == 0 ? Month.JANUARY : Month.from(avui));
		jCBDia.setSelectedItem(yearsAhead == 0 ? Integer.valueOf(1) : Integer.valueOf(avui.getDayOfMonth()));
		
	    super.add(jCBAny);
	    super.add(jCBMes);
	    super.add(jCBDia);
	}
	
	public LocalDate getLocalDate() {
		return LocalDate.of(((Year)jCBAny.getSelectedItem()).getValue(), (Month)jCBMes.getSelectedItem(), jCBDia.getSelectedIndex() + 1);
	}
	
	private class JComboBoxDies<E> extends JComboBox<E> implements ActionListener {
		
		private static final long serialVersionUID = 1L;
		
		JComboBoxDies() {
			super();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) { // Tot això per solucionar els anys de traspàs!
			Integer[] dies = new Integer[((Month)jCBMes.getSelectedItem()).length(Year.isLeap((long)((Year)jCBAny.getSelectedItem()).getValue()))];
		    for(int i = 0; i < dies.length; i++)
		    	dies[i] = Integer.valueOf(i + 1);
		    jCBDia.setModel(new DefaultComboBoxModel<Integer>(dies));
		}
		
	}

}
