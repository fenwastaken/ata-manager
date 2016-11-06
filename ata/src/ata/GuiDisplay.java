package ata;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class GuiDisplay extends JDialog{
	
	Vector<String> vec;
	
	public GuiDisplay(Vector<String> vec){
		this.setSize(800, 600);
		this.setTitle("Affichage");
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(true);
		this.vec = vec;
		initControles();
	}
	
	public void initControles(){
		
		JPanel zoneClient = (JPanel)this.getContentPane();
		JTextArea ta = new JTextArea();
		ta.setEditable(false);
		zoneClient.add(ta);
		for(String str : vec){
			ta.append(str + "\n");
		}
	}
	

}
