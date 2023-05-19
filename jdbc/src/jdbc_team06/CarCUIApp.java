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
		
		String Carmenu[] = new String[] { "1.차 정보 추가", "2.차 정보 변경", "3.차 정보 삭제", "4.차 정보 검색"};
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
				
					case 1: insertCar(); break; 	//	1.차종 추가
					case 2: updateCar(); break;	// 2.차 정보 변경
					case 3: deleteCar(); break;	// 3.차 정보 삭제
					case 4: selectCar(); break;	// 4.차 정보 검색
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
					
						case 1: insertComponent(); break; 	//	1.부품종 추가
						case 2: updateComponent(); break;	//  2. 부품정보 변경
						case 3: deleteComponent(); break;	//  3. 부품 삭제
						case 4: selectComponent(); break;	//  4. 부품 검색
						}
					}
					
					
				}
			}catch (IllegalAccessException e) {
				System.out.println("1~5번까지의 메뉴를 입력해주세요");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertCar() {
		int carNo = 0;
		String nec_component="";
		String car_name="";
		String owner_name="";
		try {
			while(true) {
				System.out.println("차량번호를 입력하세요");
			String no = in.readLine();
			if(no == null || no.equals("")) {
				continue;
			}
			//정규표현식

			carNo=Integer.parseInt(no);
			CarDTO car=DAOImpl.getDaoImpl().selectCar(carNo);
			
			
			if(car!=null) {
				System.out.println("이미 있는 차량 번호입니다.");
				continue;				
				}
			
			break;
			}
			
		while(true) {
			System.out.println("차이름을 입력해주세요");
				car_name= in.readLine();
				//정규표현식
				if(car_name==null || car_name.equals("")) {
					
					continue;
				}
				
				break;
			 }
			
		while(true) {
				 
			System.out.println("차주이름을 입력해주세요");
				owner_name= in.readLine();
				//정규표현식
				
				if(owner_name==null || owner_name.equals("")) {
					continue;
				}
				break;
			 }
		while(true) {
				 
			System.out.println("필요 부품을 입력해주세요");
				nec_component = in.readLine();
				//정규표현식
				if(nec_component==null || nec_component.equals("")) {
					continue;
				}
				break;
			}
		}catch(IOException e) {		
			e.printStackTrace();
		}
		
		
		CarDTO carDTO=new CarDTO(carNo, car_name, owner_name, nec_component);	
		int rows=DAOImpl.getDaoImpl().insertCar(carDTO);
		System.out.println(rows+"개의 차 정보가 삽입되었습니다.");
		
		}		
		
	
	private void selectComponent() {
		
		
	}

	private void deleteComponent() {
	
		
	}

	private void updateComponent() {
		// TODO Auto-generated method stub
		
	}

	private void insertComponent() {
		// TODO Auto-generated method stub
		
	}

	private void selectCar() {
		// TODO Auto-generated method stub
		
	}

	private void deleteCar() {
		// TODO Auto-generated method stub
		
	}

	private void updateCar() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new CarCUIApp();
	}
	
	
	
}
