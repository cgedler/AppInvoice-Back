
package ve.com.cge.appinvoice.items;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * ICategoryRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findById(int id);
    
    @Modifying()
    @Query(value = "INSERT INTO category (description) VALUES (?)", nativeQuery = true)
    void insert(@Param(value = "description") String description);

    @Modifying()
    @Query(value = "UPDATE category c set c.description =:description where c.id=:id")
    void update(@Param("description") String description, @Param("id") int id);
    
    /*
    @Modifying
    @Query("update User u set u.active = false where u.lastLoginDate < :date")
    void deactivateUsersNotLoggedInSince(@Param("date") LocalDate date);

    @Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);

    */
    
}
