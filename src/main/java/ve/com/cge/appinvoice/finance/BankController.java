
package ve.com.cge.appinvoice.finance;

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

/**
 * BankController 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BankController {

    private final JwtService jwtService;
    private final AuditService auditService;
    private final BankService bankService;

    public BankController(JwtService jwtService, AuditService auditService, BankService bankService) {
        this.jwtService = jwtService;
        this.auditService = auditService;
        this.bankService = bankService;
    }
  
    @GetMapping(value = "/")
    public List<Bank> getBanksData() {
        List<Bank> listBanks= new ArrayList<Bank>();
        listBanks = bankService.findBanks();
        return listBanks;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<BankDTO> getBankData(@PathVariable Integer id) {
        BankDTO bankDTO = bankService.findBankById(id);
        if (bankDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankDTO);        
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody BankDTO request, @RequestHeader("Authorization") String token) {
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "New information has been added: " + request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(bankService.insertBank(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Integer id, @RequestBody BankDTO request, @RequestHeader("Authorization") String token) {
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + request.getId().toString() + " and data: " + request.getDescription() + " was updated",
                TransactionType.UPDATE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(bankService.updateBank(request, id));
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
        return ResponseEntity.ok(bankService.deleteBank(id));        
    }

}
