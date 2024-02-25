package entity;

import adt.ArrayList;
import adt.ListInterface;
import adt.MapInterface;
import adt.HashMap;

/**
 *
 * @author Joshua Koh Min En
 */
public class Admin extends User {
    // Data Attribute
    private String name;

    // Constructor
    public Admin() {
        this("admin", "admin123");
    }
    
    public Admin(String username, String password) {
        super(username, password);
        this.name = "Admin";
    }
  
    
    // Getter
    public String getName() {
        return name;
    }
    
    // Setter 
    public void setName(String name) {
        this.name = name;
    }
}
