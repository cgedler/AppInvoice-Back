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

package ve.com.cge.appinvoice.accpay.controller;

import ve.com.cge.appinvoice.accpay.dto.ShoppingDTO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import ve.com.cge.appinvoice.accpay.model.Shopping;
import ve.com.cge.appinvoice.accpay.service.ShoppingService;
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * ShoppingController: controller in charge of handling requests related to
 * purchases from suppliers
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@RestController
@RequestMapping("/shopping")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ShoppingController {
    
    private static Logger logger = LoggerFactory.getLogger(ShoppingController.class);
    
    private final JwtService jwtService;
    private final AuditService auditService;
    private final ShoppingService shoppingService;

    public ShoppingController(JwtService jwtService, AuditService auditService, ShoppingService shoppingService) {
        this.jwtService = jwtService;
        this.auditService = auditService;
        this.shoppingService = shoppingService;
    }
  
    @GetMapping(value = "/")
    public List<Shopping> getShoppingsData() {
        logger.info("- Get list : ShoppingController -");
        List<Shopping> listShoppings = new ArrayList<Shopping>();
        listShoppings = shoppingService.findShoppings();
        return listShoppings;
    }
    
    @GetMapping(value = "/year/{year}")
    public ResponseEntity<?> getShoppingsDataByYear(@PathVariable int year) {
        logger.info("- Get list By Year : ShoppingController -");
        Map<String, Object> params = new HashMap<String, Object>();
        params = shoppingService.shoppingsByYear(year);
        if (params == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ShoppingDTO> getShoppingData(@PathVariable Long id) {
        logger.info("- Get by Id : ShoppingController -");
        ShoppingDTO shoppingDTO = shoppingService.findShoppingById(id);
        if (shoppingDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shoppingDTO);        
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody ShoppingDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Add new : ShoppingController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "New information has been added: " + request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(shoppingService.insertShopping(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Long id, @RequestBody ShoppingDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Edit by Id : ShoppingController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + request.getId().toString() + " and data: " + request.getDescription() + " was updated",
                TransactionType.UPDATE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(shoppingService.updateShopping(request, id));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        logger.info("- Delete by Id : ShoppingController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + id + " was deleted",
                TransactionType.DELETE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(shoppingService.deleteShopping(id));        
    }

}
