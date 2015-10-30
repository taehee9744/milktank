package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import model.BoardService;
import model.BoardVO;
import model.MemberVO;

public class InsertController implements Controller {
	BoardVO vo = new BoardVO();
	public InsertController(BoardVO vo){
		this.vo = vo;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession(false);
		MemberVO vo1 = (MemberVO)session.getAttribute("login");
		
		try{
			service.insert(vo, vo1.getUno());
			mv.setPath("insert_ok.jsp");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
