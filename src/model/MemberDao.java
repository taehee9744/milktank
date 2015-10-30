package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.OracleConfig;


public class MemberDao {
	private static MemberDao instance = new MemberDao();
	public MemberDao(){}
	public static MemberDao getInstance(){
		return instance;
	}
	
	private void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
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
	
	public void memberRegister(MemberVO vo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "insert into users(id, pw, gender, birth, user_no, location, interest, point) values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getBirth());
			pstmt.setInt(5, vo.getUno());
			pstmt.setString(6, vo.getLocal());
			pstmt.setString(7, vo.getInterest());
			pstmt.setInt(8, 5);
			pstmt.executeUpdate();
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(pstmt, con);
		}
	}
	
	public MemberVO memberLogin(String id, String pass) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "select user_no, id, pw from users where id=? and pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}catch(SQLException e){
			e.printStackTrace();	
		}finally{
			closeAll(rs,pstmt,con);
		}
		return vo;
	}
	
	public int memberNum() throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int uno=0;
		try{
			con=DriverManager.getConnection(OracleConfig.URL,OracleConfig.USER,OracleConfig.PASS);
			String sql="select  user_no.nextval from dual";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				uno=rs.getInt(1);
			}
			}finally{
				closeAll(pstmt,con);
			}
		return uno;
	}

}
