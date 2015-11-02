package model;

public class BoardVO {
	private String title;
	private String content;
	private int p_no;
	private int magazine;
	private String m_name;
	private int section;
	private String s_name;
	private int uno;
	private String path;
	private int pic_no;
	private String u_id;
	private int like;
	public BoardVO(){}
	public BoardVO(String title, String content, int p_no, int magazine, int section, int uno, String path,
			int pic_no) {
		super();
		this.title = title;
		this.content = content;
		this.p_no = p_no;
		this.magazine = magazine;
		this.section = section;
		this.uno = uno;
		this.path = path;
		this.pic_no = pic_no;
	}
	public BoardVO(int p_no,int uno, String title, String content, int magazine, int section, int like){
		super();
		this.p_no=p_no;
		this.uno=uno;
		this.title=title;
		this.content=content;
		this.magazine=magazine;
		this.section=section;
		this.like = like;
	}
	public BoardVO(int p_no, String u_id, String title, String content, String m_name, String s_name, int magazine, int section, int like) {
		super();
		this.p_no = p_no;
		this.u_id = u_id;
		this.title = title;
		this.content = content;
		this.m_name = m_name;
		this.s_name = s_name;
		this.magazine = magazine;
		this.section = section;
		this.like = like;
	}
	public BoardVO(int p_no, String u_id, String title, String content, String m_name, String s_name, int magazine, int section) {
		super();
		this.p_no = p_no;
		this.u_id = u_id;
		this.title = title;
		this.content = content;
		this.m_name = m_name;
		this.s_name = s_name;
		this.magazine = magazine;
		this.section = section;
	}

	public BoardVO(String title, String content, int magazine, int section) {
		super();
		this.title = title;
		this.content = content;
		this.magazine = magazine;
		this.section = section;
	}
	public BoardVO(int p_no, String title, String content, int pic_no, String path){
		super();
		this.p_no = p_no;
		this.title = title;
		this.content = content;
		this.pic_no = pic_no;
		this.path = path;
	}
	public BoardVO(String title, String content){
		super();
		this.title = title;
		this.content = content;
	}
	public BoardVO(String title, String content, int p_no, String m_name, String s_name, String u_id) {
		super();
		this.title = title;
		this.content = content;
		this.p_no = p_no;
		this.m_name = m_name;
		this.s_name = s_name;
		this.u_id = u_id;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public int getMagazine() {
		return magazine;
	}
	public void setMagazine(int magazine) {
		this.magazine = magazine;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getPic_no() {
		return pic_no;
	}
	public void setPic_no(int pic_no) {
		this.pic_no = pic_no;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "BoardVO [title=" + title + ", content=" + content + ", p_no=" + p_no + ", magazine=" + magazine
				+ ", section=" + section + ", uno=" + uno + ", path=" + path + ", pic_no=" + pic_no + "]";
	}
	
}
