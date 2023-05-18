package jdbc_team06;

//이름             널?       유형           
//-------------- -------- ------------ 
//COMPONENT_NAME NOT NULL VARCHAR2(20) 
//PRICE                   NUMBER       
//CAR_DATE                DATE         
//COMPANY                 VARCHAR2(20) 


public class ComponentDTO {
	
	private String name;
	private int price;
	private String carDate;
	private String company;
	
	
	public ComponentDTO() {
		
	}
	
	
	public ComponentDTO(String name, int price, String carDate, String company) {
		this.name = name;
		this.price = price;
		this.carDate = carDate;
		this.company = company;
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCarDate() {
		return carDate;
	}
	public void setCarDate(String carDate) {
		this.carDate = carDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "name = "+name+"  , price = "+ price+"  , carDate = "+ carDate+ "  , company = "+company;
	}
	
}
