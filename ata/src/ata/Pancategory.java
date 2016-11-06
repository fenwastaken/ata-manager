package ata;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pancategory extends JPanel{
	
	Checkbox cb1 = new Checkbox("Formation", false);
	Checkbox cb2 = new Checkbox("Execution", false);
	Checkbox cb3 = new Checkbox("Controles", false);
	Checkbox cb4 = new Checkbox("Encadrement", false);
	Checkbox cb5 = new Checkbox("APRS", false);

	JTextField tf = new JTextField();
	
	public Pancategory(String labText, boolean tfb, boolean cb){
		this.setPreferredSize(new Dimension(800, 40));
		this.setMaximumSize(new Dimension(800, 40));
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panLab = new JPanel();
		panLab.setPreferredSize(new Dimension(70,25));
		//panLab.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		JLabel lab = new JLabel(labText);
		panLab.add(lab);
		
		this.add(panLab);
		
		tf.setPreferredSize(new Dimension(200, 25));
		tf.setMaximumSize(new Dimension(200, 25));
		
		
		if(tfb){
			this.add(tf);
		}
		

		
		
		if(cb){
			this.add(cb1);
			this.add(cb2);
			this.add(cb3);
			this.add(cb4);
			this.add(cb5);
		}	
	}

	public Checkbox getCb1() {
		return cb1;
	}

	public void setCb1(Checkbox cb1) {
		this.cb1 = cb1;
	}

	public Checkbox getCb2() {
		return cb2;
	}

	public void setCb2(Checkbox cb2) {
		this.cb2 = cb2;
	}

	public Checkbox getCb3() {
		return cb3;
	}

	public void setCb3(Checkbox cb3) {
		this.cb3 = cb3;
	}

	public Checkbox getCb4() {
		return cb4;
	}

	public void setCb4(Checkbox cb4) {
		this.cb4 = cb4;
	}

	public Checkbox getCb5() {
		return cb5;
	}

	public void setCb5(Checkbox cb5) {
		this.cb5 = cb5;
	}

	public JTextField getTf() {
		return tf;
	}

	public void setTf(JTextField tf) {
		this.tf = tf;
	}
	
}
