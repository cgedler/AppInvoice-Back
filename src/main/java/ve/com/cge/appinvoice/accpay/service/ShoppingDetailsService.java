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

import java.util.ArrayList;
import java.util.List;
import ve.com.cge.appinvoice.accpay.repository.IShoppingDetailsRepository;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.accpay.model.ShoppingDetails;

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
    
    public List<ShoppingDetails> findAllById(Long id) {
         List<ShoppingDetails> list = new ArrayList<ShoppingDetails>();
         list = shoppingDetailsRepository.findAllById(id);
         
         return list;
         
         
    }
   
}
