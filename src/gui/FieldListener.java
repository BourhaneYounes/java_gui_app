package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class FieldListener implements ActionListener{
	
	private JTextField textField;
	
	public FieldListener(JTextField field) {
		this.textField = field;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println(textField.getText());
    }

}
