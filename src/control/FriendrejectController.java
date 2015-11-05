package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberService;

public class FriendrejectController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		MemberService service = MemberService.getInstance();
		String s_id = request.getParameter("s_id");
		String r_id = request.getParameter("r_id");
		
		try{
			service.friendreject(s_id,r_id);
			mv.setPath("index.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return mv;
	}

}
