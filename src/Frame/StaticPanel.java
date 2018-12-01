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
		JRadioButton rbSum = new JRadioButton("���� ���� ����");
		rbSum.setFont(new Font("a���̾�", Font.BOLD, 25));
		rbSum.setPreferredSize(new Dimension(200, 50));
		bg.add(rbSum);
		j.add(rbSum, BorderLayout.CENTER);
		for (int i = 4; i < fieldNum; i++) {
			JRadioButton rb = new JRadioButton(fieldName[i] + " ����");
			rb.setFont(new Font("a���̾�", Font.BOLD, 25));
			rb.setPreferredSize(new Dimension(200, 50));
			bg.add(rb);
			j.add(rb, BorderLayout.CENTER);
		}
		return j;
	}
	
	//��Ʈ �׸���
	public JPanel createChart() {
		JPanel j = new JPanel();
		double[] values = gdb.countNumberBySum();
		String[] names = {"0~10("+(int)values[0]+"��)","10~20("+(int)values[1]+"��)","20~30("+(int)values[2]+"��)",
				"30~40("+(int)values[3]+"��)","40~50("+(int)values[4]+"��)","50~60("+(int)values[5]+"��)",
				"60~70("+(int)values[6]+"��)","70~80("+(int)values[7]+"��)","80~90("+(int)values[8]+"��)","90~100("+(int)values[9]+"��)"};
		ChartPanel ch = new ChartPanel(values, names, "���� ���� �׷���");
		ch.setPreferredSize(new Dimension(1100, 500));
		j.add(ch, BorderLayout.CENTER);
		return j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
