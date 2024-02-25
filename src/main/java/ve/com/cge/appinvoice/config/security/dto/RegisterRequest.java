
package ve.com.cge.appinvoice.config.security.dto;

import ve.com.cge.appinvoice.config.user.Role;

/**
 * RegisterRequest 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 24, 2024
 */
public class RegisterRequest {
    
    private String username;
    private String name;
    private String password;
    private Role role;
 
    public RegisterRequest() {
    }

    public RegisterRequest(String username, String name, String password, Role role) {
        this.username = username;
        this.name = name;
        this.password = password;        
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
