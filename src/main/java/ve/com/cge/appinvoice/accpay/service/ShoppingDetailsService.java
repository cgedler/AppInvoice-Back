
package ve.com.cge.appinvoice.accpay.service;

import ve.com.cge.appinvoice.accpay.repository.IShoppingDetailsRepository;
import org.springframework.stereotype.Service;

/**
 * ShoppingDetailsService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class ShoppingDetailsService {

    private final IShoppingDetailsRepository shoppingDetailsRepository;

    public ShoppingDetailsService(IShoppingDetailsRepository shoppingDetailsRepository) {
        this.shoppingDetailsRepository = shoppingDetailsRepository;
    }

}
