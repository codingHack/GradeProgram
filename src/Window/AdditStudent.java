package Window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import DBInfo.StudentDB;
import Main.Student;

//�� â���� �л��� �߰����ִ� Ŭ����
public class AdditStudent extends JFrame implements ActionListener{
	JTextField tnumber, tStuNum, tname, tphoneNum;//����, �л�, �̸�, �޴���
	JTextField tratio;// ����
	JTextArea tremark;//Ư�̻���
	JTextField tYear, tMonth, tDate;//����
	JComboBox cbGrade; //1~4�г� ����
	JRadioButton rbMan, rbWoman;//���� ����
	JButton bSave, bExit;//�л����, ���
	
	Student Pstd;//���� ������ �г�
	
	GridBagLayout glay; //gridbagLayout���, ������Ʈ ��ġ �� ũ�� �������� ����
	GridBagConstraints gbc;//������Ʈ�� ������ �����ϱ� ����
	
	public AdditStudent() {
		Show();
	}
	
	public AdditStudent(Student Pstd) {
		Show();
		//this.Pstd = Pstd;
	}
	
	//������ �޾� ȭ�鿡 �����ַ���..(���߿� ��񿡼� ������)
	private void inputData(Student stu) {
		String number = stu.getNumber();
		String studentNumber = stu.getStudentNumber();
		String name = stu.getName();
		String grade = stu.getGrade();
		String gender = stu.isGender();
		String phoneNumber = stu.getPhoneNumber();
		String birthday = stu.getBirthday();
		String remarks = stu.getRemarks();
		String ratio = stu.getRatio();
		
		tnumber.setText(number);
		tnumber.setEditable(false);//����x
		tStuNum.setText(studentNumber);
		tname.setText(name);
		tphoneNum.setText(phoneNumber);
		tremark.setText(remarks);
		tratio.setText(ratio);
		
		tYear.setText(birthday.substring(0,4));//4���� ��� �б�
		tMonth.setText(birthday.substring(4,6));
		tDate.setText(birthday.substring(6,8));
		
		cbGrade.setSelectedItem(grade);
		
		if(gender.equals("M")) {
			rbMan.setSelected(true);
		}else if(gender.equals("W")) {
			rbWoman.setSelected(true);
		}
		
	}
	
	//���� �������� â �޼ҵ�
	public void Show() {
		this.setTitle("�л� ���");
		glay = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		setLayout(glay);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
	    JLabel st1 = new JLabel("��ȣ : ");
	    st1.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    tnumber = new JTextField(5);
	    gbReset(st1, 0, 0, 1, 1);
	    gbReset(tnumber, 1, 0, 3, 1);
	    
	    JLabel st2 = new JLabel("�й� :");
	    st2.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    tStuNum = new JTextField(20);
	    gbReset(st2, 0, 1, 1, 1);
	    gbReset(tStuNum, 1, 1, 3, 1);//��,�Ʒ�, ����
	    
	    JLabel st3 = new JLabel("�̸� :");
	    st3.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    tname = new JTextField(20);
	    gbReset(st3, 0, 2, 1, 1);
	    gbReset(tname, 1, 2, 3, 1);
	    
	    JLabel ygrade = new JLabel("�г� :");
	    ygrade.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    String[] aygrade = {"1 �г�","2 �г�","3 �г�","4 �г�"};
	    cbGrade = new JComboBox(aygrade);
	    JPanel pgrade = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    pgrade.add(cbGrade);
	    gbReset(ygrade, 0, 3, 1, 1);
	    gbReset(pgrade, 1, 3, 3, 1);
	    
	    JLabel Jgender = new JLabel("���� :");
	    Jgender.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    JPanel Pgender = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    rbMan = new JRadioButton("��", true);
	    rbWoman = new JRadioButton("��", true);
	    ButtonGroup group = new ButtonGroup();
	    group.add(rbMan);
	    group.add(rbWoman);
	    Pgender.add(rbMan);
	    Pgender.add(rbWoman);
	    gbReset(Jgender, 0,4,1,1);
	    gbReset(Pgender, 1,4,3,1);
	    
	    JLabel st4 = new JLabel("����ó :");
	    st4.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    tphoneNum = new JTextField(20);
	    gbReset(st4, 0, 5, 1, 1);
	    gbReset(tphoneNum, 1, 5, 3, 1);
	    
	    JLabel st5 = new JLabel("������� :");
	    st5.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    JPanel Pbirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    tYear = new JTextField(6);
	    tMonth = new JTextField(6);
	    tDate = new JTextField(6);
	    Pbirth.add(tYear);
	    Pbirth.add(new JLabel(" / "));
	    Pbirth.add(tMonth);
	    Pbirth.add(new JLabel(" / "));
	    Pbirth.add(tDate);
	    gbReset(st5, 0, 6, 1, 1);
	    gbReset(Pbirth, 1, 6, 3, 1);
	    
	    JLabel st6 = new JLabel("Ư�̻��� :");
	    st6.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    tremark = new JTextArea(5, 20);
	    JScrollPane scroll = new JScrollPane(tremark);
	    gbReset(st6, 0, 7, 1, 1);
	    gbReset(scroll, 1, 7, 3, 1);
	    
	    JLabel st7 = new JLabel("���� :");
	    st7.setFont(new Font("KBIZ�Ѹ������ M", Font.BOLD, 15));
	    tratio = new JTextField(20);
	    gbReset(st7, 0, 8, 1, 1);
	    gbReset(tratio, 1, 8, 3, 1);
	    
	    JPanel PButton = new JPanel();
	    bSave = new JButton("����"); 
	    bExit = new JButton("����");
	    PButton.add(bSave);
	    PButton.add(bExit);
	    gbReset(PButton, 0, 9, 4, 1);
	    
	    bSave.addActionListener(this);
	    bExit.addActionListener(this);
	    
		setSize(360,430);
	    setVisible(true);
	    setResizable(false);
	    setLocation(600,300);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);//����â�� ����
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b==bSave) {
			System.out.println("â �����ư");
			//insertStudent();
			dispose();
		}else if(b==bExit) {
			int s = JOptionPane.showConfirmDialog(this, "�����Ͻø� �ۼ��Ͻ� ������ ������ϴ�.");
			if(s==JOptionPane.OK_OPTION) {
				dispose(); // ���� ����� ������ ����
			}else {
				JOptionPane.showMessageDialog(this, "���Ḧ ����Ͽ����ϴ�.");
			}
		}
	}
	
	/*private void insertStudent() {
		StudentDB stuDB = new StudentDB();
		Student stuget = getData();
		boolean check = stuDB.insertStudent(stuget);//DB�� �����͸� ����
		System.out.println("����Ǿ����ϴ�");//Ȯ��
	}
	
	public Student getData() {
		Student stu = new Student();
		
		return stu;
	}*/
	
	//������Ʈ ��ġ ����
	private void gbReset(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        glay.setConstraints(c, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }
}
