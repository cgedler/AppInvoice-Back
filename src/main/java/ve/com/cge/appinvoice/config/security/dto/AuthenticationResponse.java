
package ve.com.cge.appinvoice.config.security.dto;

/**
 * AuthenticationResponse : DTO for responses
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 24, 2024
 */
public class AuthenticationResponse {
    
    private String token;

    public AuthenticationResponse() {
    }
    
    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
