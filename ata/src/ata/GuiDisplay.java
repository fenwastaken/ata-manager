package ata;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GuiDisplay extends JDialog{
	
	Vector<String> vec;
	JButton btCopy;
	JTextArea ta;
	
	public GuiDisplay(Vector<String> vec){
		this.setSize(1100, 600);
		this.setTitle("Affichage");
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(true);
		this.vec = vec;
		initControles();
	}
	
	public void initControles(){
		
		JPanel zoneClient = (JPanel)this.getContentPane();
		
		zoneClient.setLayout(new BorderLayout());
		
		ta = new JTextArea();
		ta.setEditable(false);
		zoneClient.add(ta, BorderLayout.CENTER);
		
		btCopy = new JButton("Copier");
		zoneClient.add(btCopy, BorderLayout.SOUTH);
		
		for(String str : vec){
			ta.append(str + "\n");
		}
		
		btCopy.addActionListener(new appActionListener());
		
	}
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if(arg0.getSource() == btCopy){
				StringSelection stringSelection = new StringSelection(ta.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				Toolkit.getDefaultToolkit().beep();
				ta.setText("copié!");
				btCopy.setEnabled(false);
			}
			
		}
		
	
		
	}

}
