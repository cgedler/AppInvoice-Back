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

package ve.com.cge.appinvoice.items.service;

import ve.com.cge.appinvoice.items.dto.CategoryDTO;
import ve.com.cge.appinvoice.items.repository.ICategoryRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;
import ve.com.cge.appinvoice.items.model.Category;

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
       
    public CategoryDTO findCategoryById(Long id) {
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
    public UserResponse updateCategory(CategoryDTO request, Long id) {
        Category category = new Category(id, request.getDescription());
        categoryRepository.save(category);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   
    
}
