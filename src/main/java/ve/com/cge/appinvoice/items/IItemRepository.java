
package ve.com.cge.appinvoice.items;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * IItemRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
public interface IItemRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findById(int id);
     
    @Modifying()
    @Query(value = "INSERT INTO items (description) VALUES (?)", nativeQuery = true)
    void insert(@Param(value = "description") String description);
     
}
