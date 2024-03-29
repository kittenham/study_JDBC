package xyz.itwill.jdbc;

//Class 객체(Clazz)를 수동으로 생성하는 방법(제공받는 방법) 3가지.
//1) Class.forName(String className) 메소드를 호출하여 반환받는 방법
//2) Object.getClass() 메소드를 호출하여 현재 사용중인 클래스에 대한 Class 객체를 반환받는 방법
//3) [클래스명.클래스] 형식으로 표현하는 방법

public class StaticBlockApp {
	public static void main(String[] args) throws ClassNotFoundException {
		/*<얘는 JVM에 의해서 자동>
		//1. JVM은 ClassLoader 프로그램을 이용하여 클래스(Class 파일)를 읽어 메모리(메소드 영역)에 저장한다.
		//2. new 연산자로 메모리에 저장된 클래스(Class 객체 - Clazz)의 생성자를 호출하여 객체(Object - instance) 생성 - 메모리의 Heap 영역에 객체 생성
		//3. 생성된 객체의 메모리주소(HashCode)를 제공받아 참조변수를 생성하여 저장.
		//4. 참조변수는 메모리의 Stack 영역에 만들어진다.
		StaticBlock sb = new StaticBlock();
		
		//참조변수에 저장된 메모리 주소로 객체를 참조하여 메소드를 호출
		sb.display();
		*/
		
		//<얘는 수동>
		//Class.forName(String className) : 문자열로 표현된 패키지가 포함된 클래스를 전달받아 ClassLoader 프로그램을 사용하여 클래스를 읽어 메모리에 저장하는 메소드
		//=> 메모리에 저장된 클래스의 정보가 저장된 Class 객체(Clazz) 반환
		//=> ClassNotFoundException 발생 : 해당 패키지에 클래스가 없는 경우 발생 - 일반예외
		Class.forName("xyz.itwill.jdbc.StaticBlock"); //수동으로 클래스를 메모리에 저장한 것.
		StaticBlock sb = new StaticBlock();
		sb.display();

	}
}
