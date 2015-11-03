package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardService;
import model.BoardVO;
import model.MemberVO;

public class LikeaddsubController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession(false);
		MemberVO vo1 = (MemberVO)session.getAttribute("login");
		
		BoardVO vo = null;
		try{
			vo = service.likeaddsub(p_no, vo1.getUno());
			request.setAttribute("like", vo);
			mv.setPath("like.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
