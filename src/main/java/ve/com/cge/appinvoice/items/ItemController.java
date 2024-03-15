
package ve.com.cge.appinvoice.items;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditDTO;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * ItemController 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    private Logger logger;
            
    private final CategoryService categoryService;
    private final JwtService jwtService;
    private final AuditService auditService;

    public ItemController(CategoryService categoryService, JwtService jwtService, AuditService auditService) {
        this.categoryService = categoryService;
        this.jwtService = jwtService;
        this.auditService = auditService;
    }
    
    
    @PostMapping(value = "/category/add")
    public ResponseEntity<UserResponse> add(@RequestBody CategoryDTO request, @RequestHeader("Authorization") String bearerToken) {
        logger.info("antes");
        String username = jwtService.getUsernameFromToken(bearerToken);
        logger.info(username);
        AuditDTO transaction = new AuditDTO(
                username,
                request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);        
        return ResponseEntity.ok(categoryService.saveCategory(request));
    }
    
    
}
