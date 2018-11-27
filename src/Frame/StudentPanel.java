package Frame;

import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DBInfo.StudentDB;
import Main.Student;
import Window.AdditStudent;

public class StudentPanel extends TopPanel implements ActionListener, MouseListener{
	private JScrollPane studentT;
	Vector stulist; //�л� ����Ʈ
	Vector cols; // ���̺� ���
	DefaultTableModel model;
	JTable table;
	
	JComboBox search_jcb; //�˻��Ϸ��� �޺��ڽ�
	JButton btn, mbtn, dbtn, search_btn; //���, ����, ����, �˻���ư
	JButton resetB, stuNumSort, nameSort; // ����, �й�, �̸������� �����ϴ� ��ư
	JLabel topic;
	JTextField search_Text;
	
	StudentDB s;
	Student stu;
	AdditStudent aClick, bClick; //delete �޼ҵ� �θ�������(additStudent)
	String studentNumber;
	
	public StudentPanel(){
		s = new StudentDB(); // db�� ����� �����͸� �������� ����
		stu = new Student(); //search�Ҷ� �� �������� ����
		stulist = s.getMemberList();
		
		this.setLayout(new BorderLayout());
		JPanel topS;
		topS = makesT();
		this.add(topS, BorderLayout.NORTH);
		
		JPanel midS = new JPanel();
		studentT = makeStudentTable();
		midS.add(studentT);
		this.add(midS, BorderLayout.CENTER);
		
		JPanel botS;
		botS = makesB();
		this.add(botS, BorderLayout.SOUTH);
	}

