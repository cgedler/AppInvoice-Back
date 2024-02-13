
package ve.com.cge.appinvoice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("hello")
public class HelloRestController {
    
    @GetMapping("user")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }
    
    @GetMapping("publico")
    public String helloPublico() {
        return "Publico";
    }
    
    @GetMapping("privado")
    public String helloPrivado() {
        return "Acceso privado";
    }

}