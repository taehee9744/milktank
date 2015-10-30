package control;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;

public class DeleteController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		BoardService service =BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		int pno = Integer.parseInt(request.getParameter("pno"));
		//System.out.println(pno);
		
		try{
			service.delete(pno);
			mv.setPath("delete_result.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
