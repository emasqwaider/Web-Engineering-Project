package classes;

public class Librarian  extends User{

    public Librarian() {
         setRole("librarian");
    }
	 
    
            @Override
	    public String getRole() {
	        return role;
                }
	 
	 private String employeeId;

    public Librarian(int userId, String name, String role) {
        super(userId, name, role);
        setRole("librarian");
    }

	    // Getters and Setters
	    public String getEmployeeId() {
	        return employeeId;
	    }

	    public void setEmployeeId(String employeeId) {
	        this.employeeId = employeeId;
            }
}
