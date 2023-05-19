package jdbc_team06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOException;

public class CarCUIApp {
	
	private BufferedReader in;
	
	public CarCUIApp() {
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		String Carmenu[] = new String[] { "1.차종 추가", "2.차 정보 변경", "3.차 정보 삭제", "4.차 정보 검색"};
		String Component[] = new String[] {"1.부품 추가", "2.부품 변경", "3.부품 삭제", "4.부품 조회"};
		
		System.out.println("********** 카센타에 들어온 차 정보 검색 프로그램 **********");
		
		System.out.println("메뉴를 선택하세요");
		System.out.println("1.차 2. 부품");
		
		try {
			int menuSelect = Integer.parseInt(in.readLine());
			
			System.out.println("메뉴를 선택하세요");
			
			if(menuSelect == 1) {	// 메뉴 선택 1번
				for (String car  : Carmenu) {
					System.out.println(car);
					}
				
				while(true) {
					
					int carMenuSelect = Integer.parseInt(in.readLine());
					
					if(carMenuSelect == 5) {
						break;
					}
					
					switch(carMenuSelect) {
				
					case 1: break; 	//	1.차종 추가
					case 2: break;	// 2.차 정보 변경
					case 3: break;	// 3.차 정보 삭제
					case 4: break;	// 4.차 정보 검색
					}
					
					
				}
				
			} else if(menuSelect == 2) {
				
					
					
					for (String comp : Component) {
						System.out.println(comp);
					}
					
					System.out.println("메뉴를 선택하세요");
					
	
					while(true) {
						
						int compMenuSelect = Integer.parseInt(in.readLine());
						
						if(compMenuSelect < 1 || compMenuSelect > 5) {
							throw new IllegalAccessException();
						}
						
						if(compMenuSelect == 5) {
							
							break;
						}
						
						switch(compMenuSelect) {
					
						case 1: break; 	//	1.부품종 추가
						case 2: break;	//  2. 부품정보 변경
						case 3: break;	//  3. 부품 삭제
						case 4: break;	//  4. 부품 검색
						}
					}
					
					
				}
			}catch (IllegalAccessException e) {
				System.out.println("1~5번까지의 메뉴를 입력해주세요");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void InsertCar() {
		
		int no;
		
		try {
			String user = in.readLine();
			
			// 정규 표현식
			
			
			
			
			
			DAOImpl.getDaoImpl().insertCar();
					
					
			
					
				
			
		} catch (IOException e) {
			System.out.println("형식에 맞는 값을 입력해주세요");
		}
	}
	
}
