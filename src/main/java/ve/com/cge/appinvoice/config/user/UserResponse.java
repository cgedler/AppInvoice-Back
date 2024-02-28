
package ve.com.cge.appinvoice.config.user;

/**
 * UserResponse 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 27, 2024
 */
public class UserResponse {
    
    String message;

    public UserResponse() {
    }
    
    public UserResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
