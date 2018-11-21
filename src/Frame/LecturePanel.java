package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import Main.GradeRatio;
import Main.Lecture;

public class LecturePanel extends TopPanel {
	private Lecture myLecture;
	private GradeRatio myRatio;
	
	public LecturePanel() {
		this.myRatio = new GradeRatio(15,15,20,20,10,10,0,0,0);
		this.myLecture = new Lecture("��� ��ü ���� ���α׷���",1333,3,"����","������","Y5437",40,"Java J2SE�� ������� �ϴ� ��ü���� �̷�\r\n" + 
				"��ü���� ���� ��Ģ �� ������ ����\r\n" + 
				"Swing �� JDBC�� �̿��� ��ü���� ����");
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel infoP = creatInfoPanel();    //1.���� ���� �г�
		JPanel introP = creatIntroPanel();  //2.���� �Ұ� �г�	
		JPanel ratioP = creatRatioPanel();  //3.���� �г�	

		this.add(infoP);
		this.add(introP);
		this.add(ratioP);
		
	}
	
	public JPanel creatInfoPanel() { //1.���� ���� �г�
		JPanel jp=new JPanel();
		String str[]= {"���� ","����","����","��米��","���ǽ�","���¹�ȣ","�л���"};
		Object obj[]= {myLecture.getName(),myLecture.getScore(),myLecture.getDivision(),
				myLecture.getProfessor(),myLecture.getRoomNumber(),myLecture.getLectureNumber(),myLecture.getCount()};
		
		for(int i=0;i<7;i++) {			
			JLabel j = new JLabel(str[i]+"- "+obj[i]);
			j.setFont(new Font("a���̾�", Font.BOLD, 35));
			j.setBounds(100, 50*i+ 150 , 500, 50);
			add(j);
		}	
		
		return jp;
	}

	public JPanel creatIntroPanel() {  //2.���� �Ұ� �г�
		JPanel jp=new JPanel();
		JLabel j = new JLabel("���� �Ұ�");
		j.setFont(new Font("a���̾�", Font.BOLD, 45));
		j.setBounds(500, 50, 500, 50);
		add(j);
		
		JTextArea i = new JTextArea(myLecture.getIntroduce());
		i.setEditable(false);
		i.setFont(new Font("a���̾�", Font.BOLD, 30));
		i.setBounds(560, 230, 550, 200);
		add(i);
			
		return jp;
	}
	
	public JPanel creatRatioPanel() { //3.���� �г�
		JPanel jp=new JPanel();	
		
		JLabel j = new JLabel("���� ����");
		j.setFont(new Font("a���̾�", Font.BOLD, 40));
		j.setBounds(500, 470, 500, 100);
		add(j);
		
		String col[]= {"A+","A","B+","B","C+","C","D+","D","F"};
		Object[][] row= {{myRatio.getaPlusRatio(),myRatio.getaRatio(),myRatio.getbPlusRatio(),myRatio.getbRatio(),
			myRatio.getcPlusRatio(),myRatio.getcRatio(),myRatio.getdPlusRatio(),myRatio.getdRatio(),myRatio.getfRatio()}};
		JTable t=new JTable(row,col);
		JScrollPane jsp = new JScrollPane(t);
		jsp.setBounds(290, 600, 500, 42);
		add(jsp);
		
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myRatio.setaPlusRatio(t.getValueAt(0, 0));
				myRatio.setaRatio(t.getValueAt(0, 1));
				myRatio.setbPlusRatio(t.getValueAt(0, 2));
				myRatio.setbRatio(t.getValueAt(0, 3));
				myRatio.setcPlusRatio(t.getValueAt(0, 4));
				myRatio.setcRatio(t.getValueAt(0, 5));
				myRatio.setdPlusRatio(t.getValueAt(0, 6));
				myRatio.setdRatio(t.getValueAt(0, 7));
				myRatio.setfRatio(t.getValueAt(0, 8));
			}
		};
		
		JButton b=new JButton("����");
		b.setBounds(789, 600, 70, 42);
		b.addActionListener(l);
		add(b);
		
		
		
		return jp;
	}
	
	
}
