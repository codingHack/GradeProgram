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
	Connection con = null;

	public GradeDB() {
		connectDB = new ConnectionDB();
		con = connectDB.makeConnection();
	}

	GradeDB(GradePanel gp) {
		this.gp = gp;
	}

	/*
	 * studentList[countStudent-1]=new Student();
	 * System.out.println(rs.getString("�й�"));
	 * System.out.println(rs.getString("�̸�")); System.out.println(sum);
	 * studentList[countStudent-1].setStudentNumber(rs.getString("�й�"));
	 * studentList[countStudent-1].setName(rs.getString("�̸�"));
	 * studentList[countStudent-1].setSum(sum);
	 */

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
				row.add(countStudent);
				for (int i = 1; i < fieldNum; i++) {
					if (i == 3) {
						row.add("F");
						continue;
					}
					row.add(rs.getString(fieldName[i]));
				}
				double sum = accumulateSum(row);
				row.add(sum);
				data.add(row);
				countStudent++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	};

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
			for (int i : arr) {
				System.out.println(i);
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

		return Double.parseDouble(String.format("%.2f", sum));
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
