package M7_Milestone3Fase1.vehicles.application;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HintTextField extends JTextField implements FocusListener {

	static final long serialVersionUID = 1L;
	
	private final String hint;
	private boolean showingHint;
	private boolean enableHint;

	public HintTextField(final String hint) {
		super(hint);
	    this.hint = hint;
	    showingHint = true;
	    enableHint = true;
	    super.addFocusListener(this);
	}
	
	public void enableHint(boolean b) {
		enableHint = b;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(this.getText().isEmpty()) {
			super.setText("");
			showingHint = false;
	    }
	}
	  
	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().isEmpty()) {
			super.setText(hint);
			showingHint = true;
	    }
	}

	@Override
	public String getText() {
		return (showingHint && enableHint) ? "" : super.getText();
	}
	  
}
