package Main;
import Frame.*;

public class GradeP {
	public static void main(String[] args) {
		SuperFrame css = new SuperFrame();
		css.setTitle("성적처리 프로그램 version.1");
		css.setSize(1200, 800);
		css.setLocation(300,100);
		css.setResizable(false);
		css.setDefaultCloseOperation(css.EXIT_ON_CLOSE);
		css.setVisible(true);
	}
}
