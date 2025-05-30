package classes;

public abstract class User {
	
	
	    private int userId;
	    private String name;
	    private String email;
	    private String password;
             public String role;

    public void setRole(String role) {
        this.role = role;
    }

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public  User() {
    } 
    
	    public int getUserId()
            { return userId; }
	    public void setUserId(int userId)
            { this.userId = userId; }
	    public String getName() 
            { return name; }
	    public void setName(String name)
            { this.name = name; }
	    public String getEmail()
            { return email; }
	    public void setEmail(String email)
            { this.email = email; }
	    public String getPassword()
            { return password; }
	    public void setPassword(String password) 
            { this.password = password;}
            
             public abstract String getRole();
            
     }

    

