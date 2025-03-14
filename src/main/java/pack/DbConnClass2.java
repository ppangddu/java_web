package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DbConnClass2 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DbConnClass2() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
		} catch (Exception e) {
			System.out.println("DbConnClass err: ");
		}
	}
	
	public ArrayList<JikwonDto> getDataAll(String bName) {
		ArrayList<JikwonDto> list = new ArrayList<JikwonDto>();
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test", "root", "1234");
			 // 부서명으로 필터링된 데이터만 가져오는 SQL 쿼리
            String sql = "SELECT jikwon.busernum, jikwon.jikwonno, jikwon.jikwonname, jikwon.jikwonjik, jikwon.jikwongen, jikwon.jikwonpay, buser.busername " +
                         "FROM jikwon " +
                         "INNER JOIN buser ON jikwon.busernum = buser.buserno " +
                         "WHERE buser.busername LIKE ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + bName + "%"); // 부서명에 해당하는 데이터 필터링
            rs = pstmt.executeQuery();
			
			//busernum, jikwonno, jikwonname, jikwonjik, jikwongen
			while(rs.next()) {
				JikwonDto dto = new JikwonDto();
				dto.setBusername(rs.getString("busername"));
				dto.setJikwonno(rs.getString("jikwonno"));
				dto.setJikwonname(rs.getString("jikwonname"));
				dto.setJikwonjik(rs.getString("jikwonjik"));
				dto.setJikwongen(rs.getString("jikwongen"));
				dto.setJikwonpay(rs.getString("jikwonpay"));
				list.add(dto);
			}
		}	catch (Exception e) {
					System.out.println("getDataAll err : " + e);
				} finally {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					} catch (Exception e2) {
						
					}
				}
				return list;
	}
}
