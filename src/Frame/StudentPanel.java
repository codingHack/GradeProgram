package Frame;

import java.awt.event.*;

import javax.swing.JButton;

import Window.AdditStudent;

public class StudentPanel extends TopPanel{

	//AdditStudent s = new AdditStudent();
	public StudentPanel(){
		JButton btn = new JButton("�л��г�");
		btn.addActionListener(new MyActionListener());
		add(btn);
	}
	
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("�л��г�")) {
            	System.out.println("�л��г��� ����");
            	new AdditStudent();
            }
		}
	}
}
