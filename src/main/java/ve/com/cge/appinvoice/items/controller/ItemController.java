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
 
package ve.com.cge.appinvoice.items.controller;

import java.io.FileNotFoundException;
import ve.com.cge.appinvoice.items.dto.ItemDTO;
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
import ve.com.cge.appinvoice.items.model.Item;
import ve.com.cge.appinvoice.items.service.ItemService;

/**
 * ItemController: controller in charge of handling requests related to articles
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@RestController
@RequestMapping("/item")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ItemController {
    
    private static Logger logger = LoggerFactory.getLogger(ItemController.class);
    
    private final JwtService jwtService;
    private final AuditService auditService;
    private final ItemService itemService;

    public ItemController(JwtService jwtService, AuditService auditService, ItemService itemService) {
        this.jwtService = jwtService;
        this.auditService = auditService;
        this.itemService = itemService;
    }
  
    @GetMapping(value = "/item")
    public List<Item> getItemsData() {
        logger.info("- Get list : ItemController -");
        List<Item> listItems= new ArrayList<Item>();
        listItems = itemService.findItems();
        return listItems;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDTO> getItemData(@PathVariable Long id) {
        logger.info("- Get by Id : ItemController -");
        ItemDTO itemDTO = itemService.findItemById(id);
        if (itemDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDTO);        
    }
    
    @GetMapping(value = "/categoryid/{id}")
    public List<Item> getItemDataByCategoryId(@PathVariable Long id) {
        logger.info("- Get by Category Id : ItemController -");
        List<Item> listItems = new ArrayList<Item>();
        listItems = itemService.findItemByCategoryId(id);
        return listItems;        
    }
    
     @GetMapping(value = "/pdf/{id}")
    public ResponseEntity<byte[]> getItemByIdPDF(@PathVariable Long id) throws JRException, FileNotFoundException {
        logger.info("- Print PDF by Id : ItemController -");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("itemReport", "itemReport.pdf");
        return ResponseEntity.ok().headers(headers).body(itemService.exporByIdToPdf(id));
    }
    
    @GetMapping(value = "/pdf")
    public ResponseEntity<byte[]> getItemPDF() throws JRException, FileNotFoundException {
        logger.info("- Print list PDF : ItemController -");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("itemReport", "itemsReport.pdf");
        return ResponseEntity.ok().headers(headers).body(itemService.exportListToPdf());
    }
    
    @GetMapping(value = "/xls")
    public ResponseEntity<byte[]> getItemXLS() throws JRException, FileNotFoundException {
        logger.info("- Print list XLS : ItemController -");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        ContentDisposition contentDisposition= ContentDisposition.builder("attachment").filename("itemsReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(itemService.exportListToXls());
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody ItemDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Add new : ItemController -");
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
    public ResponseEntity<UserResponse> edit(@PathVariable Long id, @RequestBody ItemDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Edit by Id : ItemController -");
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
    public ResponseEntity<UserResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        logger.info("- Delete by Id : ItemController -");
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
