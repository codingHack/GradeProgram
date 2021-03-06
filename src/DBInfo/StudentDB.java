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
		// return connected DB to 'connectDB'
		connectDB = new ConnectionDB();
	}
	
	StudentDB(StudentPanel stuP){
		this.stuP = stuP;
	}

	//한사람 학번으로 학생정보 검색해서 가져온다.
	public Student getStudent(String stuNum) {
		Student st = new Student();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select * from student where studentNumber=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, stuNum);//하나의 쿼리를 검색
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
	
	//리스트 목록 출력
	public Vector getMemberList() { 
		Vector data = new Vector(); //Jtable에 값 넣기위해
		Connection con = null; //연결하기위해
		PreparedStatement ps = null; //db명령 넣음
		ResultSet rs = null; //출력
		try {
			con = connectDB.makeConnection();
			String sql = "select * from student order by number asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int a=1;
			
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
                row.add(a++);
                row.add(studentNumber);
                row.add(name);
                row.add(grade);
                row.add(gender);
                row.add(phoneNumber);
                row.add(birthday);
                row.add(remarks);
                row.add(ratio);
  
                data.add(row); //큰 벡터값에 넣음
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	//학생 등록
	public boolean insertStudent(Student std) {
		boolean check = false;
		Connection con = null; //연결하기위해
		PreparedStatement ps = null; //db명령 넣음
		
		try {
			con = connectDB.makeConnection();
			String sql = "insert into student(number,studentNumber,name,grade,gender,phoneNumber,birthday,remark)"+
					"values(?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, std.getNumber());
			ps.setString(2, std.getStudentNumber());
			ps.setString(3, std.getName());
			ps.setString(4, std.getGrade());
			ps.setString(5, std.isGender());
			ps.setString(6, std.getPhoneNumber());
			ps.setString(7, std.getBirthday());
			ps.setString(8, std.getRemarks());
		
			
			int saveStu = ps.executeUpdate(); // 실행 => 저장
			
			sql="insert into grade(학번,이름,성적) values("+std.getStudentNumber()+",'"+ std.getName()+"','"+std.getRatio()+"')";
	        System.out.println(sql);
	        ps = con.prepareStatement(sql);
	        ps.executeUpdate();
	        
			if(saveStu>0) {
				System.out.println("가입 성공");
				check=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//학생 정보 수정
	public boolean updateStudent(Student upstd) {
		//System.out.println("std="+upstd.toString()); // 현재 정보 콘솔에 ..(확인하기위해)
		boolean check = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connectDB.makeConnection();
			String sql = "update student set name=?, grade=?, gender=?, phoneNumber=?, birthday=?"+
			", remark=?" + "where studentNumber=?";
			ps = con.prepareStatement(sql);
			
			
			ps.setString(1, upstd.getName());
			ps.setString(2, upstd.getGrade());
			ps.setString(3, upstd.isGender());
			ps.setString(4, upstd.getPhoneNumber());
			ps.setString(5, upstd.getBirthday());
			ps.setString(6, upstd.getRemarks());
			ps.setString(7, upstd.getStudentNumber());
			
			int saveStu = ps.executeUpdate(); // 실행 => 저장
			
			sql = "update grade set 이름=? WHERE 학번=?";// 학번 변경X
			//sql = "update grade set 학번=" +upstd.getStudentNumber()+" , 이름='" +upstd.getName() + "',성적="+ upstd.getRatio();
			System.out.println(sql);
	        ps = con.prepareStatement(sql);
	        
			ps.setString(1, upstd.getName());
			ps.setString(2, upstd.getStudentNumber());
	        ps.executeUpdate();
	        
			if(saveStu>0) {
				check=true;
			}else {
				System.out.println("업데이트 실패 db");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//디비 데이터 삭제
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
			
			//
			String sqlatt = "delete from attend where studentNumber=?";
			System.out.println(check);
			ps = con.prepareStatement(sqlatt);
			ps.setString(1, studentNumber);
			ps.executeUpdate();
			//
			sql="delete from grade where 학번="+studentNumber;
	        System.out.println(sql);
	        ps = con.prepareStatement(sql);
	        ps.executeUpdate();
			if(saveStu>0) {
				check = true;
				System.out.println("디비 삭제 성공");
			}else {
				System.out.println("디비 삭제 실패");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return check;
	}
	
	//db 데이터 다시 부르기(이름 정렬)
	public void studentCheck(DefaultTableModel model) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select *from student order by name asc";
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			//tablemodel에 있는 데이터 지우기
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(0);
			}
			int a=1;
			while(rs.next()) {
				Object data [] = {
					a,
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9)
				};
				a++;
				model.addRow(data);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
    }
	//학번 정렬
	public void stuNumCheck(DefaultTableModel model) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select *from student order by studentNumber asc";
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			int a=1;
			//tablemodel에 있는 데이터 지우기
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(0);
			}
			
			while(rs.next()) {
				Object data [] = {
					a,
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9)
				};
				a++;
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
	
	//리셋(번호정렬)
	public void numberCheck(DefaultTableModel model) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select *from student order by number asc";
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			//tablemodel에 있는 데이터 지우기
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(0);
			}
			int a=1;
			while(rs.next()) {
				Object data [] = {
					a,
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9)
				};
				a++;
				model.addRow(data);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
    }
	
	//학번 검색
	public void stuNumberSearch(DefaultTableModel model, String stuNum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select *from student where studentNumber=?";
			ps = con.prepareStatement(sql); 
			ps.setString(1, stuNum);//하나의 쿼리를 검색
			rs = ps.executeQuery();
			
			//tablemodel에 있는 데이터 지우기
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
		}
    }
	//이름검색
	public void nameSearch(DefaultTableModel model, String name) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectDB.makeConnection();
			String sql = "select *from student where name=?";
			ps = con.prepareStatement(sql); 
			ps.setString(1, name);//하나의 쿼리를 검색
			rs = ps.executeQuery();
			
			//tablemodel에 있는 데이터 지우기
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
		}
    }
	// 2018-11-30 updated : make connectDB Getter
	public ConnectionDB getConnection() {
		return connectDB;
	}
}
