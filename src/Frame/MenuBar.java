package Frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javafx.scene.paint.Color;

public class MenuBar extends JMenuBar implements ActionListener, MouseListener{
	JMenuItem item;

	private JMenu gradeMenu;
	private JMenu studentMenu;
	private JMenu lectureMenu;
	private JMenu attendMenu;
	private JMenu staticMenu;
	private JMenu IntroMenu;
	
	SuperFrame s;//���������� �ޱ�����
	KeyStroke key;
	
	public MenuBar(SuperFrame s) {
		this.s = s;
	
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
		gradeMenu = new JMenu(" ����");
		gradeMenu.setPreferredSize(new Dimension(80, 50));
		gradeMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		gradeMenu.addMouseListener(this);
				
//���		
		attendMenu = new JMenu(" ���");
		attendMenu.setPreferredSize(new Dimension(80, 50));
		attendMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		attendMenu.addMouseListener(this);
//���		
		staticMenu = new JMenu(" ���");
		staticMenu.setPreferredSize(new Dimension(80, 50));
		staticMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		staticMenu.addMouseListener(this);
//�л�
		studentMenu = new JMenu(" �л�");
		studentMenu.setPreferredSize(new Dimension(80, 50));
		studentMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		studentMenu.addMouseListener(this);
		
//����
		lectureMenu = new JMenu(" ���� ");
		lectureMenu.setPreferredSize(new Dimension(80, 50));
		lectureMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		lectureMenu.addMouseListener(this);
//��������
		IntroMenu = new JMenu(" Help");
		IntroMenu.setPreferredSize(new Dimension(80, 50));
		IntroMenu.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		IntroMenu.addMouseListener(this);
		
		gradeMenu.setToolTipText("Ŭ���ϸ� ������ ���ϴ�.");
		attendMenu.setToolTipText("Ŭ���ϸ� ����� ���ϴ�.");
		staticMenu.setToolTipText("Ŭ���ϸ� ��踦 ���ϴ�.");
		studentMenu.setToolTipText("Ŭ���ϸ� �л��� ���ϴ�.");
		lectureMenu.setToolTipText("Ŭ���ϸ� ���Ǹ� ���ϴ�.");
		IntroMenu.setToolTipText("Ŭ���ϸ� ���������� ���ϴ�.");
		
		this.add(fileMenu);
		this.add(gradeMenu);	
		this.add(attendMenu);	
		this.add(staticMenu);	
		this.add(studentMenu);	
		this.add(lectureMenu);	
		this.add(IntroMenu);
		setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JMenu m = (JMenu)e.getSource();
		if(m == gradeMenu) {
			s.change("gradeP");
			
			
		}else if(m == attendMenu) {
			s.change("attendP");
			
		}else if(m == staticMenu) {
			s.change("staticP");
			
		}else if(m == studentMenu) {
			s.change("studentP");
			
		}else if(m == lectureMenu) {
			s.change("lectureP");
			
		}else if(m == IntroMenu) {
			s.change("IntroP");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem mi = (JMenuItem) e.getSource();
		
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
			
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
