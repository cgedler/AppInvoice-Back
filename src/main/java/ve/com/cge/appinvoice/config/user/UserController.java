
package ve.com.cge.appinvoice.config.user;

import javax.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 27, 2024
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    //@RolesAllowed("USER")
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        //return new ResponseEntity<>(" Arrive" + id, HttpStatus.OK);
        UserDTO userDTO = userService.getUser(id);
        if (userDTO == null)
        {
           //return new ResponseEntity<>("Null", HttpStatus.OK); 
           return ResponseEntity.notFound().build();
        }
        //return new ResponseEntity<>("With data : " , HttpStatus.OK); 
        return ResponseEntity.ok(userDTO);
    }
    
    @PutMapping()
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest)
    {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }
    
}
