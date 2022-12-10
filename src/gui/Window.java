package gui;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Window {
	
	private JFrame frame;
	private JPanel panel;
	//private boolean activate;
	
	public Window() throws IOException {
		
		init();
	}
	
	private void init() throws IOException {
		
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
		
		searchPanel.add(searchButton);
		searchPanel.add(searchField);
		searchPanel.add(searchLabel);
		this.panel.add(searchPanel);
		
	}
	
	private void createSearchBox() {
		
		JPanel searchBoxPanel = new JPanel();
		searchBoxPanel.setLayout(null);
		searchBoxPanel.setBounds(420,80,570,450);
		searchBoxPanel.setBackground(Color.white);
		
		JTable searchTable = new JTable();
		
		JButton refreshButton = new JButton("Actualiser");
		refreshButton.setBounds(233,380,105,33);
		refreshButton.setFocusable(false);
		
		searchBoxPanel.add(refreshButton);
		
		this.panel.add(searchBoxPanel);
		
	}
	
	private void createRegistrationPanel() {
		JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(null);
        registrationPanel.setBounds(10,80,400,370);
        registrationPanel.setBackground(Color.white);
        this.panel.add(registrationPanel);
        
        JLabel registrationLabel = new JLabel();
        registrationLabel.setText("<html><div style='text-align:center;font-size:13px'>Enregistrement</div></html>");
        registrationLabel.setBounds(150,10,150,20);
        registrationLabel.setForeground(Color.black);
        registrationPanel.add(registrationLabel);
        
        // composants formulaire
        
        formField("Nom", 5, 52, 75, 15, registrationPanel);
        formField("Prénom", 5, 92, 85, 15, registrationPanel);
        formField("Age", 5, 132, 50, 15, registrationPanel);
        formField("CNE", 5, 172, 50, 15, registrationPanel);
        formField("Classe", 5, 212, 70, 15, registrationPanel);
        formField("Filière", 5, 252, 75, 15, registrationPanel);
        formField("Tél", 5, 292, 50, 15, registrationPanel);
        formField("Ville", 5, 332, 70, 15, registrationPanel);
	}
	
	private void createButtonRegistration() {
		JPanel buttonPanelRegistration = new JPanel();
		buttonPanelRegistration.setLayout(null);
		buttonPanelRegistration.setBounds(10,450,400,100);
		//buttonPanelRegistration.setBackground(Color.white);
		this.panel.add(buttonPanelRegistration);
		
		JButton valider = new JButton("Valider");
		valider.setFocusable(false);
		valider.setBounds(100,5,110,30);
		
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
        
        panel.add(labelField);
        panel.add(textField);
        
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	
}
