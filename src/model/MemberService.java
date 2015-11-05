package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {
	private static MemberService instance = new MemberService();
	private MemberService(){}
	public static MemberService getInstance(){
		return instance;
	}
	MemberDao dao = MemberDao.getInstance();
	
	public void register(MemberVO vo) throws SQLException{
		int uno=dao.memberNum();
		vo.setUno(uno);
		dao.memberRegister(vo);
	}
	
	public MemberVO login(String id, String pass) throws SQLException{
		MemberVO vo = dao.memberLogin(id, pass);
		return vo;
	}
	
	public boolean idcheck(String id) throws SQLException{
		System.out.println("memberservice:"+id);
		return dao.idcheck(id);
	}
	
	public void requestFriend(String r_id, String s_id) throws SQLException {
		dao.requestFriend(r_id,s_id);
	}
	
	public boolean friendCheck(String r_id) throws SQLException{
		return dao.friendCheck(r_id);
	}
	
	public ArrayList<MemberVO> friendconfirm(String r_id) throws SQLException{
		ArrayList<MemberVO> list = dao.friendconfirm(r_id);
		return list;
	}
	public void friendapprove(String s_id,String r_id) throws SQLException{
		dao.friendapprove(s_id,r_id);
	}
	public void addfriend(String s_id, String r_id) throws SQLException{
		dao.addfriend(s_id,r_id);
	}
	
	public void deletefriendrequest(String s_id, String r_id)throws SQLException{
		dao.deletefriendrequest(s_id,r_id);
	}
	public void friendreject(String s_id, String r_id) throws SQLException{
		dao.friendreject(s_id,r_id);
		
	}
}
