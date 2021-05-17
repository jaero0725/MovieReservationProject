package client.VO;

public class CustomerVO {
	
	private String name;
	private String id;
	private String password;
	private String email;
	private String phoneNumber;
	
	public CustomerVO() {}
	public CustomerVO(String name, String id, String password, String email, String phoneNumber) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
