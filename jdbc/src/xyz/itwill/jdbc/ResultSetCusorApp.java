package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetCusorApp {
	public static void main(String[] args) throws SQLException{
		Connection con = ConnectionFactory.getConnection();
		
		Statement stmt=con.createStatement();			
			//Connection.createStatement() : 현재 접속중인 DBMS 서버에 SQL 명령을 전달하기 위한 Statement 객체를 반환하는 메소드
			//createStatement()에 매개변수를 설정하지 않았으므로 무조건 다음행으로만 커서 이동함.
			//원하는 곳으로 커서를 이동하고 싶으면 createStatement()에 매개변수로 ResultSet.TYPE_SCROLL_INSENSITIVE, 
			//	ResultSet.CONCUR_READ_ONLY와 같은 이동 관련 속상값 전달하면됨. 아래 설명 있음.
		
		String sql = "select * from student order by no";
		ResultSet rs = stmt.executeQuery(sql);
			//Statement.excuteQuery(String sql) : SELECT 명령을 전달하여 실행하고 검색된 모든 행이 저장된 ResultSet 객체를 반환하는 메소드
			//=> ResultSet 객체에 저장된 모든 검색행을 행단위로 처리하기 위해 내부적으로 커서(Cursor)를 이용 >> ResultSet 커서
			//=> ResultSet 커서는 다음행으로만 이동 가능하여 커서 위치의 처리행에 대한 조작 불가능
		
			//ResultSet.next() : ResultSet 커서를 다음행으로 이동하는 메소드
			//=> 이동된 커서 위치에 처리행이 없는 경우 [false]를 반환하고, 처리행이 있는 경우 [true]를 반환함

		while(rs.next()) {
			System.out.println(rs.getRow()+"행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
				//ResultSet.getRow() : ResultSet 커서가 위치함 처리행의 행번호(RowIndex)를 반환하는 메소드
				//Resultset.getXXX(String columnLabel) : ResultSet 커서가 위차한 처리행의 컬럼값을
		}
		System.out.println("=======================================================");
		
		stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//Connection.createStatement(Int resultSetType, inresultSetConcurrency) 
			//=> 현재 접속중인 DBMS 서버에 SQL 명령을 전달하기 위한 Statement 객체를 반환하는 메소드
			//=> 매개변수에 전달되는 값에 따라 Statement 객체에 의해 생성되는 ResultSet 객체의 커서에 대한 
			//	 이동 설정 및 커서가 위치한 처리행의 조작설정 가능
			//resultSetType : ResultSet 커서의 이동 관련 값(ResultSet 인터페이스의 상수 사용)을 전달하여 설정
			//=> ResultSet.TYPE_FORWARD_ONLY : ResultSet 커서를 다음행으로만 이동 가능 - 기본
			//=> ResultSet.TYPE_SCROLL_INSENSITIVE : ResultSet 커서를 자유롭게 이동 가능 - 데이타베이스 변경 미반영(멀티유저가 업데이트 한 내용이 반영안됨.)
			//=> ResultSet.TYPE_SCROLL_SENSITIVE : ResultSet 커서를 자유롭게 이동 가능 - 데이타베이스 변경 반영(멀티유저가 업데이트 한 내용이 반영되어 사용.)
			//resultSetConcurrency : ResultSet 커서가 위치한 처리행의 조작 관련 속성값(ResultSet 인터페이스의 상수) 전달
			//=> ResultSet.CONCUR_READ_ONLY : ResultSet 커서가 위치한 처리행 조작 불가능 - 기본
			//=> ResultSet.CONCUR_UPDATABLE : ResultSet 커서가 위치한 처리행 조작 가능

		
		sql = "select * from student order by no";
		rs = stmt.executeQuery(sql);
		
		rs.first();
			//ResultSet.first() : ResultSet 커서를 첫번째 행으로 이동하는 메소드
		System.out.println(rs.getRow()+"행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));

		rs.last();
			//ResultSet.last() : ResultSet 커서를 마지막 행으로 이동하는 메소드
		System.out.println(rs.getRow()+"행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		
		rs.absolute(2);	//2번째 행으로 커서 이동.
			//ResultSet.absolute(int rowIndex) : ResultSet 커서를 매개변수로 전달받은 행으로 이동하는 메소드
		System.out.println(rs.getRow()+"행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
			
		rs.afterLast();
			//ResultSet.afterLast() : ResultSet 커서를 이전 행으로 이동하는 메소드
		
		while(rs.previous()) {
			System.out.println(rs.getRow()+"행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		
		System.out.println("=======================================================");
				
		rs.beforeFirst();
			//ResultSet.beforeFirst() : ResultSet 커서를 BOF(Before of File) 행으로 이동하는 메소드
		
		while(rs.next()) { //다음 행으로 이동
			System.out.println(rs.getRow()+"행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		
		System.out.println("=======================================================");

		stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		//ResultSet 커서가 위치한 처리행을 조작하기 위해서는 SELECT 명령의 검색대상에 [*] 사용 불가능
		sql="select no,name,phone,address,birthday from student order by no";
		rs=stmt.executeQuery(sql);

		
		/*
		rs.absolute(2);
		
		//ResultSet.updateXXX(String columnLabel, XXX value) : ResultSet 객체에서 ResultSet
		//	커서가 위치한 처리행의 컬럼값을 변경하는 메소드
		// => XXX는 컬럼값을 변경하기 위해 전달받기 위한 Java 자료형 
		rs.updateString("name", "임걱정"); //rs(검색행이 존재) 커서가 위치한 처리행에 name을 임걱정으로 바꾸겠다는 뜻
		
		//ResultSet.updateRow() : ResultSet 객체에서 ResultSet 커서가 위치되어 변경된 
		//	컬럼값이 저장된 행을 실제 테이블에 적용하여 변경 처리하는 메소드
		rs.updateRow();
		*/
		
		
		/*
		rs.absolute(3);
		
		//ResultSet.moveToInsertRow() : ResultSet 객체에서 ResultSet 커서가 위치한 다음행에(아래행)
		//	새로운 행을 삽입하고 기존행은 다음행으로 차례대로 이동 처리하는 메소드
		rs.moveToInsertRow();
		
		//새롭게 삽입된 행의 컬럼값을 변경 (새로운 행이라 값이 없으니까 채워줘야함)
		rs.updateInt("no", 4000);
		rs.updateString("name", "일지매");
		rs.updateString("phone", "010-6715-9081");
		rs.updateString("address", "서울시 종로구");
		rs.updateString("birthday", "2000-12-31");
		
		//ResultSet.insertRow() : ResultSet 객체에서 삽입된 행을 실제 테이블에 적용하여
		//삽입 처리하는 메소드
		rs.insertRow();
		*/
		
		//rs.absolute(4);
		
		rs.deleteRow();
			//ResultSet.deleteRow() : ResultSet 객체에서 ResultSet 커서가 위치한 처리행을 삭제하고 실제 테이블에 적용하여 삭제 처리하는 메소드
		
		rs.beforeFirst();
		while(rs.next()) {
			System.out.println(rs.getRow()+"행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		
		System.out.println("==============================================================");
		ConnectionFactory.close(con, stmt, rs);
	}
}