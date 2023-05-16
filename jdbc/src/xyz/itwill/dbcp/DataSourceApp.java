package xyz.itwill.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSourceImpl;

//javax.sql.DataSource (인터페이스) : DBCP 클래스를 작성하기 위해 상속받기 위한 인터페이스
//=> DBCP 클래스의 메소드가 동일한 형식으로 작성되도록하는 규칙을 제공한다.

//UCP(Universal Connection Pool) 라이브러리로 제공되는 DBCP 클래스를 이용한 JDBC 프로그램 작성
//=> https://www.oracle.com 사이트의 페이지에서 UCP 라이브러리 파일(ucp11.jar) 파일을 다운로드받아 프로젝트에 빌드처리.

public class DataSourceApp {
	public static void main(String[] args) throws SQLException {
//		PoolDataSource pds = new PoolDataSource; ==> 만들 수 없음.
//		PoolDataSource pds = new PoolDataSourceImpl();
		
		PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
			//PoolDataSource 객체(BDCP 객체)를 반환받아 저장
			//PoolDataSorceFactory.getPoolDataSource() : PoolDataSource 객체를 생성하여 반환하는
		
		
		
		pds.setConnectionFactoryClassName("oracle.jdbc.driver.OracleDriver");
			//PoolDataSourceFactory.setConnectionFactoryClassName(String driver)
			//=> PoolDataSource 객체의 JDBC Driver 클래스를 변경하는 메소드
		
		pds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			//PoolDataSourceFactory.setURL(String url)
			//=> PoolDataSource 객체의 URL 주소를 변경하는 메소드
		
		pds.setURL("scott");
		//PoolDataSourceFactory.setURL(String user)
		//=> PoolDataSource 객체의 사용자 이름을 변경하는 메소드
		
		pds.setURL("tiger");
		//PoolDataSourceFactory.setURL(String password)
		//=> PoolDataSource 객체의 비밀번호를 변경하는 메소드
		
		//PoolDataSource 객체에 저장될 Connection 객체의 갯수를 제한하기 위한 메소드 호출
		//=> 메소드 호출을 생략할 경우 기본값(보통20개)을 사용하여 Connection 개수를 제한한다.
		pds.setInitialPoolSize(2); //최초 생성되는 Connection 객체의 갯수 변경
		pds.setMaxPoolSize(3);//최대 생성되는 Connection 객체의 갯수 변경
		
		
		//PoolDataSource.getConnection() : PoolDataSource 객체에 저장된 Connection 객체 중 하나를 반환하는 메소드
		Connection con1 = pds.getConnection();
		System.out.println("con1 = "+con1);
			//Connection 객체를 제거하면 자동으로 PoolDataSource 객체로 Connection을 반환한다.
		//con1.close();

		Connection con2=pds.getConnection();
		System.out.println("con2 = "+con2);
		//con2.close();
		
		Connection con3=pds.getConnection();
		System.out.println("con3 = "+con3);
		//con3.close();
		
		Connection con4=pds.getConnection();
		System.out.println("con4 = "+con4);
		con4.close();
		
	}
}
