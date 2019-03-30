package journalSystem;

public class User {
	
	private String email;
	private String pass;
	private String role;
	private String firstName;
	private String lastName;
	
	public User() {
		
	}
	
	public User(String email, String pass, String role, String first, String last) {
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.firstName = first;
		this.lastName = last;
	}

	
	public String getEmail() {
		return this.email;
	}
	public String getPass() {
		return this.pass;
	}
	public String getRole() {
		return this.role;
	}
	public String getfirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	
	public void setEmail(String a) {
		this.email = a;
	}
	public void setPass(String a) {
		this.pass = a;
	}
	public void setRole(String a) {
		this.role = a;
	}
	public void setFirstName(String a) {
		this.firstName = a;
	}
	public void setLastName(String a) {
		this.lastName = a;
	}

}
