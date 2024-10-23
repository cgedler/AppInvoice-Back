/* 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ve.com.cge.appinvoice.config.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ve.com.cge.appinvoice.config.user.Role;

/**
 * RegisterRequest: DTO for register request
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 24, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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
