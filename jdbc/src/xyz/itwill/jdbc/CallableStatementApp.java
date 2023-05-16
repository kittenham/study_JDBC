package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

/* 오라클에서 작성 (저장 프로시저)
CREATE OR REPLACE PROCEDURE DELETE_STUDENT(VNO IN STUDENT.NO%TYPE, VNAME OUT STUDENT.NAME%TYPE) IS	//VNO(인모드 파라미터), VNAME(아웃모드 파라미터)
BEGIN
    SELECT NAME INTO VNAME FROM STUDENT WHERE NO=VNO;
    IF SQL%FOUND THEN
        DELETE FROM STUDENT WHERE NO=VNO;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        VNAME :=NULL;
END;
/
 */

//키보드로 학번을 입력받아 STUDENT 테이블에 저장된 학생정보 중 해당 학번의 학생정보를 삭제하는 JDBC 프로그램 작성
//-DBMS의 저장 프로시저 자바에서 호출하여 학생정보를 삭제 처리해보자.
//회사에 저장 프로시저가 있다는 전제하에 실시(굳이 프로시저를 만들 이유가 없으니까 불러오기만하면됨)
public class CallableStatementApp {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("<<학생정보 삭제>>");
		System.out.print("학번 입력 >> ");
		int no = Integer.parseInt(in.readLine()); //키보드 입력
		
		System.out.println("=====================================================================");
		
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "{call delete_student(?,?)}";
			//Connection.prepareCall(String sql) : 저장 프로시저를 호출하는 명령을 전달하여 실행하기 위한 CallableStatement 객체를 반환하는 메소드
			//=> 저장 프로시저를 호출하는 명령 >>> {call 저장프로시저명(?,?,...)}
			//=> 저장 프로시저 호출시 사용한 ? 기호에는 반드시 setXXX() 라는 메소드를 호출하여 값을 전달하거나 
			//	registerOutParameter() 메소드를 호출하여 값을 제공받아 저장한다.
			//저장 프로시저를 호출하는 메소드
		CallableStatement cstmt = con.prepareCall(sql);
			//CallableStatement.setXXX(int parameterIndex, XXX value) : 저장 프로시저에 사용한 매개변수 중 IN 모드의 매개변수에 값을 전달하기 위한 메소드
		
		cstmt.registerOutParameter(2, Types.NVARCHAR); 	//①두번째 매개변수는 아웃모드라는 것을 알려주는 것.
			//CallableStatement.registerOutParameter(int parameterIndex, int sqlType)
			//=> 저장 프로시저에서 사용한 매개변수 중 OUT 모드의 매개변수에 저장된 값을 제공받기 위한 메소드
			//=> sqlType : SQL 자료형 - Types 클래스의 상수 사용
		
		cstmt.execute();
			//CallableStatement.execute() : 저장 프로시저를 호출하는 명령을 전달하여 실행하는 메소드
		
		String name = cstmt.getString(2);	//②따라서, VNAME에 저장된 실행값은 2개가 됨
			//CallableStatement.getXXX(int parameterIndex) : 저장 프로시저에서 사용한 매개변수 중 OUT 모드의 매개변수에 저장된 값을 반환하는 메소드
		
		if(name==null) {
			System.out.println("[메세지] 해당 학번의 학생정보를 찾을 수 없습니다.");
		} else {
			System.out.println("[메세지]"+name+"님을 삭제 하였습니다.");
		}
		
		ConnectionFactory.close(con, cstmt);;
		
	}
	
}
