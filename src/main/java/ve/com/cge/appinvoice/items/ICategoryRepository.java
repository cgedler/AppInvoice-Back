
package ve.com.cge.appinvoice.items;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ve.com.cge.appinvoice.audit.TransactionType;


/**
 * ICategoryRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying()
    @Query(value = "INSERT INTO category (description) VALUES (?)", nativeQuery = true)
    void save(@Param(value = "description") String description);

}
