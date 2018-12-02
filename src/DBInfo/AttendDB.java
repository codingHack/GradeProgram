package DBInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Main.Attend;

//�⼮ �гΰ� DB�� �����ϱ� ���� Ŭ�����̴�.
public class AttendDB extends StudentDB {
	//student Panel
	//StudentDB's connection ConnectDB
	
	//Attend Table�� Student��ŭ�� ���ڵ带 �־��ش�.
	public AttendDB() {
		super();
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		int col = 0;
		int count = 0;	//Attend table record num
		
		//StudentDB�� number, studentNumber, name�� Attend�� ����
		try {
			con = connectDB.makeConnection();
			String sql_stu = "select number, studentNumber, name from student.student";
			String sql_att = "select number, studentNumber, name from student.attend";
			pstmt = con.prepareStatement(sql_stu);
			pstmt1 = con.prepareStatement(sql_att);
			rs = pstmt.executeQuery();
//			while(pstmt1.executeQuery().next()) {
//				count++;
//			}
//			rs.beforeFirst();
			if(!(rs.equals(pstmt1.executeQuery()))) { //�� DB�� �ٸ��� attend�� table�� ����� �ٽ� �����Ѵ�.
//				while(rs.next()) {
//					for(int i=0; i<count; i++) {
//						if(!rs.getObject(col).equals(pstmt1.executeQuery().getObject(i))) {
//							
//						}
//					}
//					col++;
//				}
				sql_att = "insert into student.attend (number, studentNumber, name) values (?, ?, ?)";
				pstmt1 = con.prepareStatement(sql_att);
				
				while(rs.next()) {
					pstmt1.setString(1, rs.getString("number"));
					pstmt1.setString(2, rs.getString("studentNumber"));
					pstmt1.setString(3, rs.getString("name"));
					pstmt1.execute();
				}
			}
		}catch(SQLException e) {
			System.out.print(1);
			System.out.println("SQLException: "+e.getMessage());
		}
	}
	
	public void searchStudentNumber(DefaultTableModel model, String stuNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = connectDB.makeConnection();
			String sql = "select * from student.attend where studentNumber=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stuNum);
			rs = pstmt.executeQuery();
			
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(0);
			}
			while(rs.next()) {
				 String[] attStu = {
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)
				};
				model.addRow(attStu);
			}
		}catch(SQLException e) {
			System.out.println("SQL : " + e.getMessage());
		}
		
	}

	
}
