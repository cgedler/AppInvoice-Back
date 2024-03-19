
package ve.com.cge.appinvoice.audit;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * IAuditRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 13, 2024
 */
public interface IAuditRepository extends JpaRepository<Audit, Integer> {

}
