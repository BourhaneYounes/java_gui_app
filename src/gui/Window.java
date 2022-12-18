package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;




public class Window {
	
	private JFrame frame;
	private JPanel panel;
	private JScrollPane scpanel;
	private MySQLDb databaseDb = new MySQLDb();
	private ArrayList<JTextField> fields = new ArrayList<>();
	private ArrayList<String> fieldComponent = new ArrayList<>(List.of("CNE","Nom","Prénom","Email","Filière","Tél","Ville"));
	//private boolean activate;
	
	public Window() throws Exception {
		
		init();
	}
	
	private void init() throws Exception {
		
		 
		
		// composant principal fenêtre 
		frame = new JFrame();
		frame.setTitle("Gestion des étudiants");
		frame.setSize(1000,580);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// panel principal
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,1000,580);    
        //panel.setBackground(Color.gray); 
        
        // composant recherche
		
		createSearchPanel();
		createSearchBox();
        
        // composants enregistrement
        createRegistrationPanel();
        createButtonRegistration();
        
        
        frame.add(panel);
	}
	
	private void createSearchPanel() throws IOException {
		
		//activate = false;
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBounds(0,0,1000,90);
		//searchPanel.setBackground(Color.gray);
		
		JLabel searchLabel = new JLabel();
		searchLabel.setText("Recherche (par nom): ");
		searchLabel.setForeground(Color.black);
		searchLabel.setBounds(10,40,175,15);
		
		JTextField searchField = new JTextField();
		searchField.setText("Rechercher");
		searchField.setBounds(175,30,750,33);
		searchField.addFocusListener(new FieldFocusListener("Rechercher", searchField));
		
		//searchField.setBorder(BorderFactory.createCompoundBorder(
          //      new CustomBorder(), 
         //       new EmptyBorder(new Insets(15, 25, 15, 25))));
		
		//searchField.addActionListener(new FieldListener(searchField));
        //searchField.addMouseListener(new FieldMouseListener(activate,searchField));
		
		Image searchImg = ImageIO.read(getClass().getResource("assets/searchIcon.png"));
		JButton searchButton = new JButton(new ImageIcon(searchImg));
		searchButton.setBounds(930,30,65,33);
		searchButton.setFocusable(false);
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createResearchTable(searchField.getText(),scpanel);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		searchPanel.add(searchButton);
		searchPanel.add(searchField);
		searchPanel.add(searchLabel);
		this.panel.add(searchPanel);
		
	}	
	
	private void createSearchBox() throws Exception {
		
		JPanel searchBoxPanel = new JPanel();
		searchBoxPanel.setLayout(null);
		searchBoxPanel.setBounds(420,80,570,450);
		searchBoxPanel.setBackground(Color.white);
		
		
		JButton refreshButton = new JButton("Actualiser");
		refreshButton.setBounds(233,415,105,33);
		refreshButton.setFocusable(false);
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(15,15,545,395);
		scpanel = scrollPanel;
		//scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createResearchTable("",scrollPanel);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		searchBoxPanel.add(refreshButton);
		searchBoxPanel.add(scrollPanel);
		this.panel.add(searchBoxPanel);
		
	}
	
	private void createResearchTable(String name, JScrollPane panel) throws Exception {
		
		String[] columnNames = {"CNE","NOM", "PRENOM", "EMAIL", "FILIERE", "TEL", "VILLE"};
		
		DefaultTableModel tableModel = new DefaultTableModel(databaseDb.readDb(name), columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JTable table = new JTable(databaseDb.readDb(""), columnNames);
		table.setModel(tableModel);
		panel.setViewportView(table);
	}
	
	private void createRegistrationPanel() {
		JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(null);
        registrationPanel.setBounds(10,80,400,350);
        registrationPanel.setBackground(Color.white);
        this.panel.add(registrationPanel);
        
        JLabel registrationLabel = new JLabel();
        registrationLabel.setText("<html><div style='text-align:center;font-size:13px'>Enregistrement</div></html>");
        registrationLabel.setBounds(150,10,150,20);
        registrationLabel.setForeground(Color.black);
        registrationPanel.add(registrationLabel);
        
        // composants formulaire
        
        formField("CNE", 5, 52, 75, 15, registrationPanel);
        formField("Nom", 5, 92, 70, 15, registrationPanel);
        formField("Prénom", 5, 132, 85, 15, registrationPanel);
        formField("Email", 5, 172, 75, 15, registrationPanel);
        formField("Filière", 5, 212, 85, 15, registrationPanel);
        formField("Tél", 5, 252, 70, 15, registrationPanel);
        formField("Ville", 5, 292, 50, 15, registrationPanel);
	}
	
	private void createButtonRegistration() {
		JPanel buttonPanelRegistration = new JPanel();
		buttonPanelRegistration.setLayout(null);
		buttonPanelRegistration.setBounds(10,440,400,100);
		//buttonPanelRegistration.setBackground(Color.white);
		this.panel.add(buttonPanelRegistration);
		
		JButton valider = new JButton("Valider");
		valider.setFocusable(false);
		valider.setBounds(100,5,110,30);
		
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					databaseDb.insertDb(getField());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton reset = new JButton("Reset");
		reset.setFocusable(false);
		reset.setBounds(230,5,110,30);
		
		JButton update = new JButton("Update");
		update.setFocusable(false);
		update.setBounds(100,50,110,30);
		
		JButton delete = new JButton("Supprimer");
		delete.setFocusable(false);
		delete.setBounds(230,50,110,30);
		
		buttonPanelRegistration.add(valider);
		buttonPanelRegistration.add(reset);
		buttonPanelRegistration.add(update);
		buttonPanelRegistration.add(delete);
		
	}
	
	private void formField(String name, int x, int y, int w, int h, JPanel panel) {
		
		JLabel labelField = new JLabel(name + " : ");
        labelField.setForeground(Color.black);
        labelField.setFont(new Font("", Font.BOLD, 15));
        labelField.setBounds(x,y,w,h);
        
        JTextField textField = new JTextField();
        textField.setText(name);
        textField.setBounds(95,y-2,250,h+5);
        textField.addFocusListener(new FieldFocusListener(name, textField));
        //textField.setBackground(Color.white);
        //textField.addMouseListener(new FieldMouseListener(activate,textField));
        fields.add(textField);
        
        panel.add(labelField);
        panel.add(textField);
        
	}
	
	private String[] getField() {
		String[] data = new String[7];
		for (int i = 0; i < 7; i++) {
			if (!fieldComponent.contains(fields.get(i).getText()))
				data[i] = fields.get(i).getText();
			System.out.println(data[i]);
		}
		return data;
	}
	public void show() {
		this.frame.setVisible(true);
	}
	
}
