package Singleton;

public class User {
	private String username;
	private String password;
	private Boolean available = false;
	private static User single_instance = null;

	// variable of type String
	public String s;

	public static User getInstance() {
		return single_instance;
	}

	// static method to create instance of Singleton class
	public static User getInstance(String username, String password) {
		if (single_instance == null)
			single_instance = new User(username, password);

		return single_instance;
	}

	private User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
