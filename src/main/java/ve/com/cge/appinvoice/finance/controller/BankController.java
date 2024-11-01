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

package ve.com.cge.appinvoice.finance.controller;

import java.io.FileNotFoundException;
import ve.com.cge.appinvoice.finance.model.Bank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import ve.com.cge.appinvoice.audit.Audit;
import ve.com.cge.appinvoice.audit.AuditService;
import ve.com.cge.appinvoice.audit.TransactionType;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.UserResponse;
import ve.com.cge.appinvoice.finance.dto.BankDTO;
import ve.com.cge.appinvoice.finance.service.BankService;

/**
 * BankController: controller in charge of handling requests related to banks 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BankController {
    
    private static Logger logger = LoggerFactory.getLogger(BankController.class);

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
        logger.info("- Get list : BankController -");
        List<Bank> listBanks= new ArrayList<Bank>();
        listBanks = bankService.findBanks();
        return listBanks;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<BankDTO> getBankData(@PathVariable Long id) {
        logger.info("- Get by Id : BankController -");
        BankDTO bankDTO = bankService.findBankById(id);
        if (bankDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankDTO);        
    }
    
     @GetMapping(value = "/pdf/{id}")
    public ResponseEntity<byte[]> getBankByIdPDF(@PathVariable Long id) throws JRException, FileNotFoundException {
        logger.info("- Print PDF by Id : BankController -");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("bankReport", "bankReport.pdf");
        return ResponseEntity.ok().headers(headers).body(bankService.exporByIdToPdf(id));
    }
    
    @GetMapping(value = "/pdf")
    public ResponseEntity<byte[]> getBankPDF() throws JRException, FileNotFoundException {
        logger.info("- Print list PDF : BankController -");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("bankReport", "banksReport.pdf");
        return ResponseEntity.ok().headers(headers).body(bankService.exportListToPdf());
    }
    
    @GetMapping(value = "/xls")
    public ResponseEntity<byte[]> getBankXLS() throws JRException, FileNotFoundException {
        logger.info("- Print list XLS : BankController -");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        ContentDisposition contentDisposition= ContentDisposition.builder("attachment").filename("banksReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(bankService.exportListToXls());
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody BankDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Add new : BankController -");
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
    public ResponseEntity<UserResponse> edit(@PathVariable Long id, @RequestBody BankDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Edit by Id : BankController -");
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
    public ResponseEntity<UserResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        logger.info("- Delete by Id : BankController -");
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
