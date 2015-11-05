package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberService;

public class FriendcheckController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		MemberService service = MemberService.getInstance();
		String r_id = request.getParameter("r_id");
		boolean fcheck;
		try{
			fcheck=service.friendCheck(r_id);
			System.out.println(fcheck);
			request.setAttribute("fcheck",fcheck);
			mv.setPath("friendcheck.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
