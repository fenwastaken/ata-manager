package ata;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JFrame{

	String version = "Version 0.3";
	
	JPanel zoneclient;

	Pancategory panName = new Pancategory("Nom", true, false);
	Pancategory panDate = new Pancategory("Date", true, false);
	Pancategory panType = new Pancategory("Type A/C", true, false);
	Pancategory panImmat = new Pancategory("IMMAT", true, false);
	Pancategory panAta = new Pancategory("ATA", true, false);
	Pancategory panTache = new Pancategory("Tache", true, false);
	Pancategory panCheck = new Pancategory("Fonctions", false, true);

	JComboBox<String> boxAta = new JComboBox<String>();
	JComboBox<String> boxType = new JComboBox<String>();
	JComboBox<String> boxImma = new JComboBox<String>();
	JComboBox<String> boxTask = new JComboBox<String>();
	JComboBox<String> boxName = new JComboBox<String>();
	JComboBox<String> boxDate = new JComboBox<String>();
	



	JButton btOK = new JButton("Valider");
	JButton btDis = new JButton("Afficher");
	JButton btExcel = new JButton("Excel");


	JLabel labRetour = new JLabel();

	public Gui(){
		this.setSize(650, 350);
		this.setTitle("Ata manager - " + version);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		try {
			initControles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initControles() throws IOException{

		zoneclient = (JPanel)this.getContentPane();

		zoneclient.setLayout(new BoxLayout(zoneclient, BoxLayout.Y_AXIS));

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		String result = df.format(date);
		panDate.getTf().setText(result);

		panName.getTf().setText("Claude Leyour");

		zoneclient.add(panName);
		zoneclient.add(panDate);
		zoneclient.add(panType);
		zoneclient.add(panImmat);
		zoneclient.add(panAta);
		zoneclient.add(panTache);
		zoneclient.add(panCheck);

		panAta.add(boxAta);
		panType.add(boxType);
		panTache.add(boxTask);
		panImmat.add(boxImma);
		//panDate.add(boxDate);
		panName.add(boxName);
		
		
		feedBoxAta();
		feedBoxType();
		feedBoxImmat();
		feedBoxTask();
		feedBoxName();
		
		JPanel panBT = new JPanel();
		panBT.add(labRetour);
		panBT.add(btOK);
		panBT.add(btDis);
		panBT.add(btExcel);
		panBT.add(Box.createRigidArea(new Dimension(30,10)));


		panBT.setLayout(new FlowLayout(FlowLayout.RIGHT));

		zoneclient.add(panBT);

		boxAta.addItemListener(new appItemListener());
		boxAta.addFocusListener(new appFocusListener());
		btOK.addFocusListener(new appFocusListener());
		btOK.addActionListener(new appActionListener());
		btDis.addActionListener(new appActionListener());
		btExcel.addActionListener(new appActionListener());
		
		boxType.addItemListener(new appItemListener());
		boxType.addFocusListener(new appFocusListener());

		boxImma.addItemListener(new appItemListener());
		boxImma.addFocusListener(new appFocusListener());
		
		boxTask.addItemListener(new appItemListener());
		boxTask.addFocusListener(new appFocusListener());
		
		boxName.addItemListener(new appItemListener());
		boxName.addFocusListener(new appFocusListener());

		
	}
	
	public void feedBoxAta(){
		boxAta.removeAllItems();
		boxAta.addItem(" ");
		Vector<String> vecStr;
		try {
			vecStr = Manager.getAllAtas();
			for(String str : vecStr){
				boxAta.addItem(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void feedBoxType(){
		boxType.removeAllItems();
		boxType.addItem(" ");
		Vector<String> vecStr;
		try {
			vecStr = Manager.getAllTypes();
			for(String str : vecStr){
				boxType.addItem(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void feedBoxImmat(){
		boxImma.removeAllItems();
		boxImma.addItem(" ");
		Vector<String> vecStr;
		try {
			vecStr = Manager.getAllImmats();
			for(String str : vecStr){
				boxImma.addItem(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void feedBoxTask(){
		boxTask.removeAllItems();
		boxTask.addItem(" ");
		Vector<String> vecStr;
		try {
			vecStr = Manager.getAllTasks();
			for(String str : vecStr){
				boxTask.addItem(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void feedBoxName(){
		boxName.removeAllItems();
		boxName.addItem(" ");
		Vector<String> vecStr;
		try {
			vecStr = Manager.getAllNames();
			for(String str : vecStr){
				boxName.addItem(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void display(){
		try {
			Vector<String>  vec = Manager.getAllLinesToString();
			GuiDisplay gd = new GuiDisplay(vec);
			gd.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertLine(){
		String name = panName.getTf().getText();
		String date = panDate.getTf().getText();
		String type = panType.getTf().getText();;
		String immat = panImmat.getTf().getText();
		String ata = panAta.getTf().getText();
		String task = panTache.getTf().getText();
		int formation = 0;
		int execution = 0;
		int controle = 0;
		int encadrement = 0;
		int aprs = 0;

		if(panCheck.getCb1().getState()){
			formation = 1;
		}
		if(panCheck.getCb2().getState()){
			execution = 1;
		}
		if(panCheck.getCb3().getState()){
			controle = 1;
		}
		if(panCheck.getCb4().getState()){
			encadrement = 1;
		}
		if(panCheck.getCb5().getState()){
			aprs = 1;
		}

		try {
			int ret = Manager.saveLine(name, date, type, immat, ata, task, formation, execution, controle, encadrement, aprs);
			if(ret == 1){
				labRetour.setText("Ligne insérée!");
				panAta.getTf().setText("");
				panImmat.getTf().setText("");
				panTache.getTf().setText("");
				panType.getTf().setText("");
				panCheck.getCb1().setState(false);
				panCheck.getCb2().setState(false);
				panCheck.getCb3().setState(false);
				panCheck.getCb4().setState(false);
				panCheck.getCb5().setState(false);
				
				feedAll();

			}
			else{
				labRetour.setText("Echec insertion");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void feedAll(){
		feedBoxAta();
		feedBoxType();
		feedBoxImmat();
		feedBoxTask();
		feedBoxName();
	}
	
	public void excelify(){
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				String file = Excel.create();
				System.out.println("done");
				try {
					Desktop dt = Desktop.getDesktop();
					dt.open(new File(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		Thread t = new Thread(r);
		t.start();
	}

	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btOK){
				if(panType.getTf().getText().isEmpty() || panImmat.getTf().getText().isEmpty() || panTache.getTf().getText().isEmpty()){
					labRetour.setText("Veuillez renseigner le type, l'immatriculation et la tache.");
				}
				else{
					insertLine();
				}
			}
			
			if(e.getSource() == btDis){
				display();
			}
			
			if(e.getSource() == btExcel){
				excelify();
			}
		}

	}

	class appItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == boxAta && e.getStateChange() == 1 && boxAta.getSelectedIndex() != 0){
				String text = panAta.getTf().getText();
				String ata = boxAta.getSelectedItem().toString();
				ata = ata.substring(0, ata.indexOf(","));
				if(!text.isEmpty()){
					panAta.getTf().setText(text + ", " + ata);
				}
				else{
					panAta.getTf().setText(ata);
				}

			}
			
			if(e.getSource() == boxType && e.getStateChange() == 1 && boxType.getSelectedIndex() != 0){
				String type = boxType.getSelectedItem().toString();
				panType.getTf().setText("");
				panType.getTf().setText(type);
			}
			
			if(e.getSource() == boxImma && e.getStateChange() == 1 && boxImma.getSelectedIndex() != 0){
				String imma = boxImma.getSelectedItem().toString();
				panImmat.getTf().setText("");
				panImmat.getTf().setText(imma);
				
				try {
					if(Manager.ImmatExists(imma)){
						String type = Manager.getTypeFromImmat(imma);
						panType.getTf().setText(type);
						boxType.setSelectedItem(type);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			if(e.getSource() == boxTask && e.getStateChange() == 1 && boxTask.getSelectedIndex() != 0){
				String Task = boxTask.getSelectedItem().toString();
				panTache.getTf().setText("");
				panTache.getTf().setText(Task);
			}
			
			if(e.getSource() == boxName && e.getStateChange() == 1 && boxName.getSelectedIndex() != 0){
				String Name = boxName.getSelectedItem().toString();
				panName.getTf().setText("");
				panName.getTf().setText(Name);
			}
			
			fixGui();
			
		}

	}

	public void fixGui(){
		zoneclient.revalidate();
		zoneclient.repaint();
		panCheck.repaint();
		panCheck.revalidate();
	}
	
	class appFocusListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			zoneclient.revalidate();
			zoneclient.repaint();
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			fixGui();
			if(arg0.getSource() == btOK){
				labRetour.setText("");
			}
		}

	}

}
