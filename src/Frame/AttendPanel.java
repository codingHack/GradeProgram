package Frame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AttendPanel extends TopPanel{
	private String tHead[] = {"����", "�й�", "�̸�", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"�⼮", "����", "�Ἦ", "���"};
	private String tBody[][] = new String[36][23];
	private JButton nameSort;
	private JButton search;
	private JButton modify;
	private JScrollPane sp;
	public AttendPanel() {
//		this.add(new JButton("�⼮ �г�"));
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JPanel topP;
		JPanel midP = new JPanel();
		JPanel botP;
		
		topP = makeTop();
		sp = makeAttendTable();
		//�̹� Table�� ��ũ���� �����ϴµ� paging�� �� �ʿ䰡 ������
		botP = makePaging();
		
		midP.add(sp);
		
		this.add(topP, BorderLayout.NORTH);
		this.add(midP, BorderLayout.CENTER);
		this.add(botP, BorderLayout.SOUTH);
	}
	
	public JPanel makeTop() {
		JPanel topP = new JPanel();
		JPanel topP_search = new JPanel();
		JPanel topP_mod = new JPanel();
		
		topP.setLayout(new BorderLayout());
		topP_search.setLayout(new FlowLayout());
		topP_mod.setLayout(new FlowLayout());
		
		nameSort = new JButton("��  �� ��");
		search = new JButton("�� ��");
		topP_search.add(nameSort);
		topP_search.add(new JTextField(20));
		topP_search.add(search);
		modify = new JButton("�⼮ ����");
		topP_mod.add(modify);
		
		topP.add(topP_search, BorderLayout.WEST);
		topP.add(topP_mod, BorderLayout.EAST);
		
		return topP;
	}
	
	public JScrollPane makeAttendTable() {
		DefaultTableModel model = new DefaultTableModel(tBody, tHead);
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setPreferredSize(new java.awt.Dimension(1185, 601));
		// ���̺� ��� ����
		DefaultTableCellRenderer tRenderer = new DefaultTableCellRenderer();
		tRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcolumn = table.getColumnModel();
		for(int i=0; i<tcolumn.getColumnCount(); i++) {
			tcolumn.getColumn(i).setCellRenderer(tRenderer);
		}
		// �� �� ���� �ø���
		table.getColumn("�й�").setPreferredWidth(200);
		table.getColumn("�̸�").setPreferredWidth(100);
		table.getColumn("���").setPreferredWidth(300);
		
		return sc;
	}
	
	public JPanel makePaging() {
		JPanel botP = new JPanel();
		JPanel paging = new JPanel();
		
		botP.setLayout(new BorderLayout());
		paging.setLayout(new FlowLayout());
		
		paging.add(new JButton("�� "));
		paging.add(new JLabel(" Page "));
		paging.add(new JButton("�� "));
		
		botP.add(paging, BorderLayout.CENTER);
		
		return botP;

	}
}