
package ve.com.cge.appinvoice.finance;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ISalesLedgerRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface ISalesLedgerRepository extends JpaRepository<SalesLedger, Integer>  {

}
