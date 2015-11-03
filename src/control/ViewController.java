package control;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardService;
import model.BoardVO;

/**
 * Servlet implementation class ViewController
 */
public class ViewController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException{
      
      int pno = Integer.parseInt(request.getParameter("pno"));
      System.out.println(pno);
      BoardService service = BoardService.getInstance();
      BoardVO vo = null; 
      
      
      
      
      try {
         vo = service.get_Ppath(pno);
         response.setContentType("image/jpeg");
         response.addHeader("Content-Transger-Encoding", "binary");
         String filename = "default.jpg";
         System.out.println("view"+vo.getPath());
         if( vo.getPath() != null ) {
            filename = vo.getPath();
         }
         FileInputStream fis = new FileInputStream(filename);
         BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
         byte[] buff = new byte[1024];
         for (int i = 0; (i = fis.read(buff)) != -1;) {
            bos.write(buff, 0, i);
         }
         bos.flush();
         
         System.out.println(vo.getPath());
      } catch (IOException | SQLException e) {
         System.out.println("Error:" + e.getMessage());
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
   }

}