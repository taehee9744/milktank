package control;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberService;
import model.MemberVO;

public class LoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
				
		MemberService service = MemberService.getInstance();
		ModelAndView mv = new ModelAndView();
		MemberVO vo = null;
		
		try{
			vo = service.login(id, pass);
			if(vo!=null){
				HttpSession session = request.getSession();
				session.setAttribute("login", vo);
				mv.setPath("login_ok.jsp");
			}else{
			
				mv.setPath("login.jsp");
			}
			/*if(id.equals(vo.getId()) && pass.equals(vo.getPass())){
				mv.setPath("login_ok.jsp");
			}else{
				mv.setPath("login.jsp");
			}*/
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return mv;
	}
	

}
