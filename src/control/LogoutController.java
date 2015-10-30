package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView mv = new ModelAndView();
		if(session!=null && session.getAttribute("login")!=null){
			session.invalidate();
			mv.setPath("index.jsp");
		}
		return mv;
	}

}
