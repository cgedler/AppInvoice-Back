
package ve.com.cge.appinvoice.items.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ve.com.cge.appinvoice.items.model.Item;

/**
 * IItemRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
public interface IItemRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findById(int id);

}
