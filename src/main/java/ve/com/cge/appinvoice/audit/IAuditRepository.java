
package ve.com.cge.appinvoice.audit;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * IAuditRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 13, 2024
 */
public interface IAuditRepository extends JpaRepository<Audit, Integer> {
    
    @Modifying()
    @Query(value = "INSERT INTO audit (username, transaction, transactiontype, creation) VALUES (?,?,?,?)", nativeQuery = true)
    void save(
            @Param(value = "username") String username,
            @Param(value = "transaction") String transaction,
            @Param(value = "transactiontype") TransactionType transactionType,
            @Param(value = "creation") Timestamp creation);

}
