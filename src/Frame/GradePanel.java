package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DBInfo.StudentDB;
import DBInfo.GradeDB;
import Main.Student;
import Window.AdditStudent;
import Window.addGrade;
import Window.editRatio;
import Window.editScore;
import Window.removeGrade;

public class GradePanel extends TopPanel implements ActionListener, MouseListener{
	private JScrollPane studentT;
	Vector stulist; // �л� ����Ʈ
	Vector cols; // ���̺� ���
	DefaultTableModel model;
	JTable table;

	JComboBox search_jcb;//�˻� ����
	JButton search_btn;//�˻��ϱ��ư
	JButton addItem;// �׸��߰���ư
	JButton renameItem;// �׸������ư
	JButton inputScore;// ����������ư
	JButton ratioScore;// ����������ư
	JTextField search_Text;//�˻���ư
	String studentNumber;

	GradeDB g;


	public GradePanel() {
		g = new GradeDB();
		stulist=g.getMemberList();
		this.setLayout(new BorderLayout());
		JPanel top = createTop();
		this.add(top, BorderLayout.NORTH);

		JPanel mid = new JPanel();
		studentT = makeStudentTable();
		mid.add(studentT);
		this.add(mid, BorderLayout.CENTER);
		
		g.getGradeRatio();
	}

	public JPanel createTop() {
		JPanel j = new JPanel();
		JPanel jWest = new JPanel();
		JPanel jEast = new JPanel();

		j.setLayout(new BorderLayout());
		jWest.setLayout(new FlowLayout());
		jEast.setLayout(new FlowLayout());

		// search_jcb.setSelectedItem(cols);
		search_Text = new JTextField("");// ���߿� ���� �̾�����
		search_Text.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		search_Text.setPreferredSize(new Dimension(150, 50));

		String[] head = { "�й�", "�̸�", "����" };
		search_jcb = new JComboBox(head);
		search_jcb.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		search_jcb.setPreferredSize(new Dimension(80, 50));

		search_btn = new JButton("�л� �˻�");
		search_btn.setPreferredSize(new Dimension(120, 50));
		search_btn.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		// search_btn.addActionListener(this);

		inputScore = new JButton("���� �Է�");
		inputScore.setPreferredSize(new Dimension(130, 50));
		inputScore.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		inputScore.addActionListener(this);

		addItem = new JButton("�׸� �߰�");
		addItem.setPreferredSize(new Dimension(130, 50));
		addItem.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		addItem.addActionListener(this);

		renameItem = new JButton("�׸� ����");
		renameItem.setPreferredSize(new Dimension(130, 50));
		renameItem.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		renameItem.addActionListener(this);

		ratioScore = new JButton("���� ����");
		ratioScore.setPreferredSize(new Dimension(130, 50));
		ratioScore.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		ratioScore.addActionListener(this);

		jWest.add(search_jcb);
		jWest.add(search_Text);
		jWest.add(search_btn);

		jEast.add(inputScore);
		jEast.add(addItem);
		jEast.add(renameItem);
		jEast.add(ratioScore);

		j.add(jWest, BorderLayout.WEST);
		j.add(jEast, BorderLayout.EAST);

		return j;
	}

	public JScrollPane makeStudentTable() {
		GradeDB gradedb=new GradeDB();
		Font f = new Font("HY����L", Font.BOLD, 20);// ���̺� ��� ����

		cols = getColumn(); // ��� ������ ����	
		model = new DefaultTableModel(stulist, cols);
		table = new JTable(model);

		table.getTableHeader().setReorderingAllowed(false);// column ��ġ����x
		table.setFont(new Font("HY����L", 1, 15));// ���̺� ��Ʈ����
		table.setRowHeight(50);// ���̺� ���̺���
		table.addMouseListener(this);
		JScrollPane pane = new JScrollPane(table);

		JTableHeader header = table.getTableHeader();
		header.setFont(f);

		pane.setPreferredSize(new java.awt.Dimension(1185, 601));// ���̺� ������ ����
		String fieldName[]=g.getFieldName();
		
		table.getColumn("��ȣ").setPreferredWidth(1);
		table.getColumn("�й�").setPreferredWidth(4);
		table.getColumn("�̸�").setPreferredWidth(1);
		table.getColumn("����").setPreferredWidth(1);
		table.getColumn("�հ�").setPreferredWidth(1);


		return pane;
	}

	public Vector getColumn() {
		Vector col = new Vector();
		int fieldNum=g.getFieldNum();
		int ratio[]=g.getRatio();
		String fieldName[]=g.getFieldName();
		col.add("��ȣ");
		for(int i=1;i<4;i++) {
			col.add(fieldName[i]);
		}
		for(int i=4;i<fieldNum;i++) {
			col.add(fieldName[i]+"("+ ratio[i-4] +"%)");
		}
		col.add("�հ�");
		return col;
	}

	public void JTableRefresh() {
		g=new GradeDB();
		model = new DefaultTableModel(g.getMemberList(), getColumn());// DB������ �ٽ� �޾� ���̺� �ʱ�ȭ
		table.setModel(model); // ���̺� ���ΰ�ħ
	}
	
	public void addColumn(String newCol) {
		g.addColumn(newCol);
	}
	
	public void removeColumn(String removeCol) {
		g.removeColumn(removeCol);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("���� �Է�")) {
			new editScore(g , this);		
		} else if (b.getText().equals("�׸� �߰�")) {
			new addGrade(g , this);
		} else if (b.getText().equals("�׸� ����")) {
			new removeGrade(g , this);			
		}else if (b.getText().equals("���� ����")) {
			new editRatio(g , this);			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int rowSelect = table.getSelectedRow();
		studentNumber = (String)table.getValueAt(rowSelect,1); //2��° row�� �ִ� �й� ������
		System.out.println(studentNumber);//���° �� Ŭ���ߴ��� ��ġ Ȯ��
		//aClick = new AdditStudent(studentNumber, this, 1);//�����Ҷ� Ŭ���� ������ �ѱ�� ����
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
