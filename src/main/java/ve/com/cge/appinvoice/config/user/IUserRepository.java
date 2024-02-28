
package ve.com.cge.appinvoice.config.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * IUserRepository : 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 15, 2024
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    
    Optional<User> findById(int id);
    
    @Modifying()
    @Query("update User u set u.username=:username, u.name=:name, u.password=:password where u.id = :id")
    void updateUser(@Param(value = "id") Integer id,   @Param(value = "username") String username, @Param(value = "name") String name , @Param(value = "password") String password);

}
