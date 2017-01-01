package ata;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GuiSelectElement extends JPanel{

	public GuiSelectElement(Line line){
		
		JLabel lab = new JLabel(line.toString());
		lab.setHorizontalTextPosition(SwingConstants.LEFT);
		
		JPanel panLab = new JPanel();
		panLab.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLab.setPreferredSize(new Dimension(1080, 30));
		panLab.add(lab);
		
		panLab.add(lab);
		this.add(panLab);
		
		JPanel panButton = new JPanel();
		this.add(panButton);
		panButton.setPreferredSize(new Dimension(100, 35));
		panButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btDelete = new JButton("Effacer");
		panButton.add(btDelete);
		
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setPreferredSize(new Dimension(1200, 46));
		this.setMaximumSize(new Dimension(1200, 46));
		
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
}
