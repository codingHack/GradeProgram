package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IntroPanel extends TopPanel{
	IntroPanel(){
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5)); // ó�� ���� ����
		setLayout(null);
		
		JLabel labelImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/mainImage.PNG")).getImage();
		labelImage.setIcon(new ImageIcon(img));
		
		labelImage.setBounds(100, 190, 430, 320);//�̹��� ��ġ�� ũ�� ����
		add(labelImage);
		
		JLabel projectName = new JLabel("1. ������Ʈ �� : ");
		projectName.setFont(new Font("a���̾�", Font.BOLD, 35)); // ���̾� ��Ʈ �־�� ���� ^^
		projectName.setBounds(670, 260, 380, 29);
		add(projectName);
		
		JLabel version = new JLabel("2. ���� ���� : ");
		version.setFont(new Font("a���̾�", Font.BOLD, 35));
		version.setBounds(670, 330, 380, 29);
		add(version);
		
		JLabel developer = new JLabel("3. ������ : ");
		developer.setFont(new Font("a���̾�", Font.BOLD, 35));
		developer.setBounds(670, 400, 380, 29);
		add(developer);
		
		JLabel example = new JLabel("���߿� �̻ڰ� �ٹӽô� ..");
		example.setFont(new Font("�������", Font.BOLD, 25));
		example.setBounds(670, 580, 380, 29);
		add(example);
	}
}
