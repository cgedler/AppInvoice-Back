
package ve.com.cge.appinvoice.items;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    public UserResponse saveCategory(CategoryDTO request) {
        Category category = new Category(request.getDescription());
        categoryRepository.save(category.getDescription());
        return new UserResponse("The new category was create");
    }
    
    public UserResponse findCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        String categoryListAsString = ""; 
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            categoryListAsString = objectMapper.writeValueAsString(categoryList);
        } catch(JsonProcessingException e) {
	}
        return new UserResponse(categoryListAsString);
    }
    
    /*
    findById(
            
            
            
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
