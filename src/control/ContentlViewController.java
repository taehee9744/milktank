package control;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.BoardVO;

public class ContentlViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("pno"));
		String path = request.getParameter("isdetail");
		BoardVO vo = null;
		try{
		 vo = service.detail(no);
			request.setAttribute("content", vo);
			if(path.equals("true")){
				mv.setPath("contentview.jsp");
			}else{
				mv.setPath("update.jsp");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
