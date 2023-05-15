package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Connection 객체를 생성하여 반환하거나 JDBC관련 객체를 매개변수로 전달받아 제거하는 기능을 제공하는 클래스
//=> JDBC 프로그램 작성에 필요한 공통적인 기능을 메소드로 제공한다. 
//=> 코드의 중복성을 최소화하여 프로그램의 생산성을 높이고 유지보수의 효율성을 증가할 수 있다.
//(추상화와 캡슐화의 원리를 무시하고 효율성을 중시해서 실행한 것.)

public class ConnectionFactory {
	//Connection 객체를 생성하여 반환하는 메소드 생성하기
	public static Connection getConnection() { //getConnection은 객체없이 메소드 호출 가능.  Connection 객체를 생성함.  매개변수는 없음.
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driber.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			
			con=DriverManager.getConnection(url, user, password);
			
			
		} catch (Exception e) {
			System.out.println("[에러] Connection 객체를 생성할 수 없습니다.");
		}
		return con;
	}
	
	//JDBC관련 객체를 매개변수로 전달받아 제거하는 메소드
	public static void close(Connection con) {
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
			
		}
	}
	
}
