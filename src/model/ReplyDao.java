package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleConfig;

public class ReplyDao {
	private static ReplyDao instance = new ReplyDao();
	private ReplyDao(){}
	public static ReplyDao getInstance(){
		return instance;
	}

	private void closeAll(PreparedStatement pstmt, Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		closeAll(pstmt,con);
	}
	
	
	private int makeNum() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
		String sql = "select r_no.nextval from dual";
		int num = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	public int insertReply(ReplyVO vo, int pno, int uno) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = makeNum();
		con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
		String sql = "insert into reply(p_no, r_content, r_no, user_no) values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pno);
			pstmt.setString(2, vo.getR_content());
			pstmt.setInt(3, num);
			pstmt.setInt(4, uno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	public ReplyVO select(int num) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
		String sql = "select r.p_no, r.r_content, r.r_no, r.user_no, u.id from reply r, users u where r.user_no=u.user_no and r_no=?";
		ReplyVO vo = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new ReplyVO(rs.getInt(1), rs.getString(2), 
						rs.getInt(3), rs.getInt(4), rs.getString(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	public ArrayList<ReplyVO> selectAll(int pno) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
		String sql = "select r.r_content, r.r_no, u.id from (select * from reply where p_no=?) r, users u where r.user_no = u.user_no order by r_no";
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ReplyVO(rs.getString(1), rs.getInt(2), rs.getString(3)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int update(ReplyVO vo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
		String sql = "update reply set r_content=? "
				+ "where r_no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getR_content());
			pstmt.setInt(2, vo.getR_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo.getR_no();
	}

	public int delete(int num) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
		String sql = "delete reply where r_no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

}
