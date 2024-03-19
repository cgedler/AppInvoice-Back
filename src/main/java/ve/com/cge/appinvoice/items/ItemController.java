
package ve.com.cge.appinvoice.items;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserDTO;
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
  
    private final CategoryService categoryService;
    private final JwtService jwtService;
    private final AuditService auditService;
    

    public ItemController(CategoryService categoryService, JwtService jwtService, AuditService auditService) {
        this.categoryService = categoryService;
        this.jwtService = jwtService;
        this.auditService = auditService;
    }
    
    
    
    /*@GetMapping(value = "/category")
    public ResponseEntity<UserResponse> getCategoriesData() {
        UserResponse responseBody = categoryService.findCategories();
        return ResponseEntity.ok(responseBody);    
    }*/
    
    @GetMapping(value = "/category")
    public List<Category> getCategoriesData() {
        List<Category> listCategories= new ArrayList<Category>();
        listCategories = categoryService.findCategories();
        return listCategories; //(List<Category>) ResponseEntity.ok(listCategories);    
    }
    
    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryData(@PathVariable Integer id) {
        CategoryDTO categoryDTO = categoryService.findCategoryById(id);
        if (categoryDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryDTO);        
    }
       
    
    @PostMapping(value = "/category/add")
    public ResponseEntity<UserResponse> add(@RequestBody CategoryDTO request, @RequestHeader("Authorization") String token) {
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);        
        return ResponseEntity.ok(categoryService.insertCategory(request));
    }
        
}
