package xyz.itwill.dbcp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Properties 파일 : 프로그램 실행에 필요한 값을 제공하기 위한 텍스트파일 - 확장자 : ~.properties
//=> 프로그램을 변경하지 않고 Properties 파일의 내용을 변경하여 프로그램 실행결과를 바꿀 수 있다.
//=> 프로그램의 유지보수 효율성 증가
//=> Properties 파일에서 제공되는 값은 문자열만 가능
//=> Properties 파일에서는 영문자, 숫자, 일부 특수문자를 제외한 나머지 문자는 유니코드로 변환되어 처리된다.(못쓰는 것 아님)

//user.properties 파일에 저장된 값을 얻어와 출력하는 프로그램 작성해보자
public class PropertiesApp {
	public static void main(String[] args) throws IOException {

		//FileInputStream in = new FileInputStream("src/xyz/itwill/dbcp/user.properties"); //괄호 안에 경로 작성.
			//Properties 파일을 읽기위한 파일 입력스트림 생성
			//=> Properties 파일의 경로를 제공받아 FileInputStream 클래스로 객체 생성
			//=> 프로그램 배포시 파일 경로에 문제가 발생함. (따라서 이 방법은 별로 권장하기 않음)
			//==> 배포할 때 패키지파일은 배포가 되지 않기 때문.
		
		InputStream in = PropertiesApp.class.getClassLoader().getResourceAsStream("xyz/itwill/dbcp/user.properties");
			//클래스.class : class 파일을 이용하여 Class 객체(Clazz)를 반환하는 메소드
			//Object.getClass() : 현재 실행 중인 클래스에 대한 Class 객체(Clazz)를 받환하는 메소드
			//Class.getClassLoader() : 클래스를 읽어 메모리에 저장된 ClassLoader 객체를 반환하는 메소드
			//ClassLoader.getResourceAsStream(String name) : 리소스 파일에 대한 입력스트림을 생성하여 반환하는 메소드 (괄호안에는 리소스파일의 경로를 작성)
		
		
		Properties properties = new Properties();
			//Properties 객체(Map 인터페이스의 자식클래스) 생성
			//=> Properties 클래스는 Map 인터페이스를 상속받은 자식클래스
			//*Map(키와 값이 쌍으로 묶여 저장되는 인터페이스)
			//=> Properties 파일의 이름(Key)과 값(Value)을 하나의 엔트리(Entry)로 저장하기 위한 객체
		
		properties.load(in);
			//Properties.load(InputStream in) : 입력스트림으로 Properties 파일을 제공받아 파일에 저장된 모든 이름과 값으로
			//Properties 객체에 엔트리를 추가하는 메소드
		
		String id = (String)properties.get("id");
		String password = (String)properties.get("password");
		String name = (String)properties.get("name");
			//properties.get(String key) : Properties 객체된 엔트리에서 맵키(MapKey)를 전달받아 맵키와 매칭된 맵값(MapValue)를 반환하는 메소드
			//=> 맵값은 Object 객체로 반환되므로 반드시 명시적 객체형변환 후 사용이 가능하다.
		
		System.out.println("아이디 = "+id);
		System.out.println("비밀번호 = "+password);
		System.out.println("이름 = "+name);
		 
		//=>따라서, 이름이 바뀌면 properties 파일만 고치면 다른 결과를 제공받을 수 있음(이게 properties 파일을 이용하는 이유)

		
		
		
	}

}
