
package ve.com.cge.appinvoice.accrec.repository;

import ve.com.cge.appinvoice.accrec.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ICustomerRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
