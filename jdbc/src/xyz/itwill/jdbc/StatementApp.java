package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//Statement 객체 : 현재 접속중인 DBMS 서버에 SQL 명령을 전달하여 실행하기 위한 기능을 제공하는 객체
//=> 장점 : 하나의 Statement 객체를 사용하여 다수의 SQL 명령을 전달하여 실행 가능
//=> 단점 : SQL 명령에 Java 변수값을 포함할 경우 문자열 결합 기능 사용
//==> 문제1) 문자열 결합을 이용할 경우 가독성 및 유지보수의 효율성 감소됨.
//==> 문제2) InSQL 해킹 기술(값 대신 부분적인 SQL을 입력해 공격하는 해킹 기술)에 취약하다. (따라서, 웹사이트를 만들때는 Statement 객체 사용 금지)

public class StatementApp {

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
		
		Connection con = ConnectionFactory.getConnection();
		
		Statement stmt = con.createStatement();
		
		String sql1 = "insert into student values("+no+",'"+name+"','"+phone+"','"+address+"','"+birthday+"')";
		//no는 숫자값이라 상관없지만 name과 phone은 문자값이라 밖에 '' 을 꼭 사용해야함!
		int rows = stmt.executeUpdate(sql1);
		
		System.out.println("[결과]"+rows+"명의 학생정보를 삽입하였습니다.");
		
		System.out.println("======================================================");
		
		String sql2 = "select * from student order by no";
		
		ResultSet rs = stmt.executeQuery(sql2);
		
		System.out.println("<<학생정보 출력>>");
		while(rs.next()) {
			System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name")+
					", 전화번호 = "+rs.getString("phone")+", 주소 = "+rs.getString("address")+
					", 생일 = "+rs.getString("birthday").substring(0, 10));
		}
		
		ConnectionFactory.close(con, stmt, rs);
		
*/		
		
		//>>Statement를 사용해 웹사이트를 만들었을때 InSQL 해킹 기술에 취약한 이유 설명
		

		//키보드로 이름을 입력받아 STUDENT 테이블에 저장된 학생정보 중 해당 이름의 학생정보를
		//검색하여 출력하는 JDBC 프로그램 작성
		
		//키보드로 학생정보를 입력받기 위한 입력스트림을 생성
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		//키보드로 이름을 입력받아 저장
		System.out.println("<<학생정보 검색>>");
		System.out.print("이름 입력 >> ");
		String name=in.readLine();
		System.out.println("==============================================================");
		
		//STUDENT 테이블에 저장된 학생정보 중 해당 이름의 학생정보를 검색하여 출력		
				Connection con=ConnectionFactory.getConnection();
				
				Statement stmt=con.createStatement();
				
				String sql="select * from student where name='"+name+"' order by no";
				ResultSet rs=stmt.executeQuery(sql);
				
				System.out.println("<<검색결과>>");
				if(rs.next()) {
					do {
						System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name")
							+", 전화번호 = "+rs.getString("phone")+", 주소 = "+rs.getString("address")
							+", 생년월일 = "+rs.getString("birthday").substring(0, 10));
					} while(rs.next());
				} else {
					System.out.println("검색된 학생정보가 없습니다.");
				}
				System.out.println("==============================================================");
				ConnectionFactory.close(con, stmt, rs);
				
				//작성 후 콘솔화면에서 값을 입력할때 옳은 값을 입력하면 문제가 없음.
				//하지만, 입력값으로 [ ' or '1'='1 ]을 집어넣는다면, 코드의 값으로  name='"+name+"' order by no" 가
				//name='' or '1'='1' 가 되어버려, null 값이거나, 1=1 (무조건적인참)이 되어버려 사용자가 관리자 권한을 획득 할 수 있게 됨.
				//따라서, 웹 사이트를 만들때는 Statement 객체를 사용하는 것을 권장하지 않는다.

	}
}
