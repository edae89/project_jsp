package shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import shop.dto.ShopVO;
import util.DBManager;

public class ShopDAO {
	private ShopDAO(){
	}
	
	private static ShopDAO instance = new ShopDAO();
	
	public static ShopDAO getInstance() {
		return instance;
	}
	
	public List<ShopVO> selectAllGoodsRecent() {
		String sql = "select * from sgoods";
		List<ShopVO> list = new ArrayList<ShopVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ShopVO sVo = new ShopVO();
				sVo.setId(rs.getString("sg_id"));
				sVo.setName(rs.getString("sg_name"));
				sVo.setType(rs.getString("sg_type"));
				sVo.setPrice(rs.getInt("sg_price"));
				sVo.setStock(rs.getInt("sg_stock"));
				sVo.setRcount(rs.getInt("sg_rcount"));
				sVo.setFname1(rs.getString("sg_fname1"));
				sVo.setFname2(rs.getString("sg_fname2"));
				sVo.setFname3(rs.getString("sg_fname3"));
				list.add(sVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	public List<ShopVO> selectAllGoodsBestClicked() {
		String sql = "select * from sgoods order by sg_rcount desc";
		List<ShopVO> list = new ArrayList<ShopVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ShopVO sVo = new ShopVO();
				sVo.setId(rs.getString("sg_id"));
				sVo.setName(rs.getString("sg_name"));
				sVo.setType(rs.getString("sg_type"));
				sVo.setPrice(rs.getInt("sg_price"));
				sVo.setStock(rs.getInt("sg_stock"));
				sVo.setRcount(rs.getInt("sg_rcount"));
				sVo.setFname1(rs.getString("sg_fname1"));
				sVo.setFname2(rs.getString("sg_fname2"));
				sVo.setFname3(rs.getString("sg_fname3"));
				list.add(sVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	public ShopVO selectOneGoodsById(String id) {
		String sql = "select * from sgoods where sg_id = ?";
		ShopVO sVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sVo = new ShopVO();
				sVo.setId(rs.getString("sg_id"));
				sVo.setName(rs.getString("sg_name"));
				sVo.setType(rs.getString("sg_type"));
				sVo.setPrice(rs.getInt("sg_price"));
				sVo.setStock(rs.getInt("sg_stock"));
				sVo.setRcount(rs.getInt("sg_rcount"));
				sVo.setFname1(rs.getString("sg_fname1"));
				sVo.setFname2(rs.getString("sg_fname2"));
				sVo.setFname3(rs.getString("sg_fname3"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return sVo;
	}
	
	
	
	public void insertShop(ShopVO sVo) {
		String sql = "insert into sgoods("
				+ "sg_id, sg_name, sg_type,	sg_price, sg_stock, sg_rcount, sg_fname1, sg_fname2, sg_fname3) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sVo.getId());
			pstmt.setString(2, sVo.getName());
			pstmt.setString(3, sVo.getType());
			pstmt.setInt(4, sVo.getPrice());
			pstmt.setInt(5, sVo.getStock());
			pstmt.setInt(6, sVo.getRcount());
			pstmt.setString(7, sVo.getFname1());
			pstmt.setString(8, sVo.getFname2());
			pstmt.setString(9, sVo.getFname3());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public List<ShopVO> selectGoodsRecentByType(String type) {
		String sql = "select * from sgoods where sg_type = ?";
		ShopVO sVo = null;
		List<ShopVO> list = new ArrayList<ShopVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVo = new ShopVO();
				sVo.setId(rs.getString("sg_id"));
				sVo.setName(rs.getString("sg_name"));
				sVo.setType(rs.getString("sg_type"));
				sVo.setPrice(rs.getInt("sg_price"));
				sVo.setStock(rs.getInt("sg_stock"));
				sVo.setRcount(rs.getInt("sg_rcount"));
				sVo.setFname1(rs.getString("sg_fname1"));
				sVo.setFname2(rs.getString("sg_fname2"));
				sVo.setFname3(rs.getString("sg_fname3"));
				list.add(sVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	public List<ShopVO> selectGoodsBestClickedByType(String type) {
		String sql = "select * from sgoods where sg_type = ? order by sg_rcount desc";
		ShopVO sVo = null;
		List<ShopVO> list = new ArrayList<ShopVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVo = new ShopVO();
				sVo.setId(rs.getString("sg_id"));
				sVo.setName(rs.getString("sg_name"));
				sVo.setType(rs.getString("sg_type"));
				sVo.setPrice(rs.getInt("sg_price"));
				sVo.setStock(rs.getInt("sg_stock"));
				sVo.setRcount(rs.getInt("sg_rcount"));
				sVo.setFname1(rs.getString("sg_fname1"));
				sVo.setFname2(rs.getString("sg_fname2"));
				sVo.setFname3(rs.getString("sg_fname3"));
				list.add(sVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	public void updateReadCount(String id) {
		String sql = "update sgoods set sg_rcount=sg_rcount+1 where sg_id=?";
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
	
//	public List<ShopVO> selectGoodsByColumn(String column) {
//		String sql = "select * from sgoods order by ? desc";
//		ShopVO sVo = null;
//		List<ShopVO> list = new ArrayList<ShopVO>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBManager.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, column);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				sVo = new ShopVO();
//				sVo.setId(rs.getString("sg_id"));
//				sVo.setName(rs.getString("sg_name"));
//				sVo.setType(rs.getString("sg_type"));
//				sVo.setPrice(rs.getInt("sg_price"));
//				sVo.setStock(rs.getInt("sg_stock"));
//				sVo.setRcount(rs.getInt("sg_rcount"));
//				sVo.setFname1(rs.getString("sg_fname1"));
//				sVo.setFname2(rs.getString("sg_fname2"));
//				sVo.setFname3(rs.getString("sg_fname3"));
//				list.add(sVo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//		return list;
//	}
	
	

//	public List<ShopVO> selectGoodsByTypeColumn(String type, String column) {
//		String sql = "select * from sgoods where sg_type=? order by ? desc";
//		ShopVO sVo = null;
//		List<ShopVO> list = new ArrayList<ShopVO>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBManager.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, type);
//			pstmt.setString(2, column);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				sVo = new ShopVO();
//				sVo.setId(rs.getString("sg_id"));
//				sVo.setName(rs.getString("sg_name"));
//				sVo.setType(rs.getString("sg_type"));
//				sVo.setPrice(rs.getInt("sg_price"));
//				sVo.setStock(rs.getInt("sg_stock"));
//				sVo.setRcount(rs.getInt("sg_rcount"));
//				sVo.setFname1(rs.getString("sg_fname1"));
//				sVo.setFname2(rs.getString("sg_fname2"));
//				sVo.setFname3(rs.getString("sg_fname3"));
//				list.add(sVo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//		return list;
//	}
	
	
	

	
	
}
