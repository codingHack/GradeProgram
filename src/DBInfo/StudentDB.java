package DBInfo;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Frame.StudentPanel;
import Main.Student;

public class StudentDB {
	
	StudentPanel stuP;
	ConnectionDB connectDB;
	
	public StudentDB(){
		connectDB = new ConnectionDB();
	}
	
	StudentDB(StudentPanel stuP){
		this.stuP = stuP;
	}

	//�ѻ�� �й����� �л����� �˻�
	public Student getStudent(String stuNum) {
		Student st = new Student();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select * from student where studentNumber=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, stuNum);//�ϳ��� ������ �˻�
			rs = ps.executeQuery();
			
			if(rs.next()) {
				st.setNumber(rs.getString("number"));
				st.setStudentNumber(rs.getString("studentNumber"));
				st.setName(rs.getString("name"));
				st.setGrade(rs.getString("grade"));
				st.setGender(rs.getString("gender"));
				st.setPhoneNumber(rs.getString("phoneNumber"));
				st.setBirthday(rs.getString("birthday"));
				st.setRemarks(rs.getString("remark"));
				st.setRatio(rs.getString("ratio"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}
	
	//����Ʈ ��� ���
	public Vector getMemberList() { 
		Vector data = new Vector(); //Jtable�� �� �ֱ�����
		Connection con = null; //�����ϱ�����
		PreparedStatement ps = null; //db��� ����
		ResultSet rs = null; //���
		try {
			con = connectDB.makeConnection();
			String sql = "select * from student order by name asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String number = rs.getString("number");
                String studentNumber = rs.getString("studentNumber");
                String name = rs.getString("name");
                String grade = rs.getString("grade");
                String gender = rs.getString("gender");
                String phoneNumber = rs.getString("phoneNumber");
                String birthday = rs.getString("birthday");
                String remarks = rs.getString("remark");
                String ratio = rs.getString("ratio");
               
                Vector row = new Vector();
                row.add(number);
                row.add(studentNumber);
                row.add(name);
                row.add(grade);
                row.add(gender);
                row.add(phoneNumber);
                row.add(birthday);
                row.add(remarks);
                row.add(ratio);
  
                data.add(row); //ū ���Ͱ��� ����
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	//�л� ���
	public boolean insertStudent(Student std) {
		boolean check = false;
		Connection con = null; //�����ϱ�����
		PreparedStatement ps = null; //db��� ����
		
		try {
			con = connectDB.makeConnection();
			String sql = "insert into student(number,studentNumber,name,grade,gender,phoneNumber,birthday,remark,ratio)"+
					"values(?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, std.getNumber());
			ps.setString(2, std.getStudentNumber());
			ps.setString(3, std.getName());
			ps.setString(4, std.getGrade());
			ps.setString(5, std.isGender());
			ps.setString(6, std.getPhoneNumber());
			ps.setString(7, std.getBirthday());
			ps.setString(8, std.getRemarks());
			ps.setString(9, std.getRatio());
			
			int saveStu = ps.executeUpdate(); // ���� => ����
			if(saveStu>0) {
				System.out.println("���� ����");
				check=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//�л� ���� ����
	public boolean updateStudent(Student upstd) {
		//System.out.println("std="+upstd.toString()); // ���� ���� �ֿܼ� ..(Ȯ���ϱ�����)
		boolean check = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connectDB.makeConnection();
			String sql = "update student set number=?, name=?, grade=?, gender=?, phoneNumber=?, birthday=?"+
			", remark=?, ratio=?" + "where studentNumber=?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, upstd.getNumber());
			ps.setString(2, upstd.getName());
			ps.setString(3, upstd.getGrade());
			ps.setString(4, upstd.isGender());
			ps.setString(5, upstd.getPhoneNumber());
			ps.setString(6, upstd.getBirthday());
			ps.setString(7, upstd.getRemarks());
			ps.setString(8, upstd.getRatio());
			ps.setString(9, upstd.getStudentNumber());
			//System.out.println(upstd.getNumber());
			//System.out.println(upstd.getStudentNumber()); //�׽�Ʈ�ڵ�
			
			int saveStu = ps.executeUpdate(); // ���� => ����
			
			if(saveStu>0) {
				check=true;
			}else {
				System.out.println("������Ʈ ���� db");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//��� ������ ����
	public boolean deleteStudent(String studentNumber) {
		boolean check = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = connectDB.makeConnection();
			String sql = "delete from student where studentNumber=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			int saveStu = ps.executeUpdate();
			if(saveStu>0) {
				check = true;
				System.out.println("��� ���� ����");
			}else {
				System.out.println("��� ���� ����");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return check;
	}
	
	//db ������ �ٽ� �θ���(�̸� ����)
	public void studentCheck(DefaultTableModel model) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select *from student order by name asc";
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			//tablemodel�� �ִ� ������ �����
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(0);
			}
			
			while(rs.next()) {
				Object data [] = {
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9)
				};
				model.addRow(data);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}finally{
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            }
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if(con!=null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
