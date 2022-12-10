package gui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import javax.swing.JTextField;

public class FieldFocusListener implements FocusListener{
	
	private String name;
	private JTextField field;
	
	public FieldFocusListener(String name, JTextField field) {
		this.name = name;
		this.field = field;
	}
	
	public void focusGained(FocusEvent event) {
		if(field.getText().compareTo(name) == 0)
			field.setText("");
	}
	
	public void focusLost(FocusEvent event) {
		if(field.getText().isBlank())
			field.setText(name);
	}
}
