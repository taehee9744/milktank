package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberService;
import model.MemberVO;

public class FriendconfirmController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		MemberService service = MemberService.getInstance();
		String r_id = request.getParameter("r_id");
		System.out.println(r_id);
		try{
			ArrayList<MemberVO> list= service.friendconfirm(r_id);
			request.setAttribute("friendconfirm", list);
			mv.setPath("friendConfirm.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return mv;
	}

}
