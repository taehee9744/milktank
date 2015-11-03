package control;

import model.BoardVO;

public class HandlerMapping {
	public static HandlerMapping mapping = new HandlerMapping();
	private HandlerMapping(){}
	public static HandlerMapping getInstance(){
		return mapping;
	}
	
	public Controller create(String command, BoardVO vo){
		Controller ct = null;
		if(command.equals("register")){
			ct = new RegisterController();
		}else if(command.equals("login")){
			ct = new LoginController();
		}else if(command.equals("logout")){
			ct = new LogoutController();
		}else if(command.equals("insert")){
			ct = new InsertController(vo);
		}else if(command.equals("allview")){
			ct = new AllviewController();
		}else if(command.equals("content")){
			ct = new ContentlViewController();
		}else if(command.equals("deletecontent")){
			ct = new DeleteController();
		}else if(command.equals("updatecontent")){
			ct = new UpdateController();
		}else if(command.equals("addArticle")){
			ct = new ReplyinsertController();
		}else if(command.equals("articleList")){
			ct = new ReplyallviewController();
		}else if(command.equals("editForm")){
			ct = new ReplyupdateformController();
		}else if(command.equals("edit")){
			ct = new ReplyupdateController();
		}else if(command.equals("del")){
			ct = new ReplydeleteController();
		}else if(command.equals("likeadd")){
			ct = new LikeaddController();
		}else if(command.equals("likelist")){
			ct = new LikelistController();
		}else if(command.equals("idcheck")){
			ct = new IdcheckController();
		}
		
		return ct;
	}

}
