package Frame;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MenuBar extends JMenuBar implements ActionListener{
	public MenuBar() {
		JMenuItem item;
		KeyStroke key;
		//this.setSize(1200,200);
		//���ϰ���
		JMenu fileMenu = new JMenu("����");
		item = new JMenuItem("����");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		fileMenu.add(item);
		
		item = new JMenuItem("����");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		fileMenu.add(item);
		
		fileMenu.addSeparator();
		item = new JMenuItem("����");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		fileMenu.add(item);
		
		JMenu gradeMenu = new JMenu("����");
		JMenu attendMenu = new JMenu("���");
		JMenu staticMenu = new JMenu("���");
		JMenu studentMenu = new JMenu("�л�");
		JMenu lectureMenu = new JMenu("����");
		
		this.add(fileMenu);
		this.add(gradeMenu);	
		this.add(attendMenu);	
		this.add(staticMenu);	
		this.add(studentMenu);	
		this.add(lectureMenu);	
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem mi = (JMenuItem)(e.getSource());
		switch(mi.getText()) {
		case "����":
			//���� ���� �̺�Ʈ
			break;
		case "����":
			//���� ���� �̺�Ʈ
			break;
		case "����":
			//���� ���� �̺�Ʈ
			break;
		}
	}
}
