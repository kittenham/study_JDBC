package jdbc_team06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl extends DBConnection implements CarDAO{
	
	private static DAOImpl instance = new DAOImpl();
	
	private DAOImpl() {
		
	}
	
	public static DAOImpl getDaoImpl() {
		return instance;
	}

	
	
	
	@Override
	public int insertCar(CarDTO car) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rows = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into car values(?,?,?,?)";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, car.getNo());
			pstmt.setString(2, car.getName());
			pstmt.setString(3, car.getOwner_name());
			pstmt.setString(4, car.getNec_component());
		
			rows = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
			
		}
		
		return rows;
		
		
	}

	@Override
	public int updateCar(CarDTO car) {
			Connection con=null;
			PreparedStatement pstmt=null;
			int rows=0;
			try {
				con=getConnection();
				
				String sql="update CAR set CAR_NAME=?, OWNER_NAME=? , NEC_COMPONENT=?  where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, car.getName());
				pstmt.setString(2, car.getOwner_name());
				pstmt.setString(3, car.getNec_component());
				pstmt.setInt(4, car.getNo());

				rows=pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("[에러]updateCar() 메소드의 SQL 오류 = "+e.getMessage());
			} finally {
				close(con, pstmt);
			}
			return rows;
		}

	@Override
	public int deleteCar(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows=0;
		
		try {
			con=getConnection();
			
			String sql = "delete from car where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rows=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[에러] DeleteCar() 메소드의 SQL 오류 = "+e.getMessage());
		
		}finally {
			close(con, pstmt);
		}
		return rows;
	}

	@Override
	public CarDTO selectCar(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CarDTO car = null;
		try {
			con=getConnection();
			String sql="select * from CAR where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//검색행이 있는 경우
				car=new CarDTO();
				car.setNo(rs.getInt("no"));
				car.setName(rs.getString("CAR_NAME"));
				car.setOwner_name(rs.getString("OWNER_NAME"));
				car.setNec_component(rs.getString("NEC_COMPONENT"));
			}
		}catch (SQLException e) {
			System.out.println("[에러] selectCar() 메소드의 SQL 오류 = " + e.getMessage());
		}finally {
			close(con, pstmt, rs);
		}
		return car;
	}
	
	
//	이름            널?       유형           
//			------------- -------- ------------ 
//			NO            NOT NULL NUMBER(2)    
//			CAR_NAME               VARCHAR2(10) 
//			OWNER_NAME             VARCHAR2(10) 
//			NEC_COMPONENT          VARCHAR2(20) 
//			*/
	@Override
	public List<CarDTO> selectAllCar() {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<CarDTO> allCar = new ArrayList<>();
		
		try {
			con = getConnection();
			
			String sql = "select * from car order by no";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarDTO car = new CarDTO();
				car.setNo(rs.getInt("no"));
				car.setName(rs.getString("CAR_NAME"));
				car.setOwner_name(rs.getString("owner_name"));
				car.setNec_component(rs.getString("nec_component"));
				
				allCar.add(car);
			}
			
			
		}catch(SQLException e) {
			System.out.println("[에러] selecAllCar() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
			
		}
		
		return allCar;
	}

	@Override
	public int insertComponent(ComponentDTO component) {
			Connection con = null;
			PreparedStatement pstmt=null;
			int rows=0;
			try {
				con = getConnection();
				
				String sql = "insert into component values(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, component.getName());
				pstmt.setInt(2, component.getPrice());
				pstmt.setString(3, component.getCarDate());
				pstmt.setString(4, component.getCompany());
				
				rows = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("[에러] insertComponent()");
			}finally {
				close(con, pstmt);
			}
			return rows;
		}

	@Override
	public int updateComponent(ComponentDTO component) {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con = getConnection();
			
				String sql = "update component set price = ? , car_date = ? , company = ? where component_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, component.getPrice());
			pstmt.setString(2, component.getCarDate().substring(0,10));
			pstmt.setString(3, component.getCompany());
			pstmt.setString(4, component.getName());


			
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[에러] updateComponent()");
		}finally {
			close(con, pstmt);
		}
		return rows;
	}

	@Override
	public int deleteComponent(String name) {
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		int rows = 0;
		try {
			conn = getConnection();
			
			String sql = "delete from component where COMPONENT_NAME =?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			
			rows = pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			System.out.println("######  [Error!] 이미 다른차량에 예약된 부품입니다!  #######");
			System.out.println();
		}finally {
			close(conn, pstmt);
		}
		
		return rows;
	}
	
	
	@Override
	public ComponentDTO selectComponent(String Name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ComponentDTO cp=null;

		try {
			con=getConnection();
			String sql = "select * from component where COMPONENT_NAME=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Name);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				cp= new ComponentDTO();
				cp.setName(rs.getString("COMPONENT_NAME"));
				cp.setPrice(rs.getInt("price"));
				cp.setCarDate(rs.getString("car_Date"));
				cp.setCompany(rs.getString("company"));

			}
			
		} catch (SQLException e) {
			System.out.println("[에러] selectComponent()");
		} finally {
			close(con, pstmt, rs);
		}
		return cp;
	}
	
	
	

	
	@Override
	public List<ComponentDTO> selectAllComponent() {
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ComponentDTO> carlist=new ArrayList<>();
		try {
			con = getConnection();
			String sql="SELECT * FROM Component";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ComponentDTO cmt=new ComponentDTO();
				cmt.setName(rs.getString("COMPONENT_NAME"));
				cmt.setPrice(rs.getInt("price"));
				cmt.setCarDate(rs.getString("car_Date"));
				cmt.setCompany(rs.getString("company"));
				
				carlist.add(cmt);
			}			
			
		}catch (SQLException e) {
			System.out.println("[에러] SelectAllComponent()");
		}finally {
			close(con, pstmt, rs);
			}
		
		return carlist;
	}

	@Override
	public int insertComponentDetail(ComponentDetailDTO componentDetail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			conn = getConnection();

			String sql = "insert into component_detail values(?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, componentDetail.getComp_Name());
			pstmt.setString(2, componentDetail.getDetail());

		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}

		return rows;
	}

	@Override
	public int updateComponentDetail(ComponentDetailDTO componentDetail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			conn = getConnection();

			String sql = "update component_detail set detail = ? where comp_name = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, componentDetail.getDetail());
			pstmt.setString(2, componentDetail.getComp_Name());


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return rows;
	}

	@Override
	public List<Object> joinComponentDetail(String compName) { // 부품의 상세정보
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		List<Object> list = new ArrayList<>();



		try {
			conn = getConnection();

			String sql = "select c.car_date,c.price,c.company, cd.detail from component c join component_detail cd on c.component_name = cd.comp_name where component_name = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, compName);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ComponentDTO cp = new ComponentDTO();
				ComponentDetailDTO cpdt = new ComponentDetailDTO();


				cp.setCarDate(rs.getString("car_date").substring(0,10));
				cp.setPrice(rs.getInt("price"));
				cp.setCompany(rs.getString("company"));
				cpdt.setDetail(rs.getString("detail"));

				list.add("들어온 날짜 = " + cp.getCarDate() );
				list.add(cp.getPrice() + "원");
				list.add("제조회사 = " + cp.getCompany());
				list.add("상세 정보 = " + cpdt.getDetail());
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}


}
