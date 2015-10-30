package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.ReplyVO;

public class ReplyupdateformController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		ReplyVO vo = null;
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		try{
			vo = service.getArticle(r_no);
			request.setAttribute("a", vo);
			mv.setPath("article.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
