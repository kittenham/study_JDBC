package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//EMP 테이블에 저장된 모든 사원정보의 사원번호, 사원이름, 급여를 급여로 내림차순 정렬되도록 검색하여 출력하는 
//JDBC 프로그램 작성
public class SelectEmpApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("orcle.jdbc.driver.OrcleDriver");
			
			String url = "jdbc:oracle:thin:@localhost.1521.xe";
			String user = "scott";
			String password = "tiger";
			con=DriverManager.getConnection(url, user, password);
			
			stmt=con.createStatement();
			
			String sql = "select empno, ename, sal from emp order by sal desc";
			
			rs=stmt.executeQuery(sql);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
		} 
	}
	
	
}
