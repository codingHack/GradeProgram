package DBInfo;

import Frame.StudentPanel;
import Main.GradeRatio;
import Main.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;

import com.sun.glass.ui.Window;

import Frame.GradePanel;
import Frame.LecturePanel;

public class GradeDB {

	private ConnectionDB connectDB;
	private GradePanel gp;
	private String[] fieldName = new String[15];
	private int[] itemRatio;
	private int gradeRate[];
	private int countStudent = 1;
	private GradeRatio gr;
	private int fieldNum;
	private double averageItem;
	Connection con = null;
	Vector savedData;

	public GradeDB() {
		connectDB = new ConnectionDB();
		con = connectDB.makeConnection();
	}

	GradeDB(GradePanel gp) {
		this.gp = gp;
	}

	// �л����� ����Ʈ�� ����
	public Vector getMemberList() {
		Vector data = new Vector();
		try {
			gradeRate = getGradeRate();
			getItemList();// �׸���� �̸��� ������
			getItemRatio();// �׸���� ������ ������
			PreparedStatement ps = con.prepareStatement("select * from grade");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector row = new Vector();
				row.add(countStudent); // ����
				for (int i = 1; i < fieldNum; i++) { // �̸�, �й�, �׸� �߰�
					if (i == 3) {// ������ �켱 ���Ƿ� ����
						row.add("F");//
						continue;
					}
					row.add(rs.getString(fieldName[i]));
				}
				row.add(accumulateSum(row));
				countStudent++; // �л��� ����
				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Vector sortedVector = sortStudentBySum(data); // ���͸� �������� ����
		addGrade(sortedVector); // ���Ϳ� ���� �ο�
		savedData=sortedVector;
		return sortedVector;
	};

	//�̸�,�й�,�������� �л� �˻�
	public Vector searchStudentByNameOrNumber(String name,int num) {
		Vector s= new Vector();
		for(int i=0;i<savedData.size();i++) {
			Vector t = (Vector)(savedData.get(i));
			if(t.get(num+1).equals(name)) {
				s.add(t);
			}
		}
		return s;
	}

	// ������ �ο�
	public void addGrade(Vector data) {
		Vector addGrade = new Vector();
		double studentCount = data.size(); // �л���
		double gradeRatio[] = new double[9];
		for (int i = 0; i < 9; i++) {
			if (i == 0) {
				gradeRatio[i] = studentCount / 100 * gradeRate[i];
			} else {
				gradeRatio[i] = (studentCount / 100 * gradeRate[i] + gradeRatio[i - 1]);
			}
		}
		String str[] = { "A+", "A", "B+", "B", "C+", "C", "D+", "D", "F" };
		int number = 0;
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < 9; j++) {
				if (i < gradeRatio[j]) {
					for (; i < gradeRatio[j];) {
						Vector v = (Vector) data.get(i);
						v.add(3, str[j]);
						v.remove(4);
						try {
							PreparedStatement ps = con.prepareStatement(
									"UPDATE student SET ratio='" + str[j] + "' WHERE studentNumber='" + v.get(1) + "'");
							ps.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						i++;
					}
				}
			}
		}
	}
	
	//�հ� �з����ִ� �Լ�
	public double[] countNumberByItem(int number) {
		double arr[]=new double[10];
		double sum=0;
		double num;
		for(int i=0;i<savedData.size();i++) {
			Vector v =(Vector)(savedData.get(i));
			if(number==0) {
				num=Double.parseDouble(""+v.lastElement());
			}else {
				num=Double.parseDouble(""+v.get(number+3));
			}
			sum+=num;
			if(num>=0&&num<=10) {
				arr[0]++;
			}else if(num>=11&&num<=20) {
				arr[1]++;				
			}else if(num>=21&&num<=30) {
				arr[2]++;
			}else if(num>=31&&num<=40) {
				arr[3]++;
			}else if(num>=41&&num<=50) {
				arr[4]++;
			}else if(num>=51&&num<=60) {
				arr[5]++;
			}else if(num>=61&&num<=70) {
				arr[6]++;
			}else if(num>=71&&num<=80) {
				arr[7]++;
			}else if(num>=81&&num<=90) {
				arr[8]++;
			}else if(num>91&&num<=100) {
				arr[9]++;
			}
		}
		for(double d :arr) {
			//System.out.print(d+" ");
		}
		averageItem=sum/savedData.size();
		//System.out.println(averageItem);
		return arr;
	}
	
	public double getAverageItem() {
		return averageItem;
	}

