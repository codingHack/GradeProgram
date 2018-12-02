package Window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifyDialog extends JFrame implements ActionListener {
	// �й�, �̸�, 1~16 (�ϳ��ϳ� ����), �⼮, ����, �Ἦ, ���
	JTextField studentNumber;
	JTextField name;
	JTextField attend1;
	JTextField attend2;
	JTextField attend3;
	JTextField attend4;
	JTextField attend5;
	JTextField attend6;
	JTextField attend7;
	JTextField attend8;
	JTextField attend9;
	JTextField attend10;
	JTextField attend11;
	JTextField attend12;
	JTextField attend13;
	JTextField attend14;
	JTextField attend15;
	JTextField attend16;
	JTextField att;
	JTextField late;
	JTextField abs;
	JTextField extra;
	JButton apply;
	JButton cancel;
	public ModifyDialog() {
		JFrame modifier = new JFrame("������");
		modifier.setLayout(new BorderLayout());
		modifier.setSize(300, 280);
		modifier.setLocation(600, 300);
		modifier.setResizable(false);	//������ ����
		modifier.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		modifier.setVisible(true);
		
		addPanel(modifier);
	}
	
	public void addPanel(JFrame modifier) {
		// ������ ������������ ��ĥ �ʿ� ��
		// �й�, �̸�, 1~16 (�ϳ��ϳ� ����), �⼮, ����, �Ἦ, ���
		JPanel mainMod = new JPanel();
		
		mainMod.setLayout(new FlowLayout());
		mainMod = addComponent(mainMod);
		
		modifier.add(mainMod, BorderLayout.CENTER);
	}
	
	public JPanel addComponent(JPanel mainMod) {
		JLabel lstuNum;
		JLabel lname;
		JLabel lattend;
		JLabel latt1start, latt1end;
		JLabel latt2start, latt2end;
		JLabel latt3start, latt3end;
		JLabel latt4start, latt4end;
		
		mainMod.add(lstuNum = new JLabel("�й� : "));
		lstuNum.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		mainMod.add(studentNumber = new JTextField(6));
		
		mainMod.add(lname = new JLabel("�̸� : "));
		lname.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		mainMod.add(name = new JTextField(6)); 
		
		mainMod.add(lattend = new JLabel("�⼮ üũ(o, x)"));
		lattend.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 30));
		
		mainMod.add(latt1start = new JLabel("             [1  :  "));
		latt1start.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		mainMod.add(attend1 = new JTextField(2)); 
		mainMod.add(attend2 = new JTextField(2));
		mainMod.add(attend3 = new JTextField(2));
		mainMod.add(attend4 = new JTextField(2));
		attend1.setText("x"); attend2.setText("x");
		attend3.setText("x"); attend4.setText("x");
		mainMod.add(latt1end = new JLabel("  ]            "));
		latt1end.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		
		mainMod.add(latt2start = new JLabel("       [2  :  "));
		latt2start.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		mainMod.add(attend5 = new JTextField(2)); 
		mainMod.add(attend6 = new JTextField(2));
		mainMod.add(attend7 = new JTextField(2));
		mainMod.add(attend8 = new JTextField(2));
		attend5.setText("x"); attend6.setText("x");
		attend7.setText("x"); attend8.setText("x");
		mainMod.add(latt2end = new JLabel("  ]      "));
		latt2end.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		
		mainMod.add(latt3start = new JLabel("       [3  :  "));
		latt3start.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		mainMod.add(attend9 = new JTextField(2)); 
		mainMod.add(attend10 = new JTextField(2));
		mainMod.add(attend11 = new JTextField(2));
		mainMod.add(attend12 = new JTextField(2));
		attend9.setText("x"); attend10.setText("x");
		attend11.setText("x"); attend12.setText("x");
		mainMod.add(latt3end = new JLabel("  ]      "));
		latt3end.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		
		mainMod.add(latt4start = new JLabel("       [4  :  "));
		latt4start.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		mainMod.add(attend13 = new JTextField(2)); 
		mainMod.add(attend14 = new JTextField(2));
		mainMod.add(attend15 = new JTextField(2));
		mainMod.add(attend16 = new JTextField(2));
		attend13.setText("x"); attend14.setText("x");
		attend15.setText("x"); attend16.setText("x");
		mainMod.add(latt4end = new JLabel("  ]      "));
		latt4end.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
		
		mainMod.add(apply = new JButton("Ȯ   ��"));
		mainMod.add(apply = new JButton("��   ��"));
		
		return mainMod;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
