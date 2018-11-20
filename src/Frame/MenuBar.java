package Frame;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MenuBar extends JMenuBar implements ActionListener{
	JMenuItem open, save, close;
	KeyStroke key;
	public MenuBar() {
		
		//this.setSize(1200,200);
		//���ϰ���
		JMenu fileMenu = new JMenu("����");
		open = new JMenuItem("����");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		open.setAccelerator(key);
		open.addActionListener(this);
		fileMenu.add(open);
		
		save = new JMenuItem("����");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		save.setAccelerator(key);
		save.addActionListener(this);
		fileMenu.add(save);
		
		fileMenu.addSeparator();
		close = new JMenuItem("����");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK);
		close.setAccelerator(key);
		close.addActionListener(this);
		fileMenu.add(close);
		
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
		//JMenuitem mi = (JMeunitem)(e.getSource());
		Object mi = e.getSource();
		if(mi==open) {
			System.out.println("hi");
			System.exit(1);
		}else if(mi==save) {
			System.out.println("hi");
			System.exit(1);
		}else {
			
		}
		/*switch(mi.getText()) {
		case "����":
			System.exit(0);
			//���� ���� �̺�Ʈ
			break;
		case "����":
			System.exit(0);
			//���� ���� �̺�Ʈ
			break;
		case "����":
			System.out.println("hi");
			System.exit(0);
			//���� ���� �̺�Ʈ
			break;
		}*/
	}
}
