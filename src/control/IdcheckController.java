package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberService;

public class IdcheckController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		System.out.println("idcheckcontroller:"+id);
		MemberService service = MemberService.getInstance();
		ModelAndView mv = new ModelAndView();
		boolean check;
		try{
			check = service.idcheck(id);
			request.setAttribute("idcheck", check);
			System.out.println("idcheckcontroller check:"+check);
			mv.setPath("idcheck.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
