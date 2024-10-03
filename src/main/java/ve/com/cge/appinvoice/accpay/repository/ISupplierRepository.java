
package ve.com.cge.appinvoice.accpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ve.com.cge.appinvoice.accpay.model.Supplier;

/**
 * ISupplierRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
public interface ISupplierRepository extends JpaRepository<Supplier, Integer> {

}
