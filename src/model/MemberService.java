package model;

import java.sql.SQLException;

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
	
	

}
