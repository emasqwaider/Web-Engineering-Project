package classes;

public class Admin extends User{

    public Admin() {
         setRole("admin");
    }

	

    public Admin(int userId, String name, String role) {
        super(userId, name, role);
        setRole("admin");
    }
	
	public String getRole() {
		return role;
	}
}
