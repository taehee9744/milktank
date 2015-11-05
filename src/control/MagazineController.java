package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.BoardVO;

public class MagazineController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int u_no = Integer.parseInt(request.getParameter("u_no"));
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		ArrayList<BoardVO> list = null;
		try{
			list = service.get_Magazine(u_no);
			request.setAttribute("magazine", list);
			mv.setPath("magazine.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
