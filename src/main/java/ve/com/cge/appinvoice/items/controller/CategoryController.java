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
import ve.com.cge.appinvoice.items.model.Category;
import ve.com.cge.appinvoice.items.service.CategoryService;
import ve.com.cge.appinvoice.items.dto.CategoryDTO;
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

/**
 * CategoryController: controller in charge of handling requests related to article categories
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 21, 2024
 */
@RestController
@RequestMapping("/item/category")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryController {
    
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;
    private final JwtService jwtService;
    private final AuditService auditService;

    public CategoryController(CategoryService categoryService, JwtService jwtService, AuditService auditService) {
        this.categoryService = categoryService;
        this.jwtService = jwtService;
        this.auditService = auditService;
    }
        
    @GetMapping(value = "/")
    public List<Category> getCategoriesData() {
        logger.info("- Get list : CategoryController -");
        List<Category> listCategories = new ArrayList<Category>();
        listCategories = categoryService.findCategories();
        return listCategories;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getCategoryData(@PathVariable Long id) {
        logger.info("- Get by Id : CategoryController -");
        CategoryDTO categoryDTO = categoryService.findCategoryById(id);
        if (categoryDTO == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryDTO);        
    }
    
     @GetMapping(value = "/pdf/{id}")
    public ResponseEntity<byte[]> getCategoryByIdPDF(@PathVariable Long id) throws JRException, FileNotFoundException {
        logger.info("- Print PDF by Id : CategoryController -");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("categoryReport", "categoryReport.pdf");
        return ResponseEntity.ok().headers(headers).body(categoryService.exporByIdToPdf(id));
    }
    
    @GetMapping(value = "/pdf")
    public ResponseEntity<byte[]> getCategoryPDF() throws JRException, FileNotFoundException {
        logger.info("- Print list PDF : CategoryController -");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("categoryReport", "categorysReport.pdf");
        return ResponseEntity.ok().headers(headers).body(categoryService.exportListToPdf());
    }
    
    @GetMapping(value = "/xls")
    public ResponseEntity<byte[]> getCategoryXLS() throws JRException, FileNotFoundException {
        logger.info("- Print list XLS : CategoryController -");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        ContentDisposition contentDisposition= ContentDisposition.builder("attachment").filename("categorysReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(categoryService.exportListToXls());
    }
       
    @PostMapping(value = "/add")
    public ResponseEntity<UserResponse> create(@RequestBody CategoryDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Add new : CategoryController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "New information has been added: " + request.getDescription(),
                TransactionType.INSERT,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(categoryService.insertCategory(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> edit(@PathVariable Long id, @RequestBody CategoryDTO request, @RequestHeader("Authorization") String token) {
        logger.info("- Edit by Id : CategoryController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + request.getId().toString() + " and data: " + request.getDescription() + " was updated",
                TransactionType.UPDATE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(categoryService.updateCategory(request, id));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        logger.info("- Delete by Id : CategoryController -");
        String tokenString = jwtService.getTokenFromHeader(token);
        String username = jwtService.getUsernameFromToken(tokenString);
        Audit transaction = new Audit(
                username,
                "The information with the id: " + id + " was deleted",
                TransactionType.DELETE,
                Timestamp.valueOf(LocalDateTime.now()));
        auditService.register(transaction);
        return ResponseEntity.ok(categoryService.deleteCategory(id));        
    }
    
}
