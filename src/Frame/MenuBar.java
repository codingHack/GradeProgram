package Frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	public MenuBar() {
		this.setSize(1200,200);
		JMenu fileMenu = new JMenu("����");
		fileMenu.add(new JMenuItem("����"));
		fileMenu.add(new JMenuItem("����"));
		fileMenu.add(new JMenuItem("����"));
		
		JMenu gradeMenu = new JMenu("����");
		JMenu attendMenu = new JMenu("���");
		JMenu staticMenu = new JMenu("���");
		JMenu studentMenu = new JMenu("�л�");
		JMenu lectureMenu = new JMenu("����");
		
		
		
		this.add(fileMenu);
		this.add(gradeMenu);	
		this.add(attendMenu);	
		this.add(staticMenu);	
		this.add(studentMenu);	
		this.add(lectureMenu);	
		setVisible(true);
	}
}
