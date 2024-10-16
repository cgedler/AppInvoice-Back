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

package ve.com.cge.appinvoice.config.user;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * UserService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 27, 2024
 */
@Service
public class UserService {

    private final IUserRepository userRepository; 

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {     
        userRepository.updateUser(userRequest.id, userRequest.username, userRequest.name, userRequest.password);
        return new UserResponse("El usuario se registró satisfactoriamente");
    }

    public UserDTO getUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);     
        if (user != null)
        {
            return new UserDTO(user.getId(), user.getUsername(), user.getName(), user.getPassword());
        }
        return null;
    }
}
