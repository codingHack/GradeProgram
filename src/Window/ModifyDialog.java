package Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ModifyDialog extends JFrame implements ActionListener {

	public ModifyDialog() {
		JFrame modifier = new JFrame("��� ����â");
		modifier.setLayout(new FlowLayout());
		modifier.setSize(360, 430);
		modifier.setLocation(600, 300);
		modifier.setResizable(true);	//������ ����
		modifier.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		modifier.setVisible(true);
		
		addPanel(modifier);
	}
	
	public void addPanel(JFrame modifier) {
		// ������ ������������ ��ĥ �ʿ� ��
		// �й�, �̸�, 1~16 (�ϳ��ϳ� ����), �⼮, ����, �Ἦ, ���
		JPanel mainMod = new JPanel();
		
		mainMod.setLayout(new FlowLayout());
		mainMod = addComponent(mainMod);
		
		modifier.add(mainMod);
	}
	
	public JPanel addComponent(JPanel mainMod) {
		
		
		return mainMod;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
