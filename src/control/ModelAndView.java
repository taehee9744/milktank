package control;

public class ModelAndView {
	String path="";
	boolean isRedirect;
	ModelAndView(){
		super();
	}
	ModelAndView(String path, boolean isRedirect){
		super();
		this.path=path;
		this.isRedirect=isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	public String toString(){
		return "ModelAndView [path="+path+", isRedirect="+isRedirect+"]";
	}
}
