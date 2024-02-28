
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
