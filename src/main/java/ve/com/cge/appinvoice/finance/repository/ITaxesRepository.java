
package ve.com.cge.appinvoice.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ve.com.cge.appinvoice.finance.model.Taxes;

/**
 * ITaxesRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface ITaxesRepository extends JpaRepository<Taxes, Integer> {

}
