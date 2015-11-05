package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ReplyVO;

public class BoardService {
	private static BoardService instance = new BoardService();

	private BoardService() {
	}

	public static BoardService getInstance() {
		return instance;
	}

	BoardDao dao = BoardDao.getInstance();
	ReplyDao rdao = ReplyDao.getInstance();

	public void insert(BoardVO vo, int uno) throws SQLException {
		int p_no = dao.BoardNum();
		vo.setP_no(p_no);
		if(vo.getPath()!=null){
			int pic_no = dao.PictureNum();
			//System.out.println(pic_no);
			vo.setPic_no(pic_no);
			//System.out.println(vo.getPic_no());
			dao.PictureInsert(vo);
		}
		dao.BoardInsert(vo, uno);
	}
	
	public ArrayList<BoardVO> allView() throws SQLException{
		ArrayList<BoardVO> list = dao.getBoardAll();
//		for(int i=0; i<list.size(); i++){
//			System.out.println(list.get(i));
//		}
		return list;
	}
	
	public BoardVO detail(int no) throws SQLException{
		return dao.getBoard(no);
		
	}
	
	public void delete(int no) throws SQLException{
		dao.deleteBoard(no);
	}
	
	public void update(BoardVO vo ) throws SQLException{
		dao.updateBoard(vo);
	}
	/*public boolean getlikeState(int p_no, int u_no) throws SQLException{
		return dao.getlikeState(p_no, u_no);
	}*/
	public BoardVO likelist(int p_no, int u_no) throws SQLException{
		int like_num = dao.getLike(p_no);
		boolean likestate = dao.getlikeState(p_no, u_no);
		BoardVO vo = new BoardVO(like_num, likestate );
		return vo;
	}
	public BoardVO likeaddsub(int p_no, int u_no) throws SQLException{
		boolean likestate = dao.getlikeState(p_no, u_no);
		System.out.println("boardservice likestate:"+likestate);
		int like_num;
		BoardVO vo = null;
		if(likestate){
			//���� �Խù��� ���ƿ� ���� �����ϰ� 1�� ���� ������Ʈ ��Ŵ
			like_num = dao.getLike(p_no);
			like_num -= 1;
			dao.updateLike(p_no, like_num);
			
			//likes ���̺� �ش� ������ �ش� �Խù� ��ȣ�� ���� ���� ����(���ƿ� ���)
			dao.likesubState(p_no, u_no);
			
			//���ƿ� ���� ������Ʈ�� ����� ����, ���ƿ���� ����
			like_num = dao.getLike(p_no);
			likestate = dao.getlikeState(p_no, u_no);
			vo = new BoardVO(like_num, likestate );
			
		}else{
			//���� �Խù��� ���ƿ� ���� �����ϰ� 1�� ���ؼ� ������Ʈ ��Ŵ
			like_num = dao.getLike(p_no);
			like_num += 1;
			dao.updateLike(p_no, like_num);
			
			//likes ���̺� �ش� ������ �ش� �Խù� ��ȣ�� �Է��Ͽ� ���ƿ並 �����ٴ� ���¸� �Է�
			dao.likeaddState(p_no, u_no);
			
			//���ƿ� ���� ������Ʈ�� ����� ����, ���ƿ���� ����
			like_num = dao.getLike(p_no);
			likestate = dao.getlikeState(p_no, u_no);
			vo = new BoardVO(like_num, likestate );
		}
		return vo;
	}
	/*public void likeaddstate(int p_no, int u_no) throws SQLException{
		dao.likeaddState(p_no, u_no);
	}*/
	public BoardVO get_Ppath(int p_no) throws SQLException{
		return dao.get_Ppath(p_no);
	}
	public ArrayList<BoardVO> get_Magazine(int u_no) throws SQLException{
		return dao.get_Magazine(u_no);
	}
	public void makeMagazine(String m_name, int u_no) throws SQLException{
		int no = dao.magazineNum();
		dao.makeMagazine(m_name, u_no, no);
	}
//////////////////////////////////////////////////////////////////////////
	public ReplyVO addArticle(ReplyVO vo, int pno,MemberVO uvo) throws SQLException {
		// TODO Auto-generated method stub
		int num = rdao.insertReply(vo, pno, uvo.getUno());
		return rdao.select(num);
	}

	public ArrayList<ReplyVO> getAll(int pno) throws SQLException {
		// TODO Auto-generated method stub
		return rdao.selectAll(pno);
	}

	public ReplyVO editArticle(ReplyVO vo) throws SQLException {
		// TODO Auto-generated method stub
		int num = rdao.update(vo);
		return rdao.select(num);
	}

	public int delArticle(int num) throws SQLException{
		// TODO Auto-generated method stub
		num = rdao.delete(num);
		return num;
	}

	public ReplyVO getArticle(int num) throws SQLException{
		// TODO Auto-generated method stub
		return rdao.select(num);
	}
	
}
