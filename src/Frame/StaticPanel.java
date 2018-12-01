package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Object;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import DBInfo.GradeDB;
import Main.GradeP;

public class StaticPanel extends TopPanel implements ActionListener {
	private String[] fieldName = new String[15]; // �׸� ���� ���� �迭
	private int fieldNum; // �׸� ����
	private GradePanel gp;
	private GradeDB gdb;

	// �� ���� �� �׸� ���� ����� ����ϴ� ���
	// �л��� ������ ���� ���� ���� �׷����� Ȯ���ϴ� ���
	// �� ���� �� �׸� ���� ���� ���� �׷����� Ȯ���ϴ� ���

	public StaticPanel(GradePanel gp) {
		this.gp = gp;
		this.gdb = gp.getG();
		this.fieldName = gdb.getFieldName();
		this.fieldNum = gdb.getFieldNum();
		this.add(new JButton("��� �г�"));
		this.setLayout(new BorderLayout());

		JPanel top = createTop();
		this.add(top, BorderLayout.NORTH);

		JPanel mid = createChart();
		this.add(mid, BorderLayout.CENTER);

	}

	// ���� ��ư : ���� ��������, �� ���� �� �׸� ��������(��� ����)
	public JPanel createTop() {
		JPanel j = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		for (int i = 4; i < fieldNum; i++) {
			JRadioButton rb = new JRadioButton(fieldName[i] + " ����");
			rb.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 20));
			rb.setPreferredSize(new Dimension(200, 50));
			bg.add(rb);
			j.add(rb, BorderLayout.CENTER);
		}
		return j;
	}

	public JPanel createChart() {
		JPanel j = new JPanel();
		j.setSize(400, 300);
		double[] values = new double[3];
		String[] names = new String[3];
		values[0] = 1;
		names[0] = "Item 1";
		values[1] = 2;
		names[1] = "Item 2";
		values[2] = 4;
		names[2] = "Item 3";
		ChartPanel ch = new ChartPanel(values, names, "title");
		ch.setPreferredSize(new Dimension(500, 500));
		j.add(ch, BorderLayout.CENTER);
		return j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
