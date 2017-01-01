package ata;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class GuiSelect extends JDialog{

	Dimension sizeTf = new Dimension(100, 25);
	Dimension sizeHeightEtchedPanel = new Dimension(50, 45);

	JButton btSearch = new JButton("Recherche");
	JButton btExcel = new JButton("Excel");

	JPanel panList = new JPanel();
	JScrollPane spanCenter = new JScrollPane(panList);

	ButtonGroup rdGroup = new ButtonGroup();
	JRadioButton rdDate = new JRadioButton();
	JRadioButton rdImmat = new JRadioButton();
	JRadioButton rdType = new JRadioButton();
	JRadioButton rdAta = new JRadioButton();
	JRadioButton rdTask = new JRadioButton();
	JCheckBox cbDesc = new JCheckBox();
	JComboBox<String> cbbNom = new JComboBox<String>();
	
	JCheckBox cbDateSearch = new JCheckBox();
	JTextField tfDateSearch = new JTextField();

	public GuiSelect(){

		this.setSize(1220, 800);
		this.setTitle("Ata manager - Selecter");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		initControles();
	}

	public void initControles(){
		JPanel zoneClient = (JPanel) this.getContentPane();
		zoneClient.setLayout(new BorderLayout());

		//panCenter
		panList.setLayout(new BoxLayout(panList, BoxLayout.Y_AXIS));

		zoneClient.add(spanCenter);

		try {
			Vector<Line> vLine = Manager.getAllLinesToObject();
			for(Line ln : vLine){
				GuiSelectElement gse = new GuiSelectElement(ln);
				panList.add(gse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//panNorth

		JPanel panNorth = new JPanel();



		zoneClient.add(panNorth, BorderLayout.NORTH);

		JPanel panName = new JPanel();
		panName.setBorder(BorderFactory.createEtchedBorder());
		panName.setMinimumSize(sizeHeightEtchedPanel);
		panName.add(cbbNom);
		cbbNom.addItem("Tout");
		
		try {
			Vector<String> vNames = Manager.getAllNames();
			
			for(String str : vNames){
				cbbNom.addItem(str);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		panNorth.add(panName);
		
		JPanel panTri = new JPanel();

		JLabel labFilter = new JLabel("Tri : ");
		panTri.add(labFilter);

		rdDate.setSelected(true);
		rdDate.setText("Date");
		rdImmat.setText("Immatriculation");
		rdType.setText("Type");
		rdAta.setText("ATA");
		rdTask.setText("Tâche");


		rdGroup.add(rdDate);
		rdGroup.add(rdImmat);
		rdGroup.add(rdType);
		rdGroup.add(rdAta);
		rdGroup.add(rdTask);

		cbDesc.setText("Inverser");
		
		panTri.add(rdDate);
		panTri.add(rdImmat);
		panTri.add(rdType);
		panTri.add(rdAta);
		panTri.add(rdTask);
		panTri.add(cbDesc);

		panNorth.add(panTri);
		panTri.setBorder(BorderFactory.createEtchedBorder());
		panTri.setMinimumSize(sizeHeightEtchedPanel);

		JPanel panDateSearch = new JPanel();
		cbDateSearch.setText("Filtrer date");
		tfDateSearch.setPreferredSize(sizeTf);

		panDateSearch.add(cbDateSearch);
		panDateSearch.add(tfDateSearch);

		panDateSearch.setBorder(BorderFactory.createEtchedBorder());
		panDateSearch.setMinimumSize(sizeHeightEtchedPanel);
		panNorth.add(panDateSearch);

		JPanel panDisplayName = new JPanel();

		JCheckBox cbName = new JCheckBox();
		cbName.setText("Afficher Nom");
		cbName.setSelected(true);
		panDisplayName.add(cbName);
		panDisplayName.setBorder(BorderFactory.createEtchedBorder());
		panDisplayName.setMinimumSize(sizeHeightEtchedPanel);

		panNorth.add(panDisplayName);


		JPanel panBtSearch = new JPanel();
		panBtSearch.setMinimumSize(sizeHeightEtchedPanel);
		panBtSearch.setBorder(BorderFactory.createEtchedBorder());
		panBtSearch.add(btSearch);
		panBtSearch.add(btExcel);

		panNorth.add(panBtSearch);

		btSearch.addActionListener(new appActionListener());
		btExcel.addActionListener(new appActionListener());

	}

	public Vector<Line> feedList(){
		panList.removeAll();

		String sort = "";

		if(rdAta.isSelected()){
			sort = rdAta.getText().toString();
		}

		if(rdDate.isSelected()){
			sort = rdDate.getText().toString();
		}

		if(rdImmat.isSelected()){
			sort = rdImmat.getText().toString();
		}

		if(rdTask.isSelected()){
			sort = rdTask.getText().toString();
		}

		if(rdType.isSelected()){
			sort = rdType.getText().toString();
		}

		switch(sort){
		case "Date":
			sort = "date";
			break;
		case "Immatriculation":
			sort = "immat";
			break;
		case "Type":
			sort = "type";
			break;
		case "ATA":
			sort = "ata";
			break;
		case "Tâche":
			sort = "task";
			break;
		default:
			sort = "date";
			break;
		}

		if(cbDesc.isSelected()){
			sort += " DESC";
		}
		
		String dateFilter = null;
		
		if(cbDateSearch.isSelected()){
			dateFilter = "%" + tfDateSearch.getText() + "%";
		}
		
		String nameTri = null;
		
		if(cbbNom.getSelectedIndex() != 0){
			nameTri = cbbNom.getSelectedItem().toString();
		}
		
		Vector<Line> vLine = null;
		try {
			vLine = Manager.getFilteredLinesToObject(sort, dateFilter, nameTri);
			System.out.println(vLine.size() + " elements");
			for(Line ln : vLine){
				System.out.println(ln.toString());
				GuiSelectElement gse = new GuiSelectElement(ln);
				panList.add(gse);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panList.repaint();
		panList.revalidate();
		
		return vLine;
	}

	public void Excelify(){
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				Excel excel = new Excel();
				String file = excel.create(feedList());
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

			if(e.getSource() == btSearch){
				feedList();
			}
			
			if(e.getSource() == btExcel){
				Excelify();
			}
		}

	}


}

