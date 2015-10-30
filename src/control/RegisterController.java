package control;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberService;
import model.MemberVO;

public class RegisterController implements Controller {
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response){
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String local = request.getParameter("local");
		String[] inter = request.getParameterValues("i1");
		String interest = "";
		for(int i=0; i<inter.length; i++){
			interest += inter[i]+",";
		}
		//System.out.println(interest);
		MemberService service = MemberService.getInstance();
		ModelAndView mv = new ModelAndView();
		MemberVO vo = new MemberVO(id, pass, gender,birth, local, interest);
		
		try{
			service.register(vo);
			mv.setPath("register_result.jsp");
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return mv;
		
		
	}

}
