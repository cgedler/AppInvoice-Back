
package ve.com.cge.appinvoice.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 24, 2024
 */
@RestController
@RequestMapping("/api/v1")
public class DemoController {
    
    @PostMapping(value = "demo")
    public String welcome() {
        return "Welcome from secure endpoint";
    }

}
