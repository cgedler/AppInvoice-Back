
package ve.com.cge.appinvoice.accrec.repository;

import ve.com.cge.appinvoice.accrec.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ISellerRepository 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public interface ISellerRepository extends JpaRepository<Seller, Integer>  {

}
