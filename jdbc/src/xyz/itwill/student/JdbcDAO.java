package xyz.itwill.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

//모든 JDBC 기능의 DAO 클래스가 상속받아 사용하기 위한 부모클래스
//=> DBCP(DataBaseConnectionPool) 객체를 생성하여 미리 Connection 객체를 생성하여 저장하고, DBCP 객체로부터
//Connection 객체를 반환하거나 JDBC 관련 객체를 매개변수로 전달받아 제거하는 
//=> 객체 생성이 목적이 아닌 "상속을 목적"으로 작성된 클래스이므로, 추상클래스(명령없는 클래스)로 선언하는 것을 권장한다.

public abstract class JdbcDAO {
	//PoolDataSource 객체(DBCP 객체)를 저장하기 위한 필드
	private static PoolDataSource pds;
	
	static {
		//PoolDataSource 객체를 반환받아 필드에 저장
		pds = PoolDataSourceFactory.getPoolDataSource();
		
		try {
			//PoolDataSource 객체에 Connection 객체를 미리 생성하여 저장
			pds.setConnectionFactoryClassName("oracle.jdbc.driver.OracleDriver");
			pds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			pds.setUser("scott");
			pds.setPassword("tiger");
			pds.setInitialPoolSize(10);
			pds.setMaxPoolSize(20);
		} catch (SQLException e) {
		}
	}
	
	//PoolDataSource 객체(DBCP 객체)에 저장된 Connection 객체 중 하나를 반환하는 메소드
	public Connection getConnection() {
		Connection con = null;
		try {
			con=pds.getConnection();
		} catch (SQLException e) {
		}
		return con;
	}
	
	//매개변수로 JDBC 관련 객체를 전달받아 제거하는 메소드 (위에서 빌려 쓴 메소드를 돌려주는 것)
	public void close(Connection con) {
		try {
			//Connection 객체 제거 : PoolDataSource 객체에게 다시 Connection 객체를 되돌려주는 것
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
			
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<StudentDTO> selectNameStudentList(String name) {
		return null;
	}
}
