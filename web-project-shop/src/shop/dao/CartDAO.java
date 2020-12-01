package shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.dto.CartVO;
import shop.dto.CartViewVO;
import util.DBManager;

public class CartDAO {
	private CartDAO(){
	}
	
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}

	//장바구니 추가
	public void insertToCart(CartVO cVo) {
		String sql = "insert into scart("
				+ "sc_num, su_id, sg_id, sc_quantity) "
				+ "values(sc_num_seq.nextval, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cVo.getUserId());
			pstmt.setString(2, cVo.getGoodsId());
			pstmt.setInt(3, cVo.getQuantity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}

	
	//장바구니 목록 조회
	public List<CartViewVO> selectAllById(String nowid) {
		String sql = "select * from view_cartlist where su_id=?";
		List<CartViewVO> list = new ArrayList<CartViewVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nowid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartViewVO cvVo = new CartViewVO();
				cvVo.setNum(rs.getInt("sc_num"));
				cvVo.setId(rs.getString("su_id"));
				cvVo.setFname1(rs.getString("sg_fname1"));
				cvVo.setName(rs.getString("sg_name"));
				cvVo.setPrice(rs.getInt("sg_price"));
				cvVo.setQuantity(rs.getInt("sc_quantity"));
				list.add(cvVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
		
	}
	
	//장바구니 삭제
	public void deleteOneFromCartByNum(int num) {
		String sql = "delete from scart where sc_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	public void deleteAllFromCartById(String id) {
		String sql = "delete from scart where su_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	

}
