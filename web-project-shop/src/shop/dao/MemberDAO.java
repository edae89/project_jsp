package shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shop.dto.MemberVO;
import util.DBManager;

public class MemberDAO {
	private MemberDAO() {
	}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public void insertUser(MemberVO mVo) {
		String sql = "insert into suser("
				+ "su_id, su_pw, su_name, su_address, su_tel) "
				+ "values(?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getId());
			pstmt.setString(2, mVo.getPw());
			pstmt.setString(3, mVo.getName());
			pstmt.setString(4, mVo.getAddress());
			pstmt.setString(5, mVo.getTel());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	
	
	public int joinIdChk(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int chkFlag = 0;
		try {
			String sql = "select * from suser where su_id=?"; 
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); 
			// 중복된 id가 있으면 리턴 2
			// 중복된 id가 없으면 리턴 1
			while (rs.next()) {
				if(rs.getString("su_id").equals(id)) {
					System.out.println("중복된 id 발견");
					chkFlag = 2;
					return chkFlag;
				}
				
			}
			chkFlag = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
			System.out.println("close확인");
		}
		System.out.println("중복 id 없음");
		return chkFlag;
	}
	
	public int loginChk(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select * from suser where su_id=?";
    	String sql2 = "select * from suser where su_id=? and su_pw=?";
    	int chkFlag = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// id가 있고 pwd가 일치하면 리턴 1
			// id가 있지만 pwd가 불일치하면 리턴 2
			// id가 없으면 리턴 3
			if (rs.next()) {  // id가 있다면
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					System.out.println("아이디 비밀번호 일치");
					chkFlag = 1;
				}else{
					System.out.println("비번틀림");
					chkFlag = 2;
				}
				
			}else {
				System.out.println("등록되지 않은 아이디");
				chkFlag = 3;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("chk"+chkFlag);
		return chkFlag;
	}
	
}
