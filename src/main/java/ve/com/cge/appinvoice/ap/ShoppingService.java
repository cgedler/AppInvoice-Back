
package ve.com.cge.appinvoice.ap;

import org.springframework.stereotype.Service;

/**
 * ShoppingService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Service
public class ShoppingService {

    private final IShoppingRepository shoppingRepository;

    public ShoppingService(IShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }
    
    
    
}
