package control;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.BoardVO;
import model.MemberVO;

public class UpdateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		String u_id = request.getParameter("uid");
		String title= request.getParameter("title");
		int section = Integer.parseInt(request.getParameter("section"));
		String s_name = request.getParameter("section");
		int magazine = Integer.parseInt(request.getParameter("magazine"));
		String m_name = request.getParameter("magazine");
		String content=request.getParameter("content");
		BoardVO vo = new BoardVO(pno, u_id, title, content, m_name, s_name, magazine, section);
		
		try{
			service.update(vo);
			request.setAttribute("content", vo);
			mv.setPath("update_result.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
