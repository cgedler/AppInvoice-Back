
package ve.com.cge.appinvoice.config.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * IUserRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 15, 2024
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
