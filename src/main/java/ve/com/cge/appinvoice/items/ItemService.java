
package ve.com.cge.appinvoice.items;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * ItemService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@Service
public class ItemService {

    private final IItemRepository itemRepository;

    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public List<Item> findItems() {
        List<Item> itemsList = itemRepository.findAll();
        return itemsList;
    }

    /*
    
      public List<Category> findCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
    
    */
    
    public ItemDTO findItemById(Integer id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            return new ItemDTO(item.getId(), item.getDescription(), item.getCategory(), item.getStock(), item.getPrice());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertItem(ItemDTO request) {
        //Item item = new Item(request.getDescription());
        //itemRepository.insert(item.getDescription());
        Item item = new Item(request.getId(), request.getDescription(), request.getCategory(), request.getStock(), request.getPrice());
        itemRepository.save(item);
        return new UserResponse("The new data was create");
    }

}
