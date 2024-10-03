
package ve.com.cge.appinvoice.accrec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ve.com.cge.appinvoice.accrec.model.BillingDetails;

/**
 * IBillingDetailsRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface IBillingDetailsRepository extends JpaRepository<BillingDetails, Integer> {

}
