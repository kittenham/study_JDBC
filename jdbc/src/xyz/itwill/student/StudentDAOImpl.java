package xyz.itwill.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public static StudentDAOImpl getDAO() {
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
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update student set name=?,phone=?,address=?,birthday=? where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getPhone());
			pstmt.setString(3, student.getAddress());
			pstmt.setString(4, student.getBirthday());
			pstmt.setInt(5, student.getNo());

			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateStudent() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	
	//학번을 전달받아 STUDENT 테이블에 저장된 학생정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	@Override
	public int deleteStudent(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="delete from student where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteStudent() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	
	//학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생정보를 검색하여 반환하는 메소드
	@Override
	public StudentDTO selectStudent(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StudentDTO student=null;
		try {
			con=getConnection();
			
			String sql="select * from student where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			
			//ResultSet 객체에 저장된 검색행을 Java 객체(값)로 매핑 처리
			//검색행이 0 또는 1인 경우 선택문 사용
			if(rs.next()) {//검색행이 있는 경우
				student=new StudentDTO();
				//처리행의 컬럼값을 반환받아 DTO 객체의 필드값으로 변경
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(rs.getString("birthday").substring(0, 10));
				//substring을 넣는 이유 : Day를 String으로 바꿀때 yyyy-MM-dd HH-mm-ss가 되는데 이때 시간은 읽지 않고 날짜만 읽도록 설정하기 위해.
				//만약, 처음부터 Birthday의 String을 yyyy-MM-dd로 설정해놓으면 여기서 substring을 따로 설정해놓지 않아도 됨.
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudent() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		//검색행이 없는 경우 [null]를 반환하고 검색행이 있으면 DTO 객체 반환
		return student;
	}


	
	//이름을 전달받아 STUDENT 테이블에 저장된 해당 이름의 학생정보를 검색하여 반환하는 메소드
	@Override
	public List<StudentDTO> selectNameStudentList(String name) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<StudentDTO> studentList=new ArrayList<>();
		try {
			con=getConnection();
			
			String sql="select * from student where name=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs=pstmt.executeQuery();
			
			//검색행이 0개 이상인 경우 반복문 사용
			while(rs.next()) {
				//하나의 검색행을 DTO 객체로 매핑 처리
				StudentDTO student=new StudentDTO();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(rs.getString("birthday").substring(0, 10));
				
				//DTO 객체를 List 객체의 요소로 추가 
				studentList.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectNameStudentList() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return studentList;
	}

	
	//STUDENT 테이블에 저장된 모든 학생정보를 검색하여 반환하는 메소드
		public List<StudentDTO> selectAllStudentList() {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<StudentDTO> studentList=new ArrayList<>();
			try {
				con=getConnection();
				
				String sql="select * from student order by no";
				pstmt=con.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					StudentDTO student=new StudentDTO();
					student.setNo(rs.getInt("no"));
					student.setName(rs.getString("name"));
					student.setPhone(rs.getString("phone"));
					student.setAddress(rs.getString("address"));
					student.setBirthday(rs.getString("birthday").substring(0, 10));
					
					studentList.add(student);
				}
			} catch (SQLException e) {
				System.out.println("[에러]selectAllStudentList() 메소드의 SQL 오류 = "+e.getMessage());
			} finally {
				close(con, pstmt, rs);
			}
			return studentList;
		}
	


	@Override
	public List<StudentDTO> selectNameStudent(String name) {
		return null;
	}

	@Override
	public List<StudentDTO> selectAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

}
