package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteApp {
	public static void main(String[] args) throws SQLException{
		
		/*
		Connection con = ConnectionFactory.getConnection();
		
		Statement stmt = con.createStatement();

		String sql1 = "update student set name='임걱정' where no=2000";
		int rows = stmt.executeUpdate(sql1);
			//executeUpdate는 검색행을 반환하지 못하기 때문에 SELECT 명령 실행에 사용X
		
		System.out.println("[메세지]"+rows+"명의 학생정보를 변경하였습니다.");
		System.out.println("========================================================");
		
		String sql2 = "select * from student order by no";
		ResultSet rs = stmt.executeQuery(sql2);
			//executeQuery => select 명령을 실행해서 검색행들이 저장된 ResultSet 객체로 반환
		
		while(rs.next()) {
			System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		System.out.println("=========================================================");
		ConnectionFactory.close(con, stmt, rs);
		*/
		
		Connection con = ConnectionFactory.getConnection();
		Statement stmt = con.createStatement();
		
		int choice =1 ;
		String sql = "";
		if(choice ==1) {
			sql = "update student set name='임꺽정' where no=2000";
		}else {
			sql = "select * from student order by no";
		}
		
		stmt.execute(sql);
			//Statement.execute(String sql) : SQL 명령을 전달하여 실행하는 메소드 - boolean 반환
			// => 전달되어 실행될 SQL명령이 명확하지 않은 경우 사용되는 메소드 (반환값으로 Select를 사용할지 안할지 결정하는 것)
			// -> false 반환 : DML 명령 또는 DDL 명령을 전달하여 실행된 경우의 반환값 (INSERT, UPDATE, DELETE 사용 결정)
			// -> true 반환 : Select  명령을 전달하여 실행된 경우의 반환값 (SELECT 사용결정)
			//if, 전달되어 실행될 SQL명령이 명확한 경우 => [Select >> executeQuery], [Update, Insert, Delete >>executeUpdate] 사용
		
		boolean result = stmt.execute(sql);
		
		if(result) { //Select 명령이 전달되어 실행된 경우
			ResultSet rs = stmt.getResultSet();
				//Statement.getResultSet() :  Statement 객체로 전달되어 실행된 Select 명령의 처리결과를 ResultSet 객체로 반환하는 메소드
			while(rs.next()) {
				System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
			}
			ConnectionFactory.close(con, stmt, rs);
		} else { //DML 명령 또는 DDL 명령이 전달되어 실행된 경우
			int rows = stmt.getUpdateCount();
				//Statement.getUpdateCoundt() : Statement 객체로 전달되어 실행된 DML 명령 또는 DDL 명령의 처리결과를 정수값(int)로 반환하는 메소드
			
			System.out.println("[메세지]"+rows+"명의 학생정보를 변경하였습니다.");
			
			ConnectionFactory.close(con, stmt);
		}
		
	}
	
	
}
