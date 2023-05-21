package jdbc_team06;

import java.util.List;

public interface CarDAO {
	
	// Car
	
	int insertCar(CarDTO car);
	
	int updateCar(CarDTO car);
	
	int deleteCar(int no);
	
	CarDTO selectCar(int no);
	
	List<CarDTO> selectAllCar();
	
	
	
	//Component
	
	
	int insertComponent(ComponentDTO component);
	
	int updateComponent(ComponentDTO component);
	
	int deleteComponent(String name);
	
	ComponentDTO selectComponent(String componentName);
	
	List<ComponentDTO> selectAllComponent();




	// Component_Detail

	int insertComponentDetail(ComponentDetailDTO componentDetail);

	int updateComponentDetail(ComponentDetailDTO componentDetail);

	List<Object> joinComponentDetail(String compName);




}
