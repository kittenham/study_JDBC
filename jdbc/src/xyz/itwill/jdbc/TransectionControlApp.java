package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC 프로그램은 기본적으로 "AutoCommit 기능이 활성화 되어 있어" SQL 명령(DML)이 전달되면 실행되어 자동커밋처리 된다.
//=> 프로그램 실행시 예외가 발생된 경우, 예외 발생 전 전달되어 실행된 SQL 명령에 대한 롤백처리가 불가능하다.
//JDBC 프로그램에서 AutoCommit 기능을 비활성화 처리하고 예외 발생없이 프로그램이 정상적으로 실행되면, 커밋처리하고
//예외가 발생된 경우 롤백처리하는 것을 권장한다.

//STUDENT 테이블에 저장된 학생정보 중 학번이 [2000]인 학생의 이름을 [임꺽정]으로 변경하는 JDBC 프로그램 작성
public class TransectionControlApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String pasword = "tiger";
			con=DriverManager.getConnection(url, user, pasword);
			
			con.setAutoCommit(false);
				//Connection.setAutoCommit(boolean autoCommit) : AutoCommit 기능의 사용유무를 변경
				//=> false : AutoCommit 기능 비활성화,  true : AutoCommit 기능 활성화 (기본값)
			//if, 인위적인 예외를 만들어서 예를 들어 보자면, AutoCommit 기능이 활성화 되어 있다면 
			//예외가 발생했음에도 밑의 예외처리 기능을 통해 자동롤백이 되어 {[에러] OracleDriver 클래스를 찾을 수 없습니다.} 창이 뜨지만,
			//이미 롤백 전에 자동적으로 커밋이 되었으므로 데이터베이스에는 임걱정 -> 임꺽정으로 이름이 변경되어 있는 것을 확인할 수 있다.
			//하지만, AutoCommit 기능이 비활성화되어 있다면, 자동 커밋이 되지 않으므로 예외가 발생되었을때 예외처리로 인해 롤백처리가 되어 
			//'임걱정'이 그대로 출력됨을 확인할 수 있다.
			//자바는 순차적으로 진행되기 때문에 앞에서 커밋을 했다면 뒤에 롤백을 하더라도 이미 커밋이 되었기 때문에 값이 전으로 돌아갈 수 없다.
			
			stmt = con.createStatement();
			
			String sql = "update student set name='임꺽정' where no=2000";
			int rows = stmt.executeUpdate(sql);
			
			//if(con!=null) throw new Exception(); //인위적인 예외 발생
			
			if(rows>0) {
				System.out.println("[메세지]"+rows+"명의 학생정보를 변경하였습니다.");
			}else {
				System.out.println("[메세지] 변경 처리할 학번의 학생정보를 찾을 수 없습니다.");
			}
			
			con.commit();
				//Connection.commit() : 커밋처리하는 메소드 - 전달된 SQL 명령이 실제 테이블에 적용됨.
			
		} catch (ClassNotFoundException e) {
			System.out.println("[에러] OracleDriver 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("[에러] JDBC 관련 오류 = "+e.getMessage());
		} catch (Exception e) {
			System.out.println("[에러] 프로그램에 예기치 못한 오류가 발생 되었습니다.");
		}  finally {
			try {
				con.rollback();
					//Connection.rollback() : 롤백 처리하는 메소드 - 전달된 SQL 명령의 실행을 취소
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e2) { }
		}
		
	}
}
