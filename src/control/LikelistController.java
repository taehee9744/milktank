package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;

public class LikelistController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		int like = 0;
		try{
			like = service.likelist(p_no);
			request.setAttribute("like", like);
			mv.setPath("like.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
