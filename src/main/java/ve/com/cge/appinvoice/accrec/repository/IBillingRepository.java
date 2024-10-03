
package ve.com.cge.appinvoice.accrec.repository;

import ve.com.cge.appinvoice.accrec.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IBillingRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface IBillingRepository extends JpaRepository<Billing, Integer> {

}
