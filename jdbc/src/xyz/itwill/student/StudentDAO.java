package xyz.itwill.student;

import java.util.List;

//DAO클래스가 상속받기 위한 인터페이스
//=> 가장 중요한 역할! )) 추상메소드를 선언하여 인터페이스를 상속받은 모든 자식클래스(DAO 클래스)가 
//	 동일한 메소드가 선언되도록 메소드의 작성 규칙을 제공
//=> DAO 클래스가 변경되어도 프로그램에 영향을 최소화하기 위해 인터페이스를 선언하는 것.
//따라서 클래스를 만들기 전에 인터페이스를 먼저 만드는 것을 권장한다.
//앞으로 대부분의 DB연동은 DAO클래스로 할 예정.

public interface StudentDAO {
	
	//학생정보를 전달받아 STUDENT 테이블에 삽입하고 삽입행의 갯수를 반환하는 메소드
//	int insertStudent(int no, String name, String phone, String address, String birthday);
	int insertStudent(StudentDTO student); //이게 더 효율적.
	
	//학생정보를 전달받아 STUDENT 테이블에 저장된 학생정보를 변경하고 갯수를 반환하는 메소드
	int updateStudent(StudentDTO student);
	
	//학번을 전달받아 STUDENT 테이블에 저장된 학생정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	int deleteStudent(int no);
	
	//학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생정보를 검색하여 반환하는 메소드
	//학번이 같은 학생은 없음.(이때는 학생 1명을 검색)
	//=> 일반적으로 단일행은 값 또는 DTO 객체를 반환한다.
	StudentDTO selectStudent(int no);
		//StudentDTO에 학생정보가 저장되어있으므로 반환하면	됨
		
	//이름을 전달받아 STUDENT 테이블에 저장된 해당 이름의 학생정보를 검색하여 반환하는 메소드
	//이름은 똑같은 학생이 있을 수 있음. (학생 여러명을 검색)
	//=> 일반적으로 다중행은 무조건 List 객체를 반환한다.
	List<StudentDTO> selectNameStudent(String name);
		//List객체 안에 학생이 여러명 들어가 있음
	
	//STUDENT 테이블에 저장된 모든 학생정보를 검색하여 반환하는 메소드
	List<StudentDTO> selectAllStudent(); //매개변수는 없음.	
		
		
}

