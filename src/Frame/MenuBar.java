package Frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuBar extends JMenuBar implements ActionListener{
	JMenuItem item, grade, student;

	static public int test = 0;
	KeyStroke key;
	public MenuBar() {
//���ϰ���
		JMenu fileMenu = new JMenu(" ����");
		item = new JMenuItem("����");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		fileMenu.setPreferredSize(new Dimension(80, 50));
		fileMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		//fileMenu.setSize(100,50);
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
//����
		JMenu gradeMenu = new JMenu(" ����");
		gradeMenu.setPreferredSize(new Dimension(80, 50));
		gradeMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		
		
		grade = new JMenuItem("���� ����");
		grade.addActionListener(this);
		gradeMenu.add(grade);
		
		/*grade = new JMenuItem("���� �ݱ�");
		grade.addActionListener(this);
		gradeMenu.add(grade);*/
		
				
//���		
		JMenu attendMenu = new JMenu(" ���");
		attendMenu.setPreferredSize(new Dimension(80, 50));
		attendMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		
//���		
		JMenu staticMenu = new JMenu(" ���");
		staticMenu.setPreferredSize(new Dimension(80, 50));
		staticMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		
//�л�
		JMenu studentMenu = new JMenu(" �л�");
		studentMenu.setPreferredSize(new Dimension(80, 50));
		studentMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		student = new JMenuItem("�л� ����");
		student.addActionListener(this);
		studentMenu.add(student);
		
//����
		JMenu lectureMenu = new JMenu(" ����");
		lectureMenu.setPreferredSize(new Dimension(80, 50));
		lectureMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
	
		
		gradeMenu.setToolTipText("Ŭ���ϸ� ������ ���ϴ�.");
		attendMenu.setToolTipText("Ŭ���ϸ� ����� ���ϴ�.");
		staticMenu.setToolTipText("Ŭ���ϸ� ��踦 ���ϴ�.");
		studentMenu.setToolTipText("Ŭ���ϸ� �л��� ���ϴ�.");
		lectureMenu.setToolTipText("Ŭ���ϸ� ���Ǹ� ���ϴ�.");
		
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
		JMenuItem mi = (JMenuItem) e.getSource();
		Object menu = e.getSource();
		
		switch(mi.getText()) {
		case "����":
			//���� ���� �̺�Ʈ
			JOptionPane.showMessageDialog(this, "���� ó��");
			break;
		case "����":
			//���� ���� �̺�Ʈ
			JOptionPane.showMessageDialog(this, "���� ó��");
			break;
		case "����":
			System.exit(0);
			//���� ���� �̺�Ʈ
			break;
		}
		
		if(menu == grade) {
			JOptionPane.showMessageDialog(this, "���� ó��");
			//���� ���� �κ� (�޴��ٿ� ��ư�� ������, menuItem�� ������ ���)
		}else if(menu == student) {
			System.out.println("hi");
			test = 1;
			//System.out.println(test);
			new SuperFrame();

		}
		
	}
}
