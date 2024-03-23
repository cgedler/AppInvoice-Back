
package ve.com.cge.appinvoice.items;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
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
    
    @Transactional
    public UserResponse insertCategory(CategoryDTO request) {
        Category category = new Category(request.getDescription());
        categoryRepository.insert(category.getDescription());
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateCategory(CategoryDTO request, Integer id) {
        Category category = new Category(id, request.getDescription());
        categoryRepository.save(category);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   
    
}
