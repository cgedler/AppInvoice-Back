
package ve.com.cge.appinvoice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ve.com.cge.appinvoice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findOneByEmail(String email);
}
