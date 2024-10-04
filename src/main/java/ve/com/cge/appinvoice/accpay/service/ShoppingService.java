/* 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
