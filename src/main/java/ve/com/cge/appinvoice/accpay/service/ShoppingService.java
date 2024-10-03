
package ve.com.cge.appinvoice.accpay.service;

import ve.com.cge.appinvoice.accpay.model.Shopping;
import ve.com.cge.appinvoice.accpay.repository.IShoppingRepository;
import ve.com.cge.appinvoice.accpay.dto.ShoppingDTO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;

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
    
    public List<Shopping> findShoppings() {
        List<Shopping> shoppingList = shoppingRepository.findAll();
        return shoppingList;
    }
       
    public ShoppingDTO findShoppingById(Integer id) {
        Shopping shopping = shoppingRepository.findById(id).orElse(null);
        if (shopping != null) {
            return new ShoppingDTO(
                    shopping.getId(),
                    shopping.getDescription(),
                    shopping.getShoppingDetails(),
                    shopping.getSupplier(),
                    shopping.getBank(),
                    shopping.getTaxes(),
                    shopping.getDate(),
                    shopping.getSubTotal(),
                    shopping.getAmountTax(),
                    shopping.getTotal());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertShopping(ShoppingDTO request) {
        Shopping shopping = new Shopping(
                request.getDescription(),
                request.getShoppingDetails(),
                request.getSupplier(),
                request.getBank(),
                request.getTaxes(),
                request.getDate(),
                request.getSubTotal(),
                request.getAmountTax(),
                request.getTotal());
        shoppingRepository.save(shopping);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateShopping(ShoppingDTO request, Integer id) {
        Shopping shopping = new Shopping(
                id,
                request.getDescription(),
                request.getShoppingDetails(),
                request.getSupplier(),
                request.getBank(),
                request.getTaxes(),
                request.getDate(),
                request.getSubTotal(),
                request.getAmountTax(),
                request.getTotal());
        shoppingRepository.save(shopping);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteShopping(Integer id) {
        shoppingRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }
    
}
