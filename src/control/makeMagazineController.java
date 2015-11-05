package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;

public class makeMagazineController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int u_no = Integer.parseInt(request.getParameter("u_no"));
		System.out.println("makeMagazinecontroller u_no:"+u_no);
		String m_name = request.getParameter("m_name");
		
		ModelAndView mv = new ModelAndView();
		BoardService service = BoardService.getInstance();
		try{
			service.makeMagazine(m_name, u_no);
			mv.setPath("MyInfo.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
