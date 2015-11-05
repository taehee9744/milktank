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
			//현재 게시물의 좋아요 수를 추출하고 1을 빼서 업데이트 시킴
			like_num = dao.getLike(p_no);
			like_num -= 1;
			dao.updateLike(p_no, like_num);
			
			//likes 테이블에 해당 유저와 해당 게시물 번호에 대한 정보 삭제(좋아요 취소)
			dao.likesubState(p_no, u_no);
			
			//좋아요 수를 업데이트한 결과를 추출, 좋아요상태 추출
			like_num = dao.getLike(p_no);
			likestate = dao.getlikeState(p_no, u_no);
			vo = new BoardVO(like_num, likestate );
			
		}else{
			//현재 게시물의 좋아요 수를 추출하고 1을 더해서 업데이트 시킴
			like_num = dao.getLike(p_no);
			like_num += 1;
			dao.updateLike(p_no, like_num);
			
			//likes 테이블에 해당 유저와 해당 게시물 번호를 입력하여 좋아요를 눌렀다는 상태를 입력
			dao.likeaddState(p_no, u_no);
			
			//좋아요 수를 업데이트한 결과를 추출, 좋아요상태 추출
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
