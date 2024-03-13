
package ve.com.cge.appinvoice.prueba;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Prueba 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 6, 2024
 */
@RestController
@RequestMapping("/api/v2")
public class Prueba {
    
    @GetMapping(value = "demo")
    public String welcome() {
        return "Prueba endpoint";
    }

}
