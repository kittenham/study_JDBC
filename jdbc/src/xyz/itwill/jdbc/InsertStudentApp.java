package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


//JDBC(Java DataBase Connectivity) : Java를 이용하여 DBMS 서버에 접속해 SQL 명령을 전달하여 실행하기 위한 기능을 
//제공하는 Java API(클래스 또는 인터페이스)

//java.sql : JDBC 기능의 프로그램을 작성하기 위한 클래스 및 인터페이스가 선언된 패키지
//=> java.sql 패키지에서는 JDBC 기능 구현을 위해 인터페이스를 제공한다. - DBMS 종료가 다양하므로 클래스로 제공이 불가능하기 때문.
//=> 따라서, DBMS 프로그램을 관리하는 그룹(회사)에서 JDBC 기능을 구현하기 위한 클래스(JDBC Driver)를 배포하여 제공한다.
//=> JDBC Driver가 포함된 라이브러리 파일(Jar 파일)을 다운로드받아 프로젝트에 빌드처리 해야만 라이브러리 클래스를 사용하여
//JDBC 프로그램 작성이 가능하다.

//따라서, 우리는 Oracle DBMS를 사용하기 위한 Oracle JDBC Driver를 다운.

//Oracle DBMS를 이용한 JDBC 프로그램을 사용하기 위한 환경설정
//1. https://www.oracle.com 사이트에서 Oracle JDBC Driver 관련 라이브러리 파일을 다운로드
//   => Oracle JDBC Driver : ojdbc11.jar (JDK11 버전 이상에서부터 사용가능한 버전- Oracle버전이 아닌 JKD 버전으로 참고해야함.)
//2. Oracle JDBC Driver 관련 라이브러리 파일(ojdbc11.jar)을 프로젝트 폴더에 붙여넣기
//3. 프로젝트 폴더에 저장된 라이브러리 파일을 프로젝트에서 사용할 수 있도록 연결 (= 빌드(Build) 처리)
//	 => 라이브러리 파일(Jar)에 클래스 및 인터페이스를 프로젝트에서 사용가능하도록 설정
//	 => 순서 : 프로젝트 > 마우스 오르쪽 버튼 > Properties(속성) > Java Build Path(메뉴) > Libraries(탭 클릭) 
//	 > class Path(클릭) > Add Jars(선택) > 프로젝트 내부의 Jar 선택 > Apply And Close


//STUDENT TABLE 생성 : 학번(숫자형-PRIMARY KEY), 이름(문자형), 전화번호(문자형), 주소(문자형), 생년월일(문자형)
//CREATE TABLE STUDENT(NO NUMBER(4) PRIMARY KEY, NAME VARCHAR2(50), PHONE vARCHAR2(20), ADDRESS VRCHAR2(100), BIRTHDAY DATE);

