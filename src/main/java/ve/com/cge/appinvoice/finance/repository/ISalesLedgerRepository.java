
package ve.com.cge.appinvoice.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ve.com.cge.appinvoice.finance.model.SalesLedger;

/**
 * ISalesLedgerRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface ISalesLedgerRepository extends JpaRepository<SalesLedger, Integer>  {

}