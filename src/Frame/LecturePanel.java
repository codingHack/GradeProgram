package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Main.Lecture;

public class LecturePanel extends TopPanel {
	private Lecture myLecture;
	private JPanel ratio;
	public LecturePanel() {
		myLecture = new Lecture("��� ��ü ���� ���α׷���",1333,3,"����","������","Y5437",40,"���ǼҰ�");
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
		String str[]= {"���� �̸� - ","���� - ","���� - ","��米�� - ","���ǽ� - ","���¹�ȣ - ","�л��� - "};
		Object obj[]= {myLecture.getName(),myLecture.getScore(),myLecture.getDivision(),
				myLecture.getProfessor(),myLecture.getRoomNumber(),myLecture.getLectureNumber(),myLecture.getCount()};
		
		for(int i=0;i<7;i++) {			
			JLabel j = new JLabel(str[i]+ obj[i]);
			j.setFont(new Font("a���̾�", Font.BOLD, 35));
			j.setBounds(100, 50*i+ 100 , 500, 50);
			add(j);
		}
		
		return jp;
	}

	public JPanel creatIntroPanel() {  //2.���� �Ұ� �г�
		JPanel jp=new JPanel();
		
		JLabel j = new JLabel("���� �Ұ�");
		j.setFont(new Font("a���̾�", Font.BOLD, 35));
		j.setBounds(100, 50, 500, 50);
		
		return jp;
	}
	
	public JPanel creatRatioPanel() { //3.���� �г�
		JPanel jp=new JPanel();
		jp.add(new JButton("2"));
		return jp;
	}
	
}
