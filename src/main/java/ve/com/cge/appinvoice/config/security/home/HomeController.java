
package ve.com.cge.appinvoice.config.security.home;

import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController : Test roles controller 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 25, 2024
 */
@RestController
@RequestMapping("hello")
public class HomeController {
    
    @RolesAllowed("USER")
    @GetMapping("user")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }

}
