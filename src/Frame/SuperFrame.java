package Frame;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SuperFrame extends JFrame {
	public SuperFrame() {
		Container c = this.getContentPane();
		JPanel mainView = new JPanel();

		

		// ���̺� �׽�Ʈ

		String data[][] = { { "60142311", "������", "A+" }, { "60142000", "������", "A" }, { "60101111", "������", "F" } };
		String column[] = { "�й�", "�̸�", "����" };
		JTable jt=new JTable(data,column);    
	    jt.setBounds(10,10,200,300);          
	    JScrollPane sp=new JScrollPane(jt);
	    mainView.add(sp);
	    
		// ���̺� �׽�Ʈ
	    
	    c.add(mainView);
		this.setJMenuBar(new MenuBar());
		this.setTitle("���� ���� ���α׷�");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
