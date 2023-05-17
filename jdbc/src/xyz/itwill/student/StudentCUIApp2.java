/*
package xyz.itwill.student;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

public class StudentCUIApp2 {

	private BufferedReader in;
	
	public StudentCUIApp2() {
		in = new BufferedReader(new InputStreamReader(System.in));
		String[] menu = {"1. 학생정보 삽입", "2. 학생정보 변경", "3. 학생정보 삭제", "4. 학생정보 검색", "5. 힉생목록 출력", "6. 프로그램 종료"};
		System.out.println("<<학생관리프로그램>>");
		
		while(true) {
			for(String item:menu) {
				System.out.println(item);
			}
			int choice;
			try {
				System.err.println("메뉴선택[1~6] >>");
				choice=Integer.parseInt(in.readLine());
				if(choice<1||choice>6) throw new Exception();
			} catch (Exception e) {
				System.out.println("[에러] 메뉴를 잘못 선택했습니다.");
				System.out.println();
				continue; //while 구문 재실행
			}
			System.out.println();
			
			if(choice==6) break;
			
			switch(choice) {
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			}
			System.out.println();
		}
		System.out.println("[메세지] 학생 관리 프로그램을 종료합니다.");
	}
	
	
	public static void main(String[] args) {
		new StudentCUIApp2();
	}
	
//	[1.학생정보 삽입]
	public void addStudent() {
		System.out.println("### 학생정보 삽입 ###");
		
		try {
			int no;
			while(true) {
				System.out.print("학번 입력 >> ");
				String noTemp = in.readLine();
				
				if(noTemp ==null || noTemp.equals("")) {
					System.out.println("[입력오류] 학번을 반드시 입력해 주세요.");
					continue;
				}
				
				String noReg = "^[1-9][0-9]{3}$";
				if(!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력오류]학번은 4자리 숫자로만 입력해주세요.");
					continue;
				}
				
				no = Integer.parseInt(noTemp);
				
				StudentDTO student = StudentDAOImpl.getDao().selectStudent(no);
				
				if(student !=null) {
					System.out.println("[입력오류] 이미 사용중인 학번을 입력하였습니다.");
					continue;
				}
				
				break;
			}
			
			String name;
			while(true) {
				System.out.print("이름 입력 >>");
				name=in.readLine();
				
				if(name==null||name.equals("")) {
					System.out.println("[입력오류] 이름을 반드시 입력해 주세요.");
					continue;
				}
				
				String nameReg="^[가-힣]{2,5}$";
				if(!Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류] 이름은 2~5 범위의 한글로만 입력해주세요.");
					continue;
				}
				
				break;
			}
			
			String phone;
			while(true) {
				System.out.print("전화번호 입력 >>");
				phone=in.readLine();
				
				if(phone ==null || phone.equals("")) {
					System.out.println("[입력오류] 전화번호를 반드시 입력해 주세요.");
					continue;
				}
				
				String phoneReg = "(01[016789])-\\d{3,4}-\\d{4}";
				
			}
			
		}
	}
	
}
*/
	
	

