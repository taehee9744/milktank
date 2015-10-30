package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.ReplyVO;

public class ReplyallviewController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int pno = Integer.parseInt(request.getParameter("p_no"));
		ArrayList<ReplyVO> list = null;
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		try{
			list = service.getAll(pno); 
			request.setAttribute("data", list);
			mv.setPath("articleList.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
