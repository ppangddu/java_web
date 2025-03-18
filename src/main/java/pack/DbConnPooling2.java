package pack;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DbConnPooling2 {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private DataSource ds;

    public DbConnPooling2() {
        // DBCP 사용
        try {
            // JNDI : Java Naming end Directory Interface
            // 서비스가 제공하는 데이터 및 객체 참조(lookup)하기 위한 API
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
        } catch (Exception e) {
            System.out.println("DbConnPooling2 err" + e);
        }
    }

    public ArrayList<GuestDto> getDataAll() {
        ArrayList<GuestDto> list = new ArrayList<GuestDto>();
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement("select * from guestbook");
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	GuestDto dto = new GuestDto();
                dto.setCode(rs.getString("code"));
                dto.setWriter(rs.getString("writer"));
                dto.setTitle(rs.getString("title"));
                dto.setContents(rs.getString("contents"));
                list.add(dto);
            }

        } catch (Exception e) {
            System.out.println("DbConnPooling2 err" + e);
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
    
    public boolean insertData(GuestFormBean bean) {
    	boolean b = false;
    	try {
    		conn = ds.getConnection();
    		
    		//신상 code 구하기
    		String sql = "select max(code) as max from guestbook";
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		int maxCode = 0;
    		if (rs.next()) {
    			maxCode = rs.getInt("max");
    		}
    		maxCode += 1;
    		
    		// 추가
    		sql = "insert into guestbook values(?,?,?,?)";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, maxCode);
    		pstmt.setString(2, bean.getWriter());
    		pstmt.setString(3, bean.getTitle());
    		pstmt.setString(4, bean.getContents());
    		int result = pstmt.executeUpdate();
    		if (result == 1) {
    			b = true;
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
    	return b;
    }
    
    public GuestDto updateDataRead(String code) {
    	GuestDto dto = null;
    	
    	String sql = "select * from guestbook where code=?";
    	// try - with - resources
    	try (Connection conn = ds.getConnection();
    		 PreparedStatement pstmt = conn.prepareStatement(sql)
    	    ) {
    	     pstmt.setString(1, code); // 첫번째 ? 에다가 code를 준다. String code 받으니까 setString	 
    	     ResultSet rs = pstmt.executeQuery();
    	     if(rs.next()) {
    	    	 dto = new GuestDto();
    	    	 dto.setCode(rs.getString("code"));
    	    	 dto.setWriter(rs.getString("writer"));
    	    	 dto.setTitle(rs.getString("title"));
    	    	 dto.setContents(rs.getString("contents"));
    	     }
		} catch (Exception e) {
			System.out.println("updateDataRead err : " + e);
		}
    	
    	return dto;
    }
    
    public boolean updateData(GuestFormBean bean) {
    	boolean b = false;
    	
    	String sql = "update guestbook set writer=?, title=?, contents=? where code=?";
    	
    	try(
    			Connection conn = ds.getConnection();
    			PreparedStatement pstmt = conn.prepareStatement(sql);
    			
    	   ) {
    		pstmt.setString(1, bean.getWriter()); // db에서 정한 자료형 때문에 setString으로 쓰는 것이 아니다.
    		pstmt.setString(2, bean.getTitle());
    		pstmt.setString(3, bean.getContents());
    		pstmt.setString(4, bean.getCode());
    		if (pstmt.executeUpdate() > 0) b = true;
    		
    	} catch(Exception e) {
    		System.out.println("updateData err : " + e.getMessage());
    	}
    	
    	return b;
    }
    
    public boolean deleteData(String code) {
    	boolean b = false;
    	String sql = "delete from guestbook where code=?";
    	try(
    			Connection conn = ds.getConnection();
    			PreparedStatement pstmt = conn.prepareStatement(sql);) {
    				pstmt.setString(1, code);
    				if (pstmt.executeUpdate() > 0) b = true;
    	} catch(Exception e) {
    		System.out.println("updateData err : " + e.getMessage());
    	}
    	return b;
    }
}
