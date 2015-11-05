package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public boolean idcheck(String id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = true;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "select id from users where id=?";
			System.out.println("memberdao sql:"+sql);
			System.out.println("memberdao:"+id);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("memberdao rs:"+rs);
			while(rs.next()){
				check = false;
			}
			
		}finally{
			closeAll(rs, pstmt, con);
		}
		return check;
	}
	public void requestFriend(String r_id, String s_id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql ="insert into requestfriend (s_id,r_id)values (?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, s_id);
			pstmt.setString(2, r_id);
			pstmt.executeQuery();
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	public boolean friendCheck(String r_id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean fcheck=false;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql ="select * from requestfriend where r_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, r_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				fcheck=true;
			}else{
				fcheck=false;
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return fcheck;
	}
	
	public ArrayList<MemberVO> friendconfirm(String r_id) throws SQLException{
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo =null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql ="select s_id from requestfriend where r_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, r_id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(new MemberVO(rs.getString(1)));
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		
		return list;
	}
	public void friendapprove(String s_id, String r_id)throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql="delete from requestfriend where s_id=? and r_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, s_id);
			pstmt.setString(2, r_id);
			pstmt.executeQuery();
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	@SuppressWarnings("resource")
	public void addfriend(String s_id, String r_id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql="insert into friend (u_id,f_id) values(?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, s_id);
			pstmt.setString(2, r_id);
			pstmt.executeQuery();
			
			String sql2="insert into friend(u_id,f_id) values(?,?)";
			pstmt=con.prepareStatement(sql2);
			pstmt.setString(1, r_id);
			pstmt.setString(2, s_id);
			pstmt.executeQuery();
			
			
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	public void deletefriendrequest(String s_id, String r_id)throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql="delete from requestfriend where s_id=? and r_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, s_id);
			pstmt.setString(2, r_id);
			pstmt.executeQuery();
		}finally{
			closeAll(pstmt,con);
		}
	}
	public void friendreject(String s_id, String r_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql ="delete from requestfriend where s_id=? and r_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, s_id);
			pstmt.setString(2, r_id);
			pstmt.executeQuery();
		}finally{
			closeAll(pstmt,con);
		}
		
	}

}
