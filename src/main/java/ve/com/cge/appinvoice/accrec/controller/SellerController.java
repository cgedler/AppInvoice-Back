
package ve.com.cge.appinvoice.accrec.controller;

import ve.com.cge.appinvoice.accrec.model.Seller;
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
import ve.com.cge.appinvoice.accrec.dto.SellerDTO;
import ve.com.cge.appinvoice.accrec.service.SellerService;
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * SellerController 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SellerController {

    private final JwtService jwtService;
    private final AuditService auditService;
    private final SellerService sellerService;

    public SellerController(JwtService jwtService, AuditService auditService, SellerService sellerService) {
        this.jwtService = jwtService;
        this.auditService = auditService;
        this.sellerService = sellerService;
    }
  
    @GetMapping(value = "/")
    public List<Seller> getSellersData() {
        List<Seller> listSellers= new ArrayList<Seller>();
        listSellers = sellerService.findSellers();
        return listSellers;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<SellerDTO> getSellerData(@PathVariable Integer id) {
        SellerDTO sellerDTO = sellerService.findSellerById(id);
        if (sellerDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sellerDTO);        
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody SellerDTO request, @RequestHeader("Authorization") String token) {
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "New information has been added: " + request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(sellerService.insertSeller(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Integer id, @RequestBody SellerDTO request, @RequestHeader("Authorization") String token) {
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + request.getId().toString() + " and data: " + request.getDescription() + " was updated",
                TransactionType.UPDATE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(sellerService.updateSeller(request, id));
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
        return ResponseEntity.ok(sellerService.deleteSeller(id));        
    }

}
