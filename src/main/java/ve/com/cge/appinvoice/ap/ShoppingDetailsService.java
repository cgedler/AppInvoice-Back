
package ve.com.cge.appinvoice.ap;

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
