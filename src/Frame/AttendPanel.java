package Frame;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DBInfo.AttendDB;
import Main.Student;
import Window.ModifyDialog;
import DBInfo.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendPanel extends TopPanel {
	private ModifyDialog modifier;
	private JScrollPane sp;
	private JTable table;
	private JPanel midP;
	
	private String tHead[] = {"����", "�й�", "�̸�", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"�⼮", "����", "�Ἦ", "���"};
	private Vector<String> memberList;
	private String members[][];
	// DB�� �ԷµǾ��ִ� �л� ����ŭ �޴� ������ �ٲپ�� �Ѵ�.
	
	// for Paging
	private int currentEntryIndex;
	private int numberOfEntries;
	// ���� �������� ������ 10��
	private String curDatas[][];
	private JTextField indexTextField;
	private JTextField maxTextField;
	
	DefaultTableModel model;
	private JComboBox nameSort;
	private JButton search;
	private JButton modify;
	private JButton previousPage;
	private JButton nextPage;
	private JTextField typeName;
	
	AttendDB stu_db;	//StudentDB �ڽ�
	Student stu;
	Connection con;
	ResultSet rs;
//	PreparedStatement pstmt;
	
	public AttendPanel() {
		//DB�� ���� �� ���� ���� ������� �ұ�
		stu_db = new AttendDB(this);
		
		memberList = stu_db.getMemberList(); // 2nd dim Vector
		if(memberList == null) {
			currentEntryIndex = 0;
			numberOfEntries = 0;
		}
		// table�� �ֱ� ���� �ʿ��� ����
		members = new String[memberList.size()][23];
		System.out.print("check");
		members = setProperty(memberList.size(), 23);
		// members(��ü)���� curDatas(10����)
		curDatas = new String[10][23];
		curDatas = getMembers(0);
		
		JPanel topP = makeTop();
		midP = new JPanel();
		JPanel botP;
		
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		sp = makeAttendTable();
		midP.add(sp);
		
		midP.add(sp);
		// 1PAGE = rs.next() 10�� �д´�. 
		botP = makePaging();
		
		this.add(topP, BorderLayout.NORTH);
		this.add(midP, BorderLayout.CENTER);
		this.add(botP, BorderLayout.SOUTH);
	}
	
	// setProperty(int x, int y) : ����, �й�, �̸� �κи� ���� �־��ش�.
	public String[][] setProperty(int x, int y) {
		String[][] mem = new String[x][y];
		try {
			con = stu_db.getConnection().makeConnection();
			String sql = "SELECT * FROM student.attend";
			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			for(int i=0; i<x; i++) {
				rs.next();
				mem[i][0] = rs.getString("number");
				mem[i][1] = rs.getString("studentNumber");
				mem[i][2] = rs.getString("name");
				mem[i][3] = rs.getString("attendString").substring(0, 1); // 1
				mem[i][4] = rs.getString("attendString").substring(1, 2); // 2
				mem[i][5] = rs.getString("attendString").substring(2, 3); // 3
				mem[i][6] = rs.getString("attendString").substring(3, 4); // 4
				mem[i][7] = rs.getString("attendString").substring(4, 5); // 5
				mem[i][8] = rs.getString("attendString").substring(5, 6); // 6
				mem[i][9] = rs.getString("attendString").substring(6, 7); // 7
				mem[i][10] = rs.getString("attendString").substring(7, 8); // 8
				mem[i][11] = rs.getString("attendString").substring(8, 9); // 9
				mem[i][12] = rs.getString("attendString").substring(9, 10); // 10
				mem[i][13] = rs.getString("attendString").substring(10, 11); // 11
				mem[i][14] = rs.getString("attendString").substring(11, 12); // 12
				mem[i][15] = rs.getString("attendString").substring(12, 13); // 13
				mem[i][16] = rs.getString("attendString").substring(13, 14); // 14
				mem[i][17] = rs.getString("attendString").substring(14, 15); // 15
				mem[i][18] = rs.getString("attendString").substring(15, 16); // 16
				mem[i][19] = rs.getString("att");
				mem[i][20] = rs.getString("late");
				mem[i][21] = rs.getString("abs");
				mem[i][22] = rs.getString("extra");
				if(i%10 == 0) {
					numberOfEntries++;
				}
			}
			rs.close();
			pstmt.close();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return mem;
	}
	
	// getMembers(int start) : members���� start~start+10������ data�� �����´�.
	public String[][] getMembers(int start){
		String[][] mem = new String[10][23];
		int check = start/10;
		
		for(int i=start; i<start+10; i++) {
			for(int j=0; j<23; j++) {
				if(memberList.size() > i)
				mem[i-(check*10)][j] = members[i][j];
			}
		}
		
		return mem;
	}
	
	// makeTop() : Top�κ� �г��̴�.
	public JPanel makeTop() {
		JPanel topP = new JPanel();
		JPanel topP_search = new JPanel();	// �˻��� ������ setDefault�� �ش� ���ڵ� �ϳ��� �����ش�. �̸��� �ڽ�����. �̸� �Ǵ� �й����� �˻�. ũ������ �ʿ�
		JPanel topP_mod = new JPanel();	// ���DB�ʿ�. �л�DB�� ������ ������. ���̺� �����Ұ� ����, Ư�� ���ڵ� Ŭ�� �� ���� Ŭ����, �ش� ���ڵ忡 ���� ������ �Է�â ������. ���� �� Ȯ�� ������ ��������� ���DB�� ���.
		
		topP.setLayout(new BorderLayout());
		topP_search.setLayout(new FlowLayout());
		topP_mod.setLayout(new FlowLayout());
		
		String head[] = {"�� ��", "�� ��"};
		nameSort = new JComboBox(head);
		nameSort.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		nameSort.setPreferredSize(new Dimension(80, 50));
		search = new JButton("�� ��");
		search.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		search.setPreferredSize(new Dimension(90, 50));
		typeName = new JTextField("");
		typeName.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		typeName.setPreferredSize(new Dimension(150, 50));
		topP_search.add(nameSort);
		topP_search.add(typeName);
		topP_search.add(search);
		modify = new JButton("�⼮ ����");
		modify.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
		modify.setPreferredSize(new Dimension(150, 50));
		topP_mod.add(modify);
		
		topP.add(topP_search, BorderLayout.WEST);
		topP.add(topP_mod, BorderLayout.EAST);
		
		//�˻� ���
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		
		//�⼮���� ���
		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		
		return topP;
	}
	
	private void searchActionPerformed(ActionEvent e) {
		//textField�� ������ �ԷµǾ�����, �� �ùٸ��� �ԷµǾ����� Ȯ���Ѵ�.
		//�ԷµǾ�����, �޺��ڽ��� Ȯ���Ѵ�. ResultSet���� �̸��� �Ǵ� �й��� �����ͼ� �޺��ڽ��� ������ �������� Ȯ���Ѵ�.
		//Ȯ���� �Ǹ� DefaultTableModel�� �����Ѵ�.
		//AttendDB�� ������������ �𸣰ڴ�. �ᱹ �л��� ��ϵǾ�� ��ᵵ üũ�� �� �ִ� ���̹Ƿ� StudentDB�� ������ ������ ����Ѵ�. ������ �Ǿ���Ѵ�.
		//AttendDB�� StudentDB�κ��� ����� ������?
		//Schema AttendTable (����, �й�, �̸�, 1~16��, �⼮, ����, �Ἦ, ���)
		if(typeName.getText().equals("")) {
			System.out.println("check");
			JOptionPane.showMessageDialog(null, "�ؽ�Ʈ�ʵ忡 �˻� ������ �Է��Ͻÿ�.");
		}
		else{
			System.out.println(nameSort.getSelectedIndex());
			if(nameSort.getSelectedIndex() == 0 && stu_db.isNum(typeName.getText())) {
				//typeName.getText()�� AttendDB�� studentFound�� �̿��� 
				stu_db.searchAttendData(model, typeName.getText());
				
				
			}
			else if(nameSort.getSelectedIndex() == 1 && !(stu_db.isNum(typeName.getText()))) {
				stu_db.searchAttendData(model, typeName.getText());
			}
		}
	}
	
	private void modifyActionPerformed(ActionEvent e) {
		//���������� table�� �����Ұ��Ͽ������Ѵ�.
		//Ư�� ���ڵ带 ������ ��(�巡�״� �⺻��� �Ϸ� �� ���) �⼮ ������ư�� Ŭ���ϸ�
		//���������찡 �߰Եǰ� �ش� ���ڵ��� ���� ������ �ؽ�Ʈ�ʵ�� ��µȴ�. �Ʒ����� Ȯ�ΰ� ��� ��ư�� �����Ѵ�.
		//�ؽ�Ʈ�ʵ忡�� ������ �ϰ� ���� Ȯ���� ������. Ȯ���� ������ ���� ������ ���ŵǰ�, Table�� ���µȴ�. �����ͺ��̽��� ���µȴ�.
		boolean check=false;
		String num = new String();
		for(int i=0; i<table.getRowCount(); i++) {
			for(int j=0; j<table.getColumnCount(); j++) {
				if(table.isCellSelected(i, j)) {
					check = true;
					num = (String) table.getValueAt(i, 1);
					break;
				}
			}
			if(check) break;
		}
		if(check) {
			System.out.println(num);
			modifier = new ModifyDialog(num, stu_db, this);
		}
		else {
			JOptionPane.showMessageDialog(null, "������ ���� �����Ͻʽÿ�.");
		}
	}
	public void resetting() {
		System.out.println("����");
		numberOfEntries = 0;
		members = setProperty(memberList.size(), 23);
		curDatas = getMembers(10*currentEntryIndex);
		model.setDataVector(curDatas, tHead);
		table.getColumn("�й�").setPreferredWidth(150);
	}

	// ��� Table�� �����.
	public JScrollPane makeAttendTable() {
		model = new DefaultTableModel(curDatas, tHead) {
			//���� �Ұ�, ���� ����
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);//column ��ġ����x
		table.setFont(new Font("HY����L", 1, 15));//���̺� ��Ʈ����
		table.setRowHeight(50);//���̺� ���̺���
		table.getColumn("�й�").setPreferredWidth(150);
		//��迭 �� ũ������ �Ұ�
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
//		table.addMouseListener(this);
		JScrollPane sc = new JScrollPane(table);
		sc.setPreferredSize(new java.awt.Dimension(1185,525));
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		return sc;
	}
	
	// makePaging() : Bottom�г��� ����� ����� �����Ѵ�.
	public JPanel makePaging() {
		JPanel botP = new JPanel();
		JPanel paging = new JPanel();
		previousPage = new JButton("�� ");
		nextPage = new JButton("�� ");
		indexTextField = new JTextField(2);
		maxTextField = new JTextField(2);
		
		botP.setLayout(new BorderLayout());
		paging.setLayout(new FlowLayout());
		
		paging.add(previousPage);
		paging.add(new JLabel(" Page "));
		paging.add(indexTextField);
		paging.add(new JLabel(" / "));
		paging.add(maxTextField);
		paging.add(nextPage);
		
		indexTextField.setText("1");
		maxTextField.setText(Integer.toString(numberOfEntries));
		previousPage.setEnabled(false);
		// previousPage event
		previousPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				previousPageActionPerformed(e);
			}
		});
		
		if(numberOfEntries > 0) {
			nextPage.setEnabled(true);
		}
		else {
			nextPage.setEnabled(false);
		}
		nextPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextPageActionPerformed(e);
			}
		});
		
		botP.add(paging, BorderLayout.CENTER);
		
		return botP;
	}
	private void previousPageActionPerformed(ActionEvent e) {
		currentEntryIndex--;
		
		if(currentEntryIndex <= 0) {
			previousPage.setEnabled(false);
		}
		nextPage.setEnabled(true);
		indexTextField.setText("" + (currentEntryIndex + 1));
		// actual actionPerform
		indexTextFieldActionPerformed(e);
	}
	private void indexTextFieldActionPerformed(ActionEvent e) {
		currentEntryIndex = (Integer.parseInt(indexTextField.getText()) - 1);
		
		if(numberOfEntries != 0 && currentEntryIndex < numberOfEntries) {
			curDatas = getMembers(10*currentEntryIndex);
			model.setDataVector(curDatas, tHead);
			table.getColumn("�й�").setPreferredWidth(150);
		}
	}
	
	private void nextPageActionPerformed(ActionEvent e) {
		currentEntryIndex++;
		if(currentEntryIndex+1 >= numberOfEntries) {
			nextPage.setEnabled(false);
		}
		previousPage.setEnabled(true);
		indexTextField.setText("" + (currentEntryIndex + 1));
		// actual actionPerform
		indexTextFieldActionPerformed(e);
	}
}
