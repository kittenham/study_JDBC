package jdbc_team06;

public class ComponentDetailDTO {

    private String comp_Name;
    private String detail;

    public ComponentDetailDTO(String comp_Name, String detail) {
        this.comp_Name = comp_Name;
        this.detail = detail;
    }

    public ComponentDetailDTO() {

    }

    public String getComp_Name() {
        return comp_Name;
    }

    public void setComp_Name(String comp_Name) {
        this.comp_Name = comp_Name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return " 부품 이름 = " + comp_Name + " 부품 상제 정보 >> " + detail;
    }
}
