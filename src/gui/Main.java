package gui;

import java.awt.EventQueue;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.show();							
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
