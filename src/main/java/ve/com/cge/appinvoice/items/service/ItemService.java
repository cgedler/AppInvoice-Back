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

import ve.com.cge.appinvoice.items.dto.ItemDTO;
import ve.com.cge.appinvoice.items.repository.IItemRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;
import ve.com.cge.appinvoice.items.model.Item;

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

    public ItemDTO findItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            return new ItemDTO(item.getId(), item.getDescription(), item.getCategory(), item.getStock(), item.getPrice());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertItem(ItemDTO request) {
        Item item = new Item(request.getId(), request.getDescription(), request.getCategory(), request.getStock(), request.getPrice());
        itemRepository.save(item);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateItem(ItemDTO request, Long id) {
        Item item = new Item(request.getId(), request.getDescription(), request.getCategory(), request.getStock(), request.getPrice());
        itemRepository.save(item);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteItem(Long id) {
        itemRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   

}
