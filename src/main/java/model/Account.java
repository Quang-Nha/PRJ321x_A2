package model;

public class Account {
	private String username, password, name, address, phone;
	private int role, check;

	public Account() {
	}

	public Account(String username, String password, String name, String address, String phone, int role, int check) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.role = role;
		this.check = check;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	// hàm xác thực giá trị và gán giá trị cho biến thông báo message
	public boolean validate() {
		if (username == null || password == null) {
			return false;
		}

		String user = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		String pass = "[a-zA-Z0-9_!@#$%^&*]+";

		if (!username.matches(user) || !password.matches(pass)) {
			return false;
		}

		return true;
	}
}
