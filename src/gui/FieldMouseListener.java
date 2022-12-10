package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

import javax.swing.*;

public class FieldMouseListener implements MouseListener {
		
		private boolean activate;
		private JTextField textField;
	
		public FieldMouseListener(boolean activate, JTextField field) {
			this.activate = activate;
			this.textField = field;
		}
		
        @Override
        public void mouseClicked(MouseEvent e) {
            
            if(activate == false){
                textField.setText("");
            }
            activate = true;
            textField.setForeground(Color.black);
        }

        @Override
        public void mousePressed(MouseEvent e) {
          
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           
        }

        @Override
        public void mouseEntered(MouseEvent e) {
           
        }

        @Override
        public void mouseExited(MouseEvent e) {
          
        }
       
}