	//Top�г� �κ� 
	public JPanel makesT() {
		cols = getColumn();//�л� �˻��ϱ� ���� ������ �޾ƿ�
		
		JPanel sTop = new JPanel();
		JPanel sTop_search = new JPanel();
		JPanel sTop_mid = new JPanel();
		JPanel sTop_mod = new JPanel();
		
		sTop.setLayout(new BorderLayout());
		sTop_search.setLayout(new FlowLayout());
		sTop_mid.setLayout(new FlowLayout());
		sTop_mod.setLayout(new FlowLayout());
		
		//search_jcb.setSelectedItem(cols);
		topic = new JLabel("Student");
		topic.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 40));
		
		
		search_Text = new JTextField("");//���߿� ���� �̾�����
		search_Text.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		search_Text.setPreferredSize(new Dimension(150, 50));
		
		String[] head = {"�й�","�̸�"};
		search_jcb = new JComboBox(head);
		search_jcb.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		search_jcb.setPreferredSize(new Dimension(80, 50));
		
		search_btn = new JButton("�л� �˻�");
		search_btn.setPreferredSize(new Dimension(120, 50));
		search_btn.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		search_btn.addActionListener(this);
		
		btn = new JButton("�л� ���");
		btn.setPreferredSize(new Dimension(130, 50));
		btn.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		btn.addActionListener(this);
		
		mbtn = new JButton("���� ����");
		mbtn.setPreferredSize(new Dimension(130, 50));
		mbtn.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		mbtn.addActionListener(this);
		
		dbtn = new JButton("�л� ����");
		dbtn.setPreferredSize(new Dimension(130, 50));
		dbtn.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		dbtn.addActionListener(this);
		
	
		sTop_search.add(search_jcb);
		sTop_search.add(search_Text);
		sTop_search.add(search_btn);
		sTop_mid.add(topic);
		sTop_mod.add(btn);
		sTop_mod.add(mbtn);
		sTop_mod.add(dbtn);
		
		sTop.add(sTop_search, BorderLayout.WEST);
		sTop.add(sTop_mid, BorderLayout.CENTER);
		sTop.add(sTop_mod,BorderLayout.EAST);

		return sTop;
	}
	
	//mid �гκκ�
	public JScrollPane makeStudentTable() {
		StudentDB studb = new StudentDB();
		Font f = new Font("HY����L", Font.BOLD, 20);//���̺� ��� ����
		
		cols = getColumn(); //��� ������ ����
		model = new DefaultTableModel(stulist, cols);
		table = new JTable(model);
		
		table.getTableHeader().setReorderingAllowed(false);//column ��ġ����x
		//table.getTableHeader().setResizingAllowed(false);//ũ������ �Ұ�
		table.setFont(new Font("HY����L", 1, 15));//���̺� ��Ʈ����
		table.setRowHeight(50);//���̺� ���̺���
		table.addMouseListener(this);
		JScrollPane pane = new JScrollPane(table);
		
		//���̺� ��ũ�� ��å(�־�� ��ũ�� ����)
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(f);
		
		pane.setPreferredSize(new java.awt.Dimension(1185, 601));//���̺� ������ ����
		table.getColumn("��ȣ").setPreferredWidth(1);
		table.getColumn("�̸�").setPreferredWidth(10);
		table.getColumn("�г�").setPreferredWidth(10);
		table.getColumn("����").setPreferredWidth(5);
		table.getColumn("�޴�����ȣ").setPreferredWidth(100);
		table.getColumn("Ư�̻���").setPreferredWidth(300);
		table.getColumn("����").setPreferredWidth(10);
		
		return pane;
	}
	
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("��ȣ");
		col.add("�й�");
		col.add("�̸�");
		col.add("�г�");
		col.add("����");
		col.add("�޴�����ȣ");
		col.add("�������");
		col.add("Ư�̻���");
		col.add("����");
		return col;
	}
	
	public void JTableRefresh() {
		StudentDB stdb = new StudentDB();
		DefaultTableModel model = new DefaultTableModel(stdb.getMemberList(), getColumn());//DB������ �ٽ� �޾� ���̺� �ʱ�ȭ
		this.model = model;
		table.setModel(model); // ���̺� ���ΰ�ħ
		
		studentNumber = null;
		
		//���� �߻��� ����κ��� ���� ŭ
		table.getColumn("��ȣ").setPreferredWidth(1);
		table.getColumn("�̸�").setPreferredWidth(10);
		table.getColumn("�г�").setPreferredWidth(10);
		table.getColumn("����").setPreferredWidth(5);
		table.getColumn("�޴�����ȣ").setPreferredWidth(100);
		table.getColumn("Ư�̻���").setPreferredWidth(300);
		table.getColumn("����").setPreferredWidth(10);
		
	}
	//bottom�κ�
	public JPanel makesB() {
		
		JPanel sBot_sort = new JPanel();
	
		sBot_sort.setLayout(new FlowLayout(FlowLayout.LEFT));
		resetB = new JButton("��  ��ȣ����");
		stuNumSort = new JButton("��  �й�����");
		nameSort = new JButton("��  �̸�����");
		//stuNumSort.setBounds(0,0,0,0);
		resetB.addActionListener(this);
		stuNumSort.addActionListener(this);
		nameSort.addActionListener(this);
		
		sBot_sort.add(resetB);
		sBot_sort.add(stuNumSort);
		sBot_sort.add(nameSort);
		
		
		return sBot_sort;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 JButton b = (JButton) e.getSource();
		 int selectedIndex = search_jcb.getSelectedIndex();
		 //String name = (String)jcb.getSelectedItem(); // ������ �޺��ڽ� string ���� ������

		 if (b.getText().equals("�л� ���")) {
		 	System.out.println("�л������ ����");
		 	new AdditStudent(this);
		 	//studentNumber = null;
		 	
		 }else if(b.getText().equals("���� ����")) {
			if(studentNumber == null) {
				JOptionPane.showMessageDialog(this, "���� ���� ���� : ���� �Ϸ��� ���� �����Ͻÿ�.");
			}else {
				bClick = new AdditStudent(studentNumber, this, 2);
			}
			
		 }else if(b.getText().equals("�л� ����")) {
			System.out.println("�л�����");
			if(studentNumber == null) {
				JOptionPane.showMessageDialog(this, "�������� : �����Ϸ��� ���� �����Ͻÿ�.");
			}else {
				aClick.deleteStudent(studentNumber);
				this.JTableRefresh();//action�κп� �־����.
				//studentNumber = null;
			}
		 }else if(b.getText().equals("��  �̸�����")) {
			 s.studentCheck(model);
			 studentNumber=null;
		 }else if(b.getText().equals("��  �й�����")) {
			 s.stuNumCheck(model);
			 studentNumber=null;
		 }else if(b.getText().equals("��  ��ȣ����")) {
			 s.numberCheck(model);
			 studentNumber=null;
		 }else if(b.getText().equals("�л� �˻�")) {
			 if(selectedIndex == 0) {
				 String getString = search_Text.getText();
				 System.out.println(getString);
				 s.stuNumberSearch(model, getString);
				 studentNumber=null;
			 }else if(selectedIndex == 1) {
				 String getString = search_Text.getText();
				 System.out.println(getString);
				 s.nameSearch(model, getString);
				 studentNumber=null;
			 }
		 }
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int rowSelect = table.getSelectedRow();
		studentNumber = (String)table.getValueAt(rowSelect,1); //2��° row�� �ִ� �й� ������
		System.out.println(studentNumber);//���° �� Ŭ���ߴ��� ��ġ Ȯ��
		aClick = new AdditStudent(studentNumber, this, 1);//�����Ҷ� Ŭ���� ������ �ѱ�� ����
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
