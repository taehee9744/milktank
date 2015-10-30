package model;

public class ReplyVO {
	private int p_no;
	private String r_content;
	private int r_no;
	private int user_no;
	private String u_id;
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public ReplyVO(int p_no, String r_content, int r_no, int user_no) {
		super();
		this.p_no = p_no;
		this.r_content = r_content;
		this.r_no = r_no;
		this.user_no = user_no;
	}
	public ReplyVO(String r_content) {
		super();
		this.r_content = r_content;
	}
	public ReplyVO(){}
	public ReplyVO(String r_content, int r_no) {
		super();
		this.r_content = r_content;
		this.r_no = r_no;
	}
	public ReplyVO(int r_no) {
		super();
		this.r_no = r_no;
	}
	public ReplyVO(int p_no, String r_content) {
		super();
		this.p_no = p_no;
		this.r_content = r_content;
	}
	public ReplyVO(String r_content, int r_no, String u_id) {
		super();
		this.r_content = r_content;
		this.r_no = r_no;
		this.u_id = u_id;
	}
	public ReplyVO(int p_no, String r_content, int r_no, int user_no, String u_id) {
		super();
		this.p_no = p_no;
		this.r_content = r_content;
		this.r_no = r_no;
		this.user_no = user_no;
		this.u_id = u_id;
	}
	
	

}
