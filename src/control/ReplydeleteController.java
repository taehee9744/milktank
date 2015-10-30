package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;

public class ReplydeleteController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		try {
			r_no = service.delArticle(r_no);
			request.setAttribute("num", r_no);
			mv.setPath("del.jsp");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

}
