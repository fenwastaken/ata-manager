package ata;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GuiSelectElement extends JPanel{

	Line line;
	JLabel lab = new JLabel();
	JButton btDelete = new JButton();
	String strBt;
	
	public GuiSelectElement(Line line){

		this.line = line;
		
		if(line.isActive()){
				strBt = "Effacer";
				lab.setForeground(Color.BLACK);
		}
		else{
				strBt = "Rétablir";
				lab.setForeground(Color.RED);
		}
		
		btDelete.setText(strBt);
	

		lab.setText(line.toString());
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

		


		panButton.add(btDelete);

		this.setBorder(BorderFactory.createEtchedBorder());
		this.setPreferredSize(new Dimension(1200, 46));
		this.setMaximumSize(new Dimension(1200, 46));


		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		btDelete.addActionListener(new appActionListener());
	}
	
	public void deleteToggle(){
		if(line.isActive()){
			line.setActive(false);
			try {
				Manager.updateLine(line);
				strBt = "Rétablir";
				lab.setForeground(Color.RED);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{

			line.setActive(true);
			try {
				Manager.updateLine(line);
				strBt = "Effacer";
				lab.setForeground(Color.BLACK);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		btDelete.setText(strBt);
	}
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == btDelete){
				deleteToggle();
			}
			
		}

	}
	
}