	// ���͵��� sum ������ ���Ľ�Ű�� �Լ�
	public Vector sortStudentBySum(Vector data) {
		Vector sortedVector = new Vector();
		int a = 1;
		int size = data.size();
		for (int j = 0; j < size; j++) {
			double max = 0;
			int maxIndex = 0;
			int rsize = data.size();
			for (int i = 0; i < rsize; i++) {
				Vector s = (Vector) data.get(i);
				String str = "" + s.lastElement();
				if (Double.parseDouble(str) > max) {
					max = Double.parseDouble(str);
					maxIndex = i;
				}
			}
			Vector maxVector = (Vector) data.get(maxIndex);
			maxVector.remove(0);
			maxVector.add(0, a);
			sortedVector.add(maxVector);
			data.remove(maxIndex);
			a++;
		}
		return sortedVector;
	}


	// �׸���� ������ �������� �Լ�
	public void getItemRatio() {
		try {
			itemRatio = new int[fieldNum - 4];
			PreparedStatement ps = con.prepareStatement("select * from graderatio where idgradeRatio=1");
			ResultSet rs = ps.executeQuery();
			rs.next();
			for (int i = 4; i < fieldNum; i++) {
				itemRatio[i - 4] = rs.getInt(fieldName[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// � �׸���� �߰��Ǿ��ִ��� �����ͼ� ������ �־��ִ� ����
	public void getItemList() {
		try {
			fieldNum = 0;
			PreparedStatement ps = con.prepareStatement("show full columns from grade");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fieldName[fieldNum++] = rs.getString("Field");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//lecture���� �Է��� ��������
	public boolean setGradeRate(GradeRatio gr) {
		PreparedStatement ps = null;
		ResultSet rs = null; // ���
		String sql = "UPDATE graderate SET AP="+gr.getaPlusRatio()+",A="+gr.getaRatio()+",BP="+gr.getbPlusRatio()
			+",B="+gr.getbRatio()+",CP="+gr.getcPlusRatio()+",C="+gr.getcRatio()+",DP="+gr.getdPlusRatio()+",D="+gr.getdRatio()+",F="+gr.getfRatio()+" WHERE idgraderatio2=1";		
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	public int getCountStudent() {
		return countStudent;
	}

	// ���� ���� �������� �Լ�
	public int[] getGradeRate() {
		int[] arr = new int[10];
		PreparedStatement ps = null;
		ResultSet rs = null; // ���
		String sql = "SELECT AP,A,BP,B,CP,C,DP,D,F FROM student.graderate;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			for (int i = 1; i < 10; i++) {
				arr[i - 1] = rs.getInt(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arr;
	}

	// ������ ������ִ� �Լ�
	public double accumulateSum(Vector v) {
		double sum = 0;

		for (int i = 4; i < fieldNum; i++) {
			sum += (Integer.parseInt((String) v.get(i)) * itemRatio[i - 4]) / 100;
		}
		return sum;
		//return Double.parseDouble(String.format("%.2f", sum));
	}

	// �׸���� ������ �������ִ� �Լ�
	public boolean setRatio(JTextField[] jf) {
		String query = "";

		for (int i = 0; i < fieldNum - 4; i++) {
			query += fieldName[i + 4] + "=" + jf[i].getText() + ", ";
		}
		query = query.substring(0, query.length() - 2);

		String sql = "UPDATE graderatio SET " + query + " WHERE idgradeRatio=1;";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// �����Է¿��� ������ ������ �� �������ִ� �Լ�
	public boolean setScore(int[] arr, String studentNumber) {
		String query = "";
		for (int i = 4; i < fieldNum; i++) {
			query += fieldName[i];
			query += "=" + arr[i - 4] + ", ";
		}
		query = query.substring(0, query.length() - 2);

		String sql = "UPDATE grade SET " + query + " WHERE �й�=" + studentNumber;

		try {
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// �����Է¿��� ���Է½� ������ �Ѱ��ִ� �Լ�
	public int[] getScore(String studentNumber) {
		int arr[] = new int[fieldNum];
		String sql = "SELECT * FROM grade WHERE �й�=" + studentNumber;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			for (int i = 4; i < fieldNum; i++) {
				arr[i - 4] = Integer.parseInt(rs.getString(fieldName[i]));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	// �׸��� �߰��ϴ� �Լ�
	public void addColumn(String newCol) {
		String sql = "ALTER TABLE `student`.`grade` ADD COLUMN " + newCol + " INT NOT NULL DEFAULT 0";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql = "ALTER TABLE `student`.`graderatio` ADD COLUMN " + newCol + " INT NOT NULL DEFAULT 0";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �׸��� �����ϴ� �Լ�
	public void removeColumn(String removeCol) {
		String sql = "ALTER TABLE `student`.`grade` DROP " + removeCol;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();

			sql = "ALTER TABLE `student`.`graderatio` DROP " + removeCol;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] getRatio() {
		return itemRatio;
	}

	public int[] getGradeRatio() {
		return itemRatio;
	}

	public String[] getFieldName() {
		return fieldName;
	}

	public void setFieldName(String[] fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
	}

}
