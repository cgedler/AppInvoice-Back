/* 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ve.com.cge.appinvoice.accrec.controller;

import ve.com.cge.appinvoice.accrec.model.Billing;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ve.com.cge.appinvoice.accrec.dto.BillingDTO;
import ve.com.cge.appinvoice.accrec.service.BillingService;
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * BillingController: controller in charge of handling requests related to sales invoices 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@RestController
@RequestMapping("/billing")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BillingController {
    
    private static Logger logger = LoggerFactory.getLogger(BillingController.class);

    private final JwtService jwtService;
    private final AuditService auditService;
    private final BillingService billingService;

    public BillingController(JwtService jwtService, AuditService auditService, BillingService billingService) {
        this.jwtService = jwtService;
        this.auditService = auditService;
        this.billingService = billingService;
    }
  
    @GetMapping(value = "/")
    public List<Billing> getBillingsData() {
        logger.info("- Get list : BillingController -");
        List<Billing> listBillings= new ArrayList<Billing>();
        listBillings = billingService.findBillings();
        return listBillings;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<BillingDTO> getBillingData(@PathVariable Long id) {
        logger.info("- Get by Id : BillingController -");
        BillingDTO billingDTO = billingService.findBillingById(id);
        if (billingDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(billingDTO);        
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody BillingDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Add new : BillingController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "New information has been added: " + request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(billingService.insertBilling(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Long id, @RequestBody BillingDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Edit by Id : BillingController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + request.getId().toString() + " and data: " + request.getDescription() + " was updated",
                TransactionType.UPDATE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(billingService.updateBilling(request, id));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        logger.info("- Delete by Id : BillingController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + id + " was deleted",
                TransactionType.DELETE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(billingService.deleteBilling(id));        
    }

}
