package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.BoardVO;

public class LikeaddController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		System.out.println("LikeController/p_no:"+p_no);
		BoardService service = BoardService.getInstance();
		ModelAndView mv = new ModelAndView();
		int like = 0;
		System.out.println("LikeController/like1:"+like);
		try{
			like = service.likeadd(p_no);
			System.out.println("LikeController/like2:"+like);
			request.setAttribute("like", like);
			mv.setPath("like.jsp");
			System.out.println("LikeController/mv.getPath()"+mv.getPath());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
