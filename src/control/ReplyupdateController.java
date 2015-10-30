package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.ReplyVO;

public class ReplyupdateController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		String content = request.getParameter("content");
		System.out.println("content"+content);
		ReplyVO vo = new ReplyVO();
		vo.setR_content(content);
		vo.setR_no(r_no);
		ModelAndView mv = new ModelAndView();
		BoardService service = BoardService.getInstance();
		try{
			vo = service.editArticle(vo);
			request.setAttribute("a", vo);
			mv.setPath("article.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mv;
	}

}
