package jdbc_team06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class DBConnection {
	private static PoolDataSource pds;
	
	static {
		pds = PoolDataSourceFactory.getPoolDataSource();
		try {
		pds.setConnectionFactoryClassName("oracle.jdbc.driver.OracleDriver");
		pds.setURL("jdbc:oracle:thin:@www.itwill.xyz:1521:xe");
		pds.setUser("jdbc_team06");
		pds.setPassword("jdbc_team06");
		pds.setInitialPoolSize(10);
		pds.setMaxPoolSize(20);
	} catch(SQLException e) {
		System.out.println("여기");
		e.printStackTrace();
	}
		
	}
	
	
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = pds.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
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
	
	
}
