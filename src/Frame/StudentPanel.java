package Frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;

import Window.AdditStudent;

public class StudentPanel extends TopPanel{

	//AdditStudent s = new AdditStudent();
	public StudentPanel(){
		JButton btn = new JButton("�л��߰�");
		btn.setPreferredSize(new Dimension(130, 50));
		btn.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		btn.addActionListener(new MyActionListener());
		add(btn);
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
	}
	
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("�л��߰�")) {
            	System.out.println("�л��߰��� ����");
            	new AdditStudent();
            }
		}
	}
}
