package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardService;
import model.MemberVO;
import model.ReplyVO;

public class ReplyinsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int pno = Integer.parseInt(request.getParameter("p_no"));
		System.out.println(pno);
		String content = request.getParameter("content");
		System.out.println(content);

		ReplyVO vo = new ReplyVO();
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		
		vo.setR_content(content);
		
		HttpSession session = request.getSession(false);
		MemberVO vo1 = (MemberVO)session.getAttribute("login");
		
		try{
			vo = service.addArticle(vo, pno, vo1);
			request.setAttribute("a", vo);
			mv.setPath("article.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
