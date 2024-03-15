
package ve.com.cge.appinvoice.items;

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
    
    private final ICategoryRepository categoryRepository ;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public UserResponse saveCategory(CategoryDTO request) {
        Category category = new Category(request.getDescription());
        categoryRepository.save(category.getDescription());
        return new UserResponse("The new category was create");
    }
    
}
