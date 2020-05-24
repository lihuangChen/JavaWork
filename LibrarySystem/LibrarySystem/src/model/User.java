package model;

public class User {
	private int user_id;
	private String user_name;
	private String password;
	private int age;
	private String gender;
	private String phone_no;
	private String address;
	private int user_state;
	private int identity;
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUser_state() {
		return user_state;
	}
	public void setUser_state(int user_state) {
		this.user_state = user_state;
	}
	
	public User(int user_id, String user_name, String password, int age, String gender, String phone_no, String address,
			int user_state, int identity) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.phone_no = phone_no;
		this.address = address;
		this.user_state = user_state;
		this.identity=identity;
	}
	public User() {
		super();
	}
	
	
}
