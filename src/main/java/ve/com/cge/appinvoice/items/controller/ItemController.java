
package ve.com.cge.appinvoice.items.controller;

import ve.com.cge.appinvoice.items.dto.ItemDTO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserResponse;
import ve.com.cge.appinvoice.items.model.Item;
import ve.com.cge.appinvoice.items.service.ItemService;

/**
 * ItemController 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@RestController
@RequestMapping("/item")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ItemController {
    
    private final JwtService jwtService;
    private final AuditService auditService;
    private final ItemService itemService;

    public ItemController(JwtService jwtService, AuditService auditService, ItemService itemService) {
        this.jwtService = jwtService;
        this.auditService = auditService;
        this.itemService = itemService;
    }
  
    @GetMapping(value = "/")
    public List<Item> getItemsData() {
        List<Item> listItems= new ArrayList<Item>();
        listItems = itemService.findItems();
        return listItems;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDTO> getItemData(@PathVariable Integer id) {
        ItemDTO itemDTO = itemService.findItemById(id);
        if (itemDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDTO);        
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody ItemDTO request, @RequestHeader("Authorization") String token) {
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "New information has been added: " + request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(itemService.insertItem(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Integer id, @RequestBody ItemDTO request, @RequestHeader("Authorization") String token) {
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + request.getId().toString() + " and data: " + request.getDescription() + " was updated",
                TransactionType.UPDATE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(itemService.updateItem(request, id));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
    String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + id + " was deleted",
                TransactionType.DELETE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(itemService.deleteItem(id));        
    }
    
}
