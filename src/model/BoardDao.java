package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleConfig;

public class BoardDao {
	private static BoardDao instance = new BoardDao();
	private BoardDao(){}
	public static BoardDao getInstance(){
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
	public int BoardNum() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = 0;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "select paper_no.nextval from dual";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				no = rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return no;
	}
	public int PictureNum() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = 0;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "select pic_no.nextval from dual";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				no = rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return no;
	}
	public void BoardInsert(BoardVO vo, int uno) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "insert into paper(title, p_no, s_no, m_no, content, pic_no, user_no, likes, font_no, public_no) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getP_no());
			pstmt.setInt(3, vo.getSection());
			pstmt.setInt(4, vo.getMagazine());
			pstmt.setString(5, vo.getContent());
			pstmt.setInt(6, vo.getPic_no());
			pstmt.setInt(7, uno);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 1);
			pstmt.setInt(10, 1);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt, con);
		}
	}
	public void PictureInsert(BoardVO vo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "insert into picture(pic_no, pic_path) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPic_no());
			pstmt.setString(2, vo.getPath());
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt, con);
		}
	}
	public ArrayList<BoardVO> getBoardAll() throws SQLException{
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "select p1.p_no, p1.title, p1.content, p1.pic_no, p2.pic_path from paper p1, picture p2 where p1.pic_no = p2.pic_no order by p_no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return list;
	}
//	public BoardVO getBoard(int no) throws SQLException{
//		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		BoardVO vo = null;
//		try{
//			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
//			String sql = "select u.id, p.title, p.content, m.M_NAME, s.s_name, p.m_no, p.s_no "
//					+ "from (select * from paper where p_no=?) p, users u, magazine m, section s "
//					+ "where p.user_no = u.user_no and p.m_no=m.m_no and p.s_no=s.s_no";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			rs = pstmt.executeQuery();
//			if(rs.next()){
//				vo = new BoardVO(no, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
//			}
//		}finally{
//			closeAll(rs, pstmt, con);
//		}
//		return vo;
//	}
	public BoardVO getBoard(int no) throws SQLException{
	      ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      BoardVO vo = null;
	      try{
	         con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
	         String sql = "select user_no, title, content, m_no, s_no, likes from paper where p_no = ?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, no);
	         rs = pstmt.executeQuery();
	         if(rs.next()){
	            vo = new BoardVO(no, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	         }
	      }finally{
	         closeAll(rs, pstmt, con);
	      }
	      return vo;
	   }
	public void deleteBoard(int no)throws SQLException{
		Connection con=null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql= "delete from paper where p_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,no);
			int result=pstmt.executeUpdate();
			System.out.println(result+"개 글 삭제 성공");
		}finally{
			closeAll(pstmt,con);
		}
	}
	public void updateBoard(BoardVO vo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt=null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql ="update paper set title=?, content=?, m_no=?, s_no=?  where p_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getMagazine());
			pstmt.setInt(4, vo.getSection());
			pstmt.setInt(5, vo.getP_no());
			
			int result = pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	public int getLike(int p_no) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int like_num = 0;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "select likes from paper where p_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				like_num = rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return like_num;
	}
	public void updateLike(int p_no, int like_num) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(OracleConfig.URL, OracleConfig.USER, OracleConfig.PASS);
			String sql = "update paper set likes=? where p_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, like_num);
			pstmt.setInt(2, p_no);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt, con);
		}
	}
}
