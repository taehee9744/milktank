package control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberVO;

public class MyInfoController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(false);
		MemberVO vo1 = (MemberVO)session.getAttribute("login");
		
		ModelAndView mv = new ModelAndView();
		mv.setPath("MyInfo.jsp");
		return mv;
	}

}
