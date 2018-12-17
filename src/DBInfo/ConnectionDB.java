package DBInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static final String DRIVER
	= "com.mysql.cj.jdbc.Driver";
	private static final String url 
	= "jdbc:mysql://localhost/student?characterEncoding=UTF-8&serverTimezone=UTC";
	
	// ��� ���� METHOD
	public Connection makeConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			System.out.println("�����ͺ��̽� ������..");

			con = DriverManager.getConnection(url, "root", "Dlrudals95`"); // ���� ���� ���̵�� ..

			//System.out.println("�����ͺ��̽� ���� ����");
		}catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(SQLException ex) {
			System.out.println("SQLException:"+ex.getMessage());
		}
		return con;
	}
}
