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

import java.io.FileNotFoundException;
import java.io.IOException;
import ve.com.cge.appinvoice.accrec.model.Customer;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import ve.com.cge.appinvoice.accrec.dto.CustomerDTO;
import ve.com.cge.appinvoice.accrec.service.CustomerService;
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * CustomerController: controller in charge of handling customer-related requests 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CustomerController {
    
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    private final JwtService jwtService;
    private final AuditService auditService;
    private final CustomerService customerService;

    public CustomerController(JwtService jwtService, AuditService auditService, CustomerService customerService) {
        this.jwtService = jwtService;
        this.auditService = auditService;
        this.customerService = customerService;
    }
  
    @GetMapping(value = "/")
    public List<Customer> getCustomersData() {
        logger.info("- Get list : CustomerController -");
        List<Customer> listCustomers= new ArrayList<Customer>();
        listCustomers = customerService.findCustomers();
        return listCustomers;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> getCustomerData(@PathVariable Long id) {
        logger.info("- Get by Id : CustomerController -");
        CustomerDTO customerDTO = customerService.findCustomerById(id);
        if (customerDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDTO);        
    }
    
    @GetMapping(value = "/pdf")
    public ResponseEntity<byte[]> getCustomerPDF() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("customerReport", "customerReport.pdf");
        return ResponseEntity.ok().headers(headers).body(customerService.exportListToPdf());
    }
    
    @GetMapping(value = "/xls")
    public ResponseEntity<byte[]> getCustomerXLS() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        ContentDisposition contentDisposition= ContentDisposition.builder("attachment").filename("customerReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(customerService.exportListToXls());
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody CustomerDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Add new : CustomerController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "New information has been added: " + request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(customerService.insertCustomer(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Long id, @RequestBody CustomerDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Edit by Id : CustomerController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + request.getId().toString() + " and data: " + request.getDescription() + " was updated",
                TransactionType.UPDATE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(customerService.updateCustomer(request, id));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        logger.info("- Delete by Id : CustomerController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + id + " was deleted",
                TransactionType.DELETE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(customerService.deleteCustomer(id));        
    }

}
