
package ve.com.cge.appinvoice.finance.repository;

import ve.com.cge.appinvoice.finance.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IBankRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface IBankRepository extends JpaRepository<Bank, Integer> {

}
