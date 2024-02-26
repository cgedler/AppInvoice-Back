
package ve.com.cge.appinvoice.demo;

import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ve.com.cge.appinvoice.config.user.Role;

/**
 * HomeController 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 25, 2024
 */
@RestController
@RequestMapping("hello")
public class HomeController {
    
    private Logger myLogger=Logger.getLogger(getClass().getName());
    
    @RolesAllowed("USER")
    @GetMapping("user")
    public String helloUser() {
        myLogger.info("metodo GET hello/user");
        return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }

}
