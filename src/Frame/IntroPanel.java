package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IntroPanel extends TopPanel{
	IntroPanel(){
		setBackground(Color.BLACK);
		//setBorder(new EmptyBorder(5, 5, 5, 5)); // ó�� ���� ����
		setLayout(null);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel labelImage = new JLabel(""); //�̹��� ��ġ�� ������ �� ����
		Image img = new ImageIcon(this.getClass().getResource("/mainImage.PNG")).getImage();
		labelImage.setIcon(new ImageIcon(img));
		
		labelImage.setBounds(100, 190, 430, 320);//�̹��� ��ġ�� ũ�� ����
		add(labelImage);
		
		JLabel projectName = new JLabel("1. ������Ʈ �� : lalavla");
		projectName.setFont(new Font("a���̾�", Font.BOLD, 35)); // ���̾� ��Ʈ �־�� ���� ^^
		projectName.setForeground(Color.WHITE);
		projectName.setBounds(670, 260, 380, 29);
		add(projectName);
		
		JLabel version = new JLabel("2. ���� ���� : ");
		version.setFont(new Font("a���̾�", Font.BOLD, 35));
		version.setForeground(Color.WHITE);
		version.setBounds(670, 330, 380, 29);
		add(version);
		
		JLabel developer = new JLabel("3. ������ : �μ�");
		developer.setFont(new Font("a���̾�", Font.BOLD, 35));
		developer.setForeground(Color.WHITE);
		developer.setBounds(670, 400, 380, 29);
		add(developer);
		
		JLabel example = new JLabel("# ��� ��ü���� ���α׷��� #");
		example.setFont(new Font("a���̾�", Font.BOLD, 25));
		example.setForeground(Color.WHITE);
		example.setBounds(470, 650, 380, 29);
		add(example);

	}
	//��漳��
	Image bg = new ImageIcon("Image/background.jpg").getImage();
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, null);
	}

}
