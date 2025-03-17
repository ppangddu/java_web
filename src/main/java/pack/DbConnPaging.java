package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DbConnPaging { // DB 처리용 Business logic
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	int recordTotal = 0; // 행(record)총 갯수
	int pageSize = 5; // 페이지 당 출력 행 수
	int totalPage = 0; // 전체 페이지 수
	
	
	
	public DbConnPaging() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DbConnClass err : ");
		}
	}
	
	/*
	public ArrayList<SangpumDto> getDataAll(String pa) { // 해당 페이지의 자료만 반환
		ArrayList<SangpumDto> list = new ArrayList<SangpumDto>();
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test", "root", "1234");
			String sql = "select * from sangdata order by code desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int startNum = (Integer.parseInt(pa) - 1) * pageSize;
			for (int p = 0; p < startNum; p++) {
				rs.next(); // 레코드 포인터 위치 지정 pa:1(rp:0), pa:2(rp:5), pa:3(rp:10),...
			}
			
			
			int i = 0;
			while(rs.next() && i < pageSize) {
				SangpumDto dto = new SangpumDto();
				dto.setCode(rs.getString("code"));
				dto.setSang(rs.getString("sang"));
				dto.setSu(rs.getString("su"));
				dto.setDan(rs.getString("dan"));
				list.add(dto); // 끝나면 컬렉션에 넣는다. 만약에 세션이 있으면 세션에도 넣을 수 있다.
				i++;
			}
		} catch (Exception e) {
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
	*/
	
	
	
	public ArrayList<SangpumDto> getDataAll(String pa) { // 위 코드를 람다식 사용
		ArrayList<SangpumDto> list = new ArrayList<SangpumDto>();
		String url = "jdbc:mariadb://127.0.0.1:3306/test";
		String sql = "select * from sangdata order by code desc";
		//try - with - resource 구문 사용 (finally가 필요 없음, try가 끝나면 자동으로 close() 해준다.)
		try (Connection conn = DriverManager.getConnection(url, "root", "1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				int startNum = (Integer.parseInt(pa) - 1) * pageSize + 1;
				rs.absolute(startNum - 1); // 레코드 포인터를 시작 위치로 이동
				
				list = Stream.iterate(1, i -> i + 1) // 1부터 시작하는 스트림 생성
						.limit(pageSize) // 스트림의 크기를 pageSize만큼 제한
						.map(i -> {
							try {
								if(rs.next()) { // rs.next()로 레코드를 이동하여 SangpumDto에 값 기억
									SangpumDto dto = new SangpumDto();
									dto.setCode(rs.getString("code"));
									dto.setSang(rs.getString("sang"));
									dto.setSu(rs.getString("su"));
									dto.setDan(rs.getString("dan"));
									return dto;
								}
							} catch (Exception e) {
								System.out.println("getDataAll map err : " + e);
							}
							return null;
						}) 
						.filter(Objects::nonNull) // null이 아닌 객체만 필터링
						.collect(Collectors.toCollection(ArrayList::new));
						// collect() 스트림 결과를 ArrayList로 수집
				
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
		}
		return list;
	}
	
	public int prepareTotalPage() {
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test", "root", "1234");
			String sql = "select count(*) from sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) recordTotal= rs.getInt(1); // 전체 레코드 수
			
			// 전체 페이지 수
			totalPage = recordTotal / pageSize; // 몫 구함
			if (recordTotal % pageSize != 0) {
				totalPage += 1; // 자투리 계산
			}
			System.out.println("전체 페이지 수: " + totalPage);
			
			
		} catch (Exception e) {
			System.out.println("prepareTotalPage err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
			
		}
		return totalPage;
	}
	
	public void sangpumInsert(SangpumFormBean formBean) {
		// System.out.println(formBean.getSang() + " " + formBean.getSu());
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test", "root", "1234");
			
			// 신상 code 구하기
			pstmt = conn.prepareStatement("select max(code) from sangdata");
			rs = pstmt.executeQuery(); // select만 executeQuery임
			rs.next();
			int maxCode = rs.getInt(1);
			// System.out.println("가장 큰 code: " + maxCode);
			
			// 신상 등록
			String sql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxCode + 1);
			pstmt.setString(2, formBean.getSang());
			pstmt.setString(3, formBean.getSu());
			pstmt.setString(4, formBean.getDan());
			pstmt.executeUpdate(); // 반환값을 받아 처리할 수 있다.
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		} catch (Exception e2) {
			
		}
	}
}
}
