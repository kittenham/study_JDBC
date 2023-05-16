package xyz.itwill.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

//DAO(Data Access Object) 클래스 : 저장매체에 행 단위 정보를 삽입, 삭제, 변경, 검색하는 기능을 제공하는 클래스
//=> 저장매체 : 정보를 행단위로 저장하여 관리하기 위한 하드웨어 또는 소프트웨어를 말한다. = DBMS
//--> 인터페이스를 상속받아 작성하는 것을 권장 >> 메소드 작성 규칙을 제공받을 수 있음 >> 따라서 유지보수의 효율성을 증가 시킬 수 있다.
//=> 싱글톤 디자인 패턴을 적용하여 작성하는 것을 권장 - 프로그램에 하나의 객체만 제공되는 클래스

//STUDENT 테이블에 행을 삽입, 삭제, 변경, 검색하는 기능의 메소드를 제공하는 클래스
//=> 하나의 메소드는 매개변수로 SQL 명령에 필요한 값을 객체(변수)로 전달받아 하나의 SQL 명령을 DBMS 서버에 전달하여 실행하고 
//	실행결과를 자바 객체(값)으로 매핑하여 반환
//=> JdbcDAO 클래스를 상속받아 DAO 클래스의 메소드에서 JdbcDAO 클래스의 메소드를 호출할 수 있다.
public class StudentDAOImpl extends JdbcDAO implements StudentDAO{
	
	private static StudentDAOImpl _dao;
	
	private StudentDAOImpl() {
		
	}
	
	static {
		_dao=new StudentDAOImpl();
	}
	
	public static StudentDAOImpl getDao() {
		return _dao;
	}

	//>>여기까지 싱글톤 디자인 패턴.
	
	
	
	//학생정보를 전달받아 STUDENT 테이블에 삽입하고 삽입행의 갯수를 반환하는 메소드
	@Override
	public int insertStudent(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con = getConnection();
			
			String sql = "insert into student values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getAddress());
			pstmt.setString(5, student.getBirthday());
			
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[에러] insertStudent");
		}finally {
			close(con, pstmt);
		}
		return 0;
	}

	
	//학생정보를 전달받아 STUDENT 테이블에 저장된 학생정보를 변경하고 갯수를 반환하는 메소드
	@Override
	public int updateStudent(StudentDTO student) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//학번을 전달받아 STUDENT 테이블에 저장된 학생정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	@Override
	public int deleteStudent(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생정보를 검색하여 반환하는 메소드
	@Override
	public StudentDTO selectStudent(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//이름을 전달받아 STUDENT 테이블에 저장된 해당 이름의 학생정보를 검색하여 반환하는 메소드
	@Override
	public List<StudentDTO> selectNameStudent(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//STUDENT 테이블에 저장된 모든 학생정보를 검색하여 반환하는 메소드
	@Override
	public List<StudentDTO> selectAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
