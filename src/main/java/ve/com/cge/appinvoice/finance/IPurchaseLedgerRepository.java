
package ve.com.cge.appinvoice.finance;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IPurchaseLedgerRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface IPurchaseLedgerRepository extends JpaRepository<PurchaseLedger, Integer>  {

}
