package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Connection 객체를 생성하여 반환하거나 JDBC관련 객체를 매개변수로 전달받아 제거하는 기능을 제공하는 클래스
//=> JDBC 프로그램 작성에 필요한 공통적인 기능을 메소드로 제공한다. (서버접속, 로딩 등 공통된 기능)
//=> 코드의 중복성을 최소화하여 프로그램의 생산성을 높이고 유지보수의 효율성을 증가할 수 있다.
//(추상화와 캡슐화의 원리를 무시하고 효율성을 중시해서 실행한 것.)

public class ConnectionFactory {
	//Connection 객체를 생성하여 반환하는 메소드 생성하기
	public static Connection getConnection() { //getConnection은 객체없이 메소드 호출 가능(클래스로 호출).  Connection 객체를 생성함.  매개변수는 없음.
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			
			con=DriverManager.getConnection(url, user, password); //DBMS 서버에 접속. Connection 객체를 반환.
			
			
		} catch (Exception e) {
			System.out.println("[에러] Connection 객체를 생성할 수 없습니다.");
		}
		return con;
	}
	
	//JDBC관련 객체를 매개변수로 전달받아 제거하는 메소드 
	//똑같은 메소드 안에 매개변수만 다르게 하는 것 >>오버로딩 메소드
	public static void close(Connection con) { 
		//매개변수로 Connection 객체를 받음 >> 매개 변수를 전달 받아 close 시킴
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void close(Connection con, Statement stmt) { 
		//매개변수로 Connection 객체, Statement 받음 >> 매개 변수를 전달 받아 close 시킴
		try {
			if(con!=null) con.close();
			if(stmt!=null) stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		//매개변수로 Connection 객체, Statement , ResultSet 받음 >> 매개 변수를 전달 받아 close 시킴
		try {
			if(con!=null) con.close();
			if(stmt!=null) stmt.close();
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
