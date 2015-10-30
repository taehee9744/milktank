package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.BoardVO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping mapping;

	public DispatcherServlet() {
		super();

	}

	@Override
	public void init() throws ServletException {
		mapping = HandlerMapping.getInstance();
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Controller ct = null;
		BoardVO vo = null;
		String command = request.getParameter("command");
		String rno = request.getParameter("r_no");
		
		System.out.println("command:"+command+" rno:"+rno);

		if (command.equals("insert")) {
			PicUpload pu = new PicUpload(request);
			vo = pu.upload();
			ct = mapping.create(command, vo);

			ModelAndView mv = ct.execute(request, response);

			if (mv.isRedirect()) {
				response.sendRedirect(mv.getPath());
			} else {
				request.getRequestDispatcher(mv.getPath()).forward(request, response);
			}
		} else {
			ct = mapping.create(command, vo);

			ModelAndView mv = ct.execute(request, response);
			System.out.println("mv.getpath():"+mv.getPath());

			if (mv.isRedirect()) {
				response.sendRedirect(mv.getPath());
				System.out.println("-----------------------------");
			} else {
				System.out.println("1");
				RequestDispatcher dispatcher = request.getRequestDispatcher(mv.getPath());
				dispatcher.forward(request, response);
				System.out.println("-----------------------------");
			}
			
		}
	}

}
