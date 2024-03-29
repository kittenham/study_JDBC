package jdbc_team06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.sql.rowset.serial.SQLOutputImpl;


public class CarCUIApp {
private BufferedReader in;

	public CarCUIApp() {

		in = new BufferedReader(new InputStreamReader(System.in));

		String Carmenu[] = new String[] { "1.차 정보 추가", "2.차 정보 변경", "3.차 정보 삭제", "4.차 정보 검색","5.종료", "6.되돌아가기"};
		String Component[] = new String[] {"1.부품 추가", "2.부품 변경", "3.부품 삭제", "4.부품 조회", "5.종료", "6.되돌아가기"};


		System.out.println("********** 카센타에 들어온 차 정보 검색 프로그램 **********");


		try {
			while(true) {

				System.out.println("메뉴를 선택하세요");
				System.out.println("1. 차 2. 부품");

				int menuSelect = Integer.parseInt(in.readLine());


				if(menuSelect == 1) {    // 메뉴 선택 1번


					while(true) {

						for (String car : Carmenu) {
							System.out.println(car);
						}

						int carMenuSelect = Integer.parseInt(in.readLine());

						if (carMenuSelect < 1 || carMenuSelect> 6) {
							System.out.println("*** [에러]1~6번 메뉴를 골라주세요 ***");
							continue;
						}

						switch(carMenuSelect) {

							case 1: insertCar(); break;     //	1.차종 추가
							case 2: updateCar(); break;    // 2.차 정보 변경
							case 3: deleteCar(); break;    // 3.차 정보 삭제
							case 4: selectCar(); break;    // 4.차 정보 검색
							case 5:  System.out.println("프로그램 종료"); System.exit(1);
						}
						break;
					}

				} else if(menuSelect == 2) {


					while (true) {

						for (String comp : Component) {
							System.out.println(comp);
						}
						System.out.println();
						System.out.println("메뉴를 선택하세요");

						int compMenuSelect = Integer.parseInt(in.readLine());

						if (compMenuSelect < 1 || compMenuSelect > 6) {
							System.out.println("*** [에러]1~6번 메뉴를 골라주세요 ***");
							continue;
						}

						switch (compMenuSelect) {

							case 1:
								insertComponent();
								break;    //	1.부품종 추가
							case 2:
								updateComponent();
								break;    //  2. 부품정보 변경
							case 3:
								deleteComponent();
								break;    //  3. 부품 삭제
							case 4:
								selectComponent();
								break;
							case 5:
								System.out.println("프로그램 종료");
								System.exit(1);//  4. 부품 검색
						}

						break;
					}


				}
			}
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

				String ownerReg = "^[0-9][0-9][0-9][0-9]$";
				if(!Pattern.matches(ownerReg, no)) {
					System.out.println("차량번호는 0~9로 이루어진 네자리수이하로 입력해주세요");
					continue;
				}

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
				String ownerReg = "^[가-힣]{1,5}$";

				if(!Pattern.matches(ownerReg, car_name)) {
					System.out.println("[입력오류] 차 이름은 1~5글자 문자로 입력해 주세요.");
					continue;
				}

				break;
			}

			while(true) {

				System.out.println("차주이름을 입력해주세요");
				owner_name= in.readLine();
				//정규표현식

				String ownerReg = "^[가-힣]{2,5}$";
				if(!Pattern.matches(ownerReg, owner_name)) {
					System.out.println("[입력오류] 차주 이름은 2~5글자 문자로 입력해 주세요.");
					continue;
				}
				break;
			}

			while(true) {

				System.out.println("필요 부품을 입력해주세요");
				List<ComponentDTO> componentList = DAOImpl.getDaoImpl().selectAllComponent();
				System.out.println("**************** 현재 입고된 부품 리스트 ****************");
				for (ComponentDTO component : componentList) {
					System.out.println(component);
				}

				nec_component = in.readLine();
				//정규표현식

				String compReg = "^[가-힣]{1,7}$";
				if(!Pattern.matches(compReg, nec_component)) {
					System.out.println("[입력오류] 부품명은 1~7자리 문자형식으로 입력해 주세요.");
					continue;
				}

				ComponentDTO component=DAOImpl.getDaoImpl().selectComponent(nec_component);

				if(component==null) {
					System.out.println("다시 입력해주세요. 없는 부품입니다.");
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
		System.out.println();

	}


	private void selectCar() {

		try {
			int no;

			System.out.println("*********************차 전체 정보**************************");


			List<CarDTO> cars = DAOImpl.getDaoImpl().selectAllCar();
			for (CarDTO car : cars) {
				System.out.println(car);
			}

			while(true) {


				System.out.println();


				System.out.println();

				System.out.println("차 번호를 입력해주세요");

				String user = in.readLine();

				if(user == null || user.equals("")) {
					System.out.println("조회하실 차량 번호를 입력해주세요");
					continue;
				}

				no = Integer.parseInt(user);

				CarDTO car = DAOImpl.getDaoImpl().selectCar(no);

				System.out.println("차량 정보 = : " + car.toString());
				System.out.println();

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void deleteCar() {
		int no;

		while(true) {
			try {
				System.out.println("*** 차 번호를 입력해주세요  ***");

				String user = in.readLine();
				String noReg="^[0-9]|[0-9][0-9]$";
				// 정규표현식
				if(!Pattern.matches(noReg, user)) {
					System.out.println("[에러!]차량번호 형식이 틀렸습니다. 0~99로 입력해주세요");
					System.out.println();
					continue;
				}
				no = Integer.parseInt(user);
				CarDTO car = DAOImpl.getDaoImpl().selectCar(no);
				if(car == null) {
					System.out.println("테이블에 없는 차종명입니다.");
					System.out.println();
					continue;
				}
				int rows = DAOImpl.getDaoImpl().deleteCar(no);

				System.out.println(rows + "개의 자동차가 삭제되었습니다");
				System.out.println();

				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void updateComponent() {
		String name;
		ComponentDTO cmdto;
		try {

			while(true) {
				System.out.println("정보를 변경할 부품이름을 작성해주세요");
				System.out.println();
				System.out.println("********** 현재 부품 목록 ***********");
				System.out.println();
				List<ComponentDTO> componentList = DAOImpl.getDaoImpl().selectAllComponent();
				for (ComponentDTO component : componentList) {
					System.out.println(component);
				}

				name=in.readLine();

				if(name.equals("")||name==null) {
					System.out.println("값이 입력되지 않았습니다. 다시 입력해주세요 ");
					System.out.println();
					continue;
				}

				cmdto=DAOImpl.getDaoImpl().selectComponent(name);

				if(cmdto==null) {
					System.out.println("[Error] !!!! 등록되지 않은 부품입니다. 부품이름을 다시 확인해서 입력하세요!!!");
					System.out.println();
					continue;
				}
				break;
			}


			String price;

			System.out.println("변경을 원하지 않을 경우 enter를 누르세요 ");

			while(true) {
				System.out.println("가격을 입력해주세요");
				price=in.readLine();
				//정규표현식

				String regx = "^[0-9]{0,5}$";
				if(price!=null && !price.equals("") && !Pattern.matches(regx, price)) {
					break;
				}


				break;
			}

			String date;

			while(true) {
				System.out.println("제조일자를 YYYY-MM-DD 형식으로 입력해주세요 : ");
				System.out.println("변경을 원하지 않을 경우 enter를 누르세요 ");
				date=in.readLine();

				String regx = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";

				if(date!=null && !date.equals("") && !Pattern.matches(regx, date)) {
					break;
				}


				break;
			}


			String company;

			while(true) {
				System.out.println("회사명을 입력해주세요 : ");
				System.out.println("변경을 원하지 않을 경우 enter를 누르세요 ");

				company=in.readLine();

				String regx = "^[가-힣]{1,7}$";

				//정규표현식인데 null값이나 ""값은 통과

				if(company != null && company.equals("") && (!Pattern.matches(regx, company))) {
					break; // 엔터키
				}


				break;
			}


			cmdto.setName(name);
			if(price != null && !price.equals("")) cmdto.setPrice(Integer.parseInt(price));
			if(date != null && !date.equals("")) cmdto.setCarDate(date);
			if(company != null && !company.equals("")) cmdto.setCompany(company);

			int stl=DAOImpl.getDaoImpl().updateComponent(cmdto);

			System.out.println("변경정보 >>  " + cmdto);

			System.out.println(stl+"개를 수정하였습니다.");

			System.out.println();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertComponent() {
		String component_Name ="";
		int price = 0;
		String car_date="";
		String company ="";

		try {

			while(true) { //부품이름
				System.out.println("부품 이름을 입력하세요.");
				component_Name=in.readLine();

				if(component_Name==null||component_Name.equals("")) {
					continue;
				}

				String compReg = "^[가-힣]{1,7}$";
				if(!Pattern.matches(compReg, component_Name)) {
					System.out.println("[입력오류] 부품명은 문자로 입력해 주세요.");
					continue;
				}
				ComponentDTO compt=DAOImpl.getDaoImpl().selectComponent(component_Name);

				if(compt!=null) {
					System.out.println("이미 있는 부품입니다.");
					continue;
				}
				break;
			}


			while(true) { //부품 가격
				System.out.println("부품 가격을 입력하세요.");
				String compPrice = in.readLine();

				if(compPrice==null||compPrice.equals("")) {
					continue;
				}
				String compPriceReg="^[0-9]*$";
				if(!Pattern.matches(compPriceReg, compPrice)) {
					System.out.println("[입력오류]가격은 숫자로만 입력해 주세요.");
					continue;
				}
				price=Integer.parseInt(compPrice);

				break;
			}

			while(true) { //부품 입고 날짜
				System.out.println("부품 입고일을 입력하세요.(YYYY-MM-DD 형식으로 입력.)");
				car_date=in.readLine();

				if(car_date==null||car_date.equals("")) {
					continue;
				}

				String dateReg="(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
				if(!Pattern.matches(dateReg, car_date)) {
					System.out.println("[입력오류] 정확한 날짜를 입력해주세요.");
					continue;
				}
				break;
			}

			while(true) { //회사 이름
				System.out.println("부품 회사명을 입력하세요.");
				company=in.readLine();

				if(company==null||company.equals("")) {
					continue;
				}

				String compompanyReg = "^[가-힣]{2,7}$";
				if(!Pattern.matches(compompanyReg, company)) {
					System.out.println("[입력오류] 회사이름은 문자로 입력해 주세요.");
					continue;
				}
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		ComponentDTO compDTO = new ComponentDTO(component_Name, price, car_date, company);
		int rows = DAOImpl.getDaoImpl().insertComponent(compDTO);
		System.out.println(rows+"개의 부품 정보가 삽입되었습니다.");
		System.out.println();
	}


	private void selectComponent() {
		String name="";
		String detail = "";

		try {

			System.out.println("************************부품 전체 정보**************************");

			List<ComponentDTO> components = DAOImpl.getDaoImpl().selectAllComponent();

			for (ComponentDTO component : components) {
				System.out.println(component);
			}

			System.out.println();

			while(true) {
				System.out.println("조회하실 부품을 입력해주세요 : ");
				name=in.readLine();
				if(name==null || name.equals("")) {
					System.out.println("값을 입력해주세요 : ");
					continue;
				}
				//정규표현식
				ComponentDTO cmdto=DAOImpl.getDaoImpl().selectComponent(name);
				if(cmdto==null) {
					System.out.println("해당 부품명은 없는 부품입니다. 다시 입력해주세요");
					continue;
				}
				System.out.println(cmdto.toString()); // 선택한 부품 보여주기
				System.out.println();
				System.out.println("<<<해당 상품의 상세정보를 보고싶다면 Y를 눌러주세요 아니시라면 아무키나 눌러주세요>>>");
				detail = in.readLine();

				if(detail.equals("Y") || detail.equals("y") ) {
					System.out.println("*****************<< " + name + " >>의 전체 정보 ****************** ");
					List<Object> results = DAOImpl.getDaoImpl().joinComponentDetail(name);
					for (Object result: results) {
						System.out.println(result);
						System.out.println();
					}
				}

				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void deleteComponent() {


		String componentName="";
		try {
			while(true) {
				System.out.println("삭제하실 부품의 이름을 입력하세요 : ");
				componentName = in.readLine();
				//정규표현식
				if(componentName==null || componentName.equals("")) {
					System.out.println("값을 입력해주세요");
					continue;
				}

				ComponentDTO cmdto=DAOImpl.getDaoImpl().selectComponent(componentName);


				if(cmdto==null) {
					System.out.println("없는 부품명입니다. 다시 입력해주세요");
					continue;
				}
				int stl=DAOImpl.getDaoImpl().deleteComponent(componentName);

				System.out.println(stl+"개의 부품목록이 삭제되었습니다.");
				System.out.println();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private void updateCar() {

		try {
			int no;

			while(true) {
				List<CarDTO> cars = DAOImpl.getDaoImpl().selectAllCar();
				System.out.println("************ 현재 입고된 차량 ************");
				System.out.println();
				for (CarDTO car : cars) {
					System.out.println(car);
					System.out.println();
				}


				System.out.println("*** 변경할 차 번호를 입력해주세요 ***");

				String user = in.readLine();

				String noReg = "^[0-9]*$";

				if(user == null && user.equals("") && (!Pattern.matches(noReg, user))) {
					System.out.println("***차 번호를 입력해주세요***");
					continue;
				}

				// 정규표현식

				no = Integer.parseInt(user);


				break;
			}

			CarDTO car = DAOImpl.getDaoImpl().selectCar(no);

			if(car==null) {
				System.out.println("입력하신 차번호에 맞는 차가 없습니다");
				return;
			}

			System.out.println("차 정보 >>>>>>> {" + car + " }");
			System.out.println();

			String carName;

			while(true) {

				System.out.println("####### 변경할 차 이름을 입력해주세요(**변경을 원하지 않을시 Enter를 입력해주세요**) #######");


				carName = in.readLine();

				// 정규표현식
				String carReg = "^[가-힣]{2,5}$";


				if(carName!= null && !carName.equals("") && (!Pattern.matches(carReg, carName))) {
					System.out.println("입력형식을 확인해주세요 ");
					continue;
				}

				break;
			}

			String ownerName;

			while(true) {

				System.out.println("변경할 차 소유주를 입력해주세요");

				ownerName = in.readLine();

				String ownerReg = "^[가-힣]{2,5}$";


				if(ownerName != null && !ownerName.equals("") && (!Pattern.matches(ownerReg, ownerName))) {
					System.out.println("***차 번호를 입력해주세요***");
					continue;
				}

				break;
			}

			String nec_comp;

			while(true) {
				System.out.println("변경할 차의 필요한 부품을 입력해주세요(*** 변경을 원하지 않을시 Enter를 입력해주세요 ***)");

				nec_comp = in.readLine();

				String compReg = "^[가-힣]{2,7}$";

				if(nec_comp!= null && !nec_comp.equals("") && !Pattern.matches(compReg, nec_comp)){
					continue;
				}

				break;
			}

			car.setNo(no);

			if(carName != null && !carName.equals("")) car.setName(carName);
			if(ownerName != null && !ownerName.equals("")) car.setOwner_name(ownerName);
			if(nec_comp != null && !nec_comp.equals("")) car.setNec_component(nec_comp);

			int rows = DAOImpl.getDaoImpl().updateCar(car);

			System.out.println("선택하신 차종 정보가" + rows + "개 변경되었습니다");
			System.out.println();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new CarCUIApp();
	}
}