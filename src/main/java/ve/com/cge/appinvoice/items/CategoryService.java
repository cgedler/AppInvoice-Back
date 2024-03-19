
package ve.com.cge.appinvoice.items;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ve.com.cge.appinvoice.config.user.User;
import ve.com.cge.appinvoice.config.user.UserDTO;
import ve.com.cge.appinvoice.config.user.UserResponse;


/**
 * CategoryService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@Service
public class CategoryService {
    
    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public UserResponse insertCategory(CategoryDTO request) {
        Category category = new Category(request.getDescription());
        categoryRepository.insert(category.getDescription());
        return new UserResponse("The new category was create");
    }
    
    public List<Category> findCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
       
    public CategoryDTO findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            return new CategoryDTO(category.getId(), category.getDescription());
        }
        return null;
    }
            
      
    
    
    /*        
    @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> getAll() {
        List<Entity> entityList = entityManager.findAll();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (Entity n : entityList) {
            JSONObject Entity = new JSONObject();
            entity.put("id", n.getId());
            entity.put("address", n.getAddress());
            entities.add(entity);
        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    
    */
    
    
}
