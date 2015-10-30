package model;

public class MemberVO {
	private String id;
	private String pass;
	private String gender;
	private String birth;
	private int uno;
	private String local;
	private String interest;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public MemberVO(String id, String pass, String gender, String birth, String local, String interest) {
		super();
		this.id = id;
		this.pass = pass;
		this.gender = gender;
		this.birth = birth;
		this.local = local;
		this.interest = interest;
	}
	public MemberVO(int uno, String id, String pass) {
		super();
		this.uno = uno;
		this.id = id;
		this.pass = pass;
	}
	public MemberVO(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}
	public MemberVO(){}
	
	
}
