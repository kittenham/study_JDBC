package jdbc_team06;

/*
이름            널?       유형           
------------- -------- ------------ 
NO            NOT NULL NUMBER(2)    
CAR_NAME               VARCHAR2(10) 
OWNER_NAME             VARCHAR2(10) 
NEC_COMPONENT          VARCHAR2(20) 
*/
public class CarDTO {
	
	private int no;
	private String name;
	private String owner_name;
	private String nec_component;
	
	public CarDTO() {
		
	}
	
	
	public CarDTO(int no, String name, String owner_name, String nec_component) {
		this.no = no;
		this.name = name;
		this.owner_name = owner_name;
		this.nec_component = nec_component;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getNec_component() {
		return nec_component;
	}
	public void setNec_component(String nec_component) {
		this.nec_component = nec_component;
	}


	@Override
	public String toString() {
		return " no=" + no + ", name=" + name + ", owner_name=" + owner_name + ", nec_component=" + nec_component;
	}
	
	
	
	
}
