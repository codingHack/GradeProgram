package Frame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Main.GradeP;

public class SuperFrame extends JFrame{	
	private AttendPanel AttendP;
	private GradePanel GradeP;
	private StudentPanel StudentP;
	private LecturePanel LectureP;
	private StaticPanel StaticP;
	private IntroPanel IntroP;
	
	public SuperFrame() {		
		IntroP = new IntroPanel();
		AttendP = new AttendPanel();
		GradeP = new GradePanel();
		StudentP = new StudentPanel();
		LectureP = new LecturePanel();
		StaticP = new StaticPanel();
			
		getContentPane().add(IntroP);
		this.setJMenuBar(new MenuBar(this));
		
		//this.add(AttendP, BorderLayout.CENTER);
		//this.add(GradeP, BorderLayout.CENTER);
		//this.add(LectureP, BorderLayout.CENTER);
		//this.setVisible(true);
		//this.add(StaticP, BorderLayout.CENTER);
		
<<<<<<< HEAD
	}
	public void change(String panelName) {
		if(panelName.equals("gradeP")) {
			getContentPane().removeAll();
			getContentPane().add(GradeP);
			revalidate();
			repaint();
		}else if(panelName.equals("attendP")){
			getContentPane().removeAll();
			getContentPane().add(AttendP);
			revalidate();
			repaint();
		}else if(panelName.equals("staticP")){
			getContentPane().removeAll();
			getContentPane().add(StaticP);
			revalidate();
			repaint();
		}else if(panelName.equals("studentP")){
			getContentPane().removeAll();
			getContentPane().add(StudentP);
			revalidate();
			repaint();
		}else if(panelName.equals("lectureP")){
			getContentPane().removeAll();
			getContentPane().add(LectureP);
			revalidate();
			repaint();
		}
	}
}
=======
	}
	public void change(String panelName) {
		if(panelName.equals("gradeP")) {
			getContentPane().removeAll();
			getContentPane().add(GradeP);
			revalidate();
			repaint();
		}else if(panelName.equals("attendP")){
			getContentPane().removeAll();
			getContentPane().add(AttendP);
			revalidate();
			repaint();
		}else if(panelName.equals("staticP")){
			getContentPane().removeAll();
			getContentPane().add(StaticP);
			revalidate();
			repaint();
		}else if(panelName.equals("studentP")){
			getContentPane().removeAll();
			getContentPane().add(StudentP);
			revalidate();
			repaint();
		}else if(panelName.equals("lectureP")){
			getContentPane().removeAll();
			getContentPane().add(LectureP);
			revalidate();
			repaint();
		}
	}
}
>>>>>>> 1aabe641c36cca9f1af54786fef73c57b025f7f4
