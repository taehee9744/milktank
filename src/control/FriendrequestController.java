package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberService;
import model.MemberVO;

public class FriendrequestController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		MemberService service = MemberService.getInstance();
		String r_id = request.getParameter("r_id");
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("login");
		try{
			service.requestFriend(r_id,vo.getId());
			mv.setPath("index.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
