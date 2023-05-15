package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//STUDENT 테이블에 저장된 학생정보 중 학번이 [3000]인 학생정보를 삭제하는 JDBC 프로그램 작성
public class DeleteStudentApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			
			con=DriverManager.getConnection(url, user, password);
			
			stmt=con.createStatement();
			
			String sql = "Delete from student where no=3000";
			
			int rows = stmt.executeUpdate(sql);
			
			if(rows>0) { //삭제 행이 있을때
				System.out.println("[메세지] "+rows+"명의 학생을 찾을 수 있습니다.");
			} else {  //삭제 행이 없을때
				System.out.println("[메세지] 삭제할 학번의 학생을 찾을 수 없습니다.");				
			}
			
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			
		} finally {
			try {
				if(con!=null) con.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {

			}
		}
	}

}
