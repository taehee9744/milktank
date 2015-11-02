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
		//System.out.println(vo.getPath());
		boolean a = vo.getPath().isEmpty();
		//System.out.println(a);
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
	public int likelist(int p_no) throws SQLException{
		int like_num = dao.getLike(p_no);
		return like_num;
	}
	public int likeadd(int p_no) throws SQLException{
		int like_num = likelist(p_no);
		like_num += 1;
		dao.updateLike(p_no, like_num);
		like_num = likelist(p_no);
		//System.out.println("likeadd:"+like_num);
		return like_num;
		
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