//STUDENT 테이블에 학생정보를 삽입하는 JDBC 프로그램 작성
public class InsertStudentApp {
	public static void main(String[] args) {

		//JDBC 관련 객체를 저장하기 위한 참조변수는 try 영역 외부에서 선언해야함.
		//=> 그래야만 try 영역을 포함한 모든 변수에서 참조변수를 이용하여 객체를 사용할 수 있
		Connection con = null;
		Statement stmt = null;
		try {
			//[순서1]. Oracle Driver 클래스로 객체를 생성하여 DriverManager 클래스의 JDBC Driver 객체로 등록 (사용자가 직접하는 것이 아니라 클래스만 읽어들이면 알아서 해줌)
			// => 따라서, OravcleDriver 클래스를 읽어 메모리에 저장하여 자동으로 Oracle 객체를 생성하여 DriverManager 클래스의 JDBC Driver 객체로 등록 하는 것이 더 효율적이다.
			//# JDBC Driver 객체 : DriverManager 클래스에 등록되어 관리되는 Driver 객체
			//Driver 객체 : DBMS 서버에 접속하여 SQL 명령을 전달하는 기능을 제공하는 객체
			//DriverManager 클래스 : Driver 객체를 관리하기 위한 기능을 제공하는 클래스
			//DriverManager.registerdriver(Driver driver) : Driver 객체를 매개변수로 전달받아 DriverManager 클래스가 관리할 수 있는 
			//JDBC Driver 객체로 등록하는 메소드
			// => 동일한 클래스로 생성된 Driver 객체가 DriverManager 클래스에 여러개 등록 가능
			// => 불필요한 Driver 객체가 존재하여 성능의 저하 발생
//			DriverManager.registerDriver(new OracleDriver());	==> 사용자가 직접작성한것
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
				//Class.forName(String className) 메소드를 호출하여 ClassLoader 프로그램을 이용하여 OracleDriver 클래스를 읽어 
				//메모리에 저장할 수 있다.
				//=> OracleDriver 클래스의 정적영역에서 OracleDriver 클래스를 객체로 생성하여 
				//	 DriverManager 클래스의 JDBC Driver로 등록하는 메소드를 호출한다. (효율적인 방법) : Oracle > JDBC
			
			//[순서2]. DriverManager 클래스에 등록된 JDBC Driver 객체를 이용하여 DBMS 서버에 접속하기
			//DriverManager.gtConnection(String url, String user, String password)
			// => DriverManager 클래스에 등록된 JDBC Driver 객체를 이용하여 DBMS 서버에 접속
			// => DBMS 서버에 접속된 정보가 저장된 Connection 객체를 반환
			// => 접속 URL 주소의 프로토콜을 이용하여 특정  DBMS 서버에 접속(DBMS의 종류에 따라 전부 프로토콜이 다르기 때문)
			//URL(Uniform Resource Location) : 인터넷에 존재하는 자원의 위치를 표현하는 주소
			//형식) Protocol:ServerName:Port:Resource >> http://www.itwill.xyz:80/test/index.html
			//Oracle DBMS 서버에 접속하여 데이터베이스에 접근하기 위한 URL 주소
			//형식) jdbc:oracle:thin:@ServerName:Port:SID
			//ServerName : Oracle DBMS가 설치된 컴퓨터의 이름
			//Oracle port :1521
			// =>JDBC 관련 클래스의 메소드를 호출한 경우 반드시 SQLException 발생 - 일반예외 >> 예외처리 해야함.
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			//Connection con = DriverManager.getConnection(url, user, password);
			con=DriverManager.getConnection(url, user, password); 
				//>> 여기서 변수선언하면 try 블럭 안에서밖에 변수를 사용하지 못함. 
				//따라서, 다른 블럭에서도 사용하기 위해서는 try-catch 블럭 바깥에 변수를 선언해 줘야함.
			
			//[순서3].Connection 객체로부터 SQL 명령을 전달할 수 있는 Statement 객체를 반환받아 저장
			//Connection.createStatement() : SQL 명령을 전달할 수 있는 Statement 객체를 생성하여 반환하는 메소드
			//Statement 객체 : SQL 명령을 현재 접속중인 DBMS 서버에 전달하기 위한 기능을 제공하는 객체
			//Statement stmt = con.createStatement; 
				//>> 여기서 변수선언하면 try 블럭 안에서밖에 변수를 사용하지 못함. 
				//따라서, 다른 블럭에서도 사용하기 위해서는 try-catch 블럭 바깥에 변수를 선언해 줘야함.
			stmt=con.createStatement();
			
			//[순서4]. Statement 객체를 사용하여 SQL 명령(INSERT, UPDATE, DELETE, SELECT)을 
			//DBMS 서버에 전달하여 실행하고 실행결과를 반환받아 저장
			//Statement.excuteUpdate(String sql) : DML 명령을 전달하여 실행하는 메소드
			// => DML 명령의 실행결과로 조작행의 갯수를 정수값으로 반환
			//Statement.excuteQuery(String sql) : SELECT 명령을 전달하여 실행하는 메소드
			// => SELECT 명령의 실행결과로 검색행이 저장된 ResultSet 객체를 반환

			//String sql = "insert into student values(1000,'홍길동', '010-1234-5678', '서울시 강남구', '00/01/01')";
			//String sql = "insert into student values(2000,'임꺽정', '010-4567-7894', '수원시 월담구', '99/11/01')";
			String sql = "insert into student values(3000,'전우치', '010-9876-5432', '인천시 상당구', '01/12/01')";
			int rows = stmt.executeUpdate(sql);
			
			//[순서5]. 반환받은 SQL 명령의 실행 결과값을 사용자에게 제공 - 출력
			System.out.println("[메세지]"+rows+"명의 학생정보를 삽입 하였습니다.");
			
			//커넥션은 try 안에서 끊으면 안됨.
		
		} catch(ClassNotFoundException exception){
			System.out.println("[에러] OracleDriver 클래스를 찾을 수 없습니다.");	
		} catch(SQLException e){
			System.out.println("[에러] JDBC 관련 오류 = "+e.getMessage());
		} finally {	//예외 발생과 상관없이 무조건 실행되는 명령을 작성하는 영역
			try {
			//[순서6]. JDBC 관련 객체를 모두 제거(finally에서!!!) - 객체가 생성된 순서의 반대로 제거
				//Statement.close() : Statement 객체를 삭제하는 메소드
				/*
				stmt.close(); 
					//>> stmt라는 참조변수를 try안에서 선언하면 변수가 선언된 try블럭 안에서만 사용가능하기 때문에 에러뜸. 
					//따라서, try-catch바깥에 변수선언해야함.
					//Statement.close() : 
				con.close();
					//Connection.close() : Connection 객체를 삭제하는 메소드 - 접속 종료
				*/ //=> 이렇게만 쓴다면 위에 try-catch에서 예외가 발생하지 않을경우, null값이 들어있는 stmt, con이 실행되면
				   //   NullPointExceptin 이 발생할 수 있음.
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
					//따라서, NullPointException 발생을 방지하기 위해 if문 사용.
					//NullPointExceptin : 참조변수에 Null이 저장된 상태에서 메소드를 호출한 경우 발생하는 예외
			} catch (SQLException e2) {
				// TODO: handle exception
			}
			
		}	
	}
}
