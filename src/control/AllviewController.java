package control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;

public class AllviewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		try{
			ArrayList list = service.allView();
			request.setAttribute("allview", list);
			mv.setPath("allview.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
