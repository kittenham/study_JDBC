package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//PreparedStatement 객체 : 현재 접속중인 DBMS 서버에 SQL 명령을 전달하여 실행하기 위한 기능을 제공하는 객체
//	(Statement를 부모로 가지는 자식개체이다.)
//=> 장점1) InParameter를 사용하여 SQL 명령에 Java 변수값을 문자값으로 포함하여 사용 가능
//=> 장점2) InParameter를 사용하여 가독성이 향상되고 유지보수의 효율성 증가
//=> 장점3) InSQL 해킹 기술을 무효화 처리  - InParameter로 전달받은 사용자 입력값은 SQL 명령에서 무조건 문자값으로 처리
//			(Statement의 단점이 보완가능해짐.)
//=> 단점) 하나의 PreparedStatement는 저장된 하나의 SQL 명령만 전달하여 실행 가능
public class PreparedStatementApp {
	public static void main(String[] args) throws Exception{
		
/*		
		//키보드로 학생정보를 입력받아 STUDENT 테이블에 삽입하고 STUDENT 테이블에 저장된 모든 학생 정보를 
		//검색하여 출력하는 JDBC 프로그램 작성
				
		//키보드로 학생정보를 입력받기 위한 입력스트림을 생성
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
		//키보드로 학생정보를 입력받아 저장
		System.out.print("학번 입력 >> ");
		int no = Integer.parseInt(in.readLine());
		System.out.print("이름 입력 >> ");
		String name = in.readLine();
		System.out.print("전화번호 입력 >> ");
		String phone = in.readLine();
		System.out.print("주소 입력 >> ");
		String address = in.readLine();
		System.out.print("생년월일 입력 >> ");
		String birthday = in.readLine();
		
		System.out.println("========================================================");
		//STUDENT 테이블에 키보드로 입력받은 학생정보를 삽입 처리
		Connection con = ConnectionFactory.getConnection();
		
		//Conndection.prepareStatement(String sql) : Connection 객체로부터 SQL 명령이 저장된 PreparedStatement 객체를 반환하는 메소드
		//prepareStatement은 sql명령이 미리 저장되어 있다. (Statement 객체는 sql 명령이 나중에 저장됨)
		//=> PreaparedStatement 객체에 저장되는 SQL 명령에는 ?(InParameter) 기호 사용
		//InParameter : Java 변수값을 제공받아 SQL 명령의 문자값으로 표현하기 위한 기호
		//=> 반드시 모든 InParameter에는 Java 변수값을 전달받아 완전한 SQL 명령이 완성되어야한다.
		
		String sql="insert into student values(?,?,?,?,?)"; //=> 지금 이 식은 불완전한 SQL 명령임.
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//PreparedStatement.setXXX(int parameterIndex, XXX value) 를 사용해서 완전한 SQL 명령을 만들 수 있다.
		//=> PreparedStatement 객체에 저장된 SQL 명령의 InParameter에 Java 변수값을 전달하는
		//=> XXX : InParameter에 전달하기 위한 값에 대한 Java 자료형
		//=> parameterIndex : InParameter의 위치값(첨자) - 1부터 1씩 증가되는 정수값
		//=> 반드시 모든 InParameter에는 Java 변수값을 전달받아 완전한 SQL 명령이 완성되어야한다.
		
		pstmt.setInt(1, no);
		pstmt.setString(2, name);
		pstmt.setString(3, phone);
		pstmt.setString(4, address);
		pstmt.setString(5, birthday);
		
		//PreparedStatement.excuteUpdate() : PreparedStatement 객체에 저장된 DML 명령을 전달하여 실행하고 
		//조작행의 갯수를 정수값(int)로 반환하는 메소드  (PreparedStatement에는 이미 sql이 저장되어 있기 때문에 매개변수는 집어넣으면 안됨.)
		int rows = pstmt.executeUpdate();
		
		System.out.println("[결과]"+rows+"명의 학생정보를 삽입하였습니다.");
		
		//STUDENT 테이블에 저장된 모든 학생정보를 검색하여 출력 처리
		String sql2 = "select * from student order by no";
		pstmt=con.prepareStatement(sql2);
		
		
		ResultSet rs = pstmt.executeQuery();
			//PreparedStatement.executeQuery() : PreparedStatement 객체에 저장된 SELECT 명령을 전달하여 실행하고
			//모든 검색행이 저장된  ResultSet 객체를 반환하는 메소드
		
		System.out.println("<<학생정보 출력>>");
		while(rs.next()) {
			System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name")+
					", 전화번호 = "+rs.getString("phone")+", 주소 = "+rs.getString("address")+
					", 생일 = "+rs.getString("birthday").substring(0, 10));
		}
	
		 ConnectionFactory.close(con, pstmt, rs);
*/
		
		
		//키보드로 학생정보를 입력받아 STUDENT 테이블에 저장된 학생정보 중 해당 이름의 학생정보를 검색하여 출력하는 JDBC 프로그램 작성
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("<<학생정보 검색>>");
		System.out.print("학생 이름 입력 >> ");
		String name = in.readLine();
		
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "select * from student where name = ? order by no";
			//"name ="+name+"order by no" 라고 하지 않음.
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);	//*****
		
		ResultSet rs = pstmt.executeQuery();
		
		
		System.out.println("<<검색결과>>");
		if(rs.next()) {
			do {
				System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name")+
						", 전화번호 = "+rs.getString("phone")+", 주소 = "+rs.getString("address")+
						", 생일 = "+rs.getString("birthday").substring(0, 10));
			} while(rs.next());
		} else {
			System.out.println("검색된 학생 정보가 없습니다.");
		}
		ConnectionFactory.close(con, pstmt, rs);
		
		
		
		
		
		
		
		
	}

}
