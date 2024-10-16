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

import ve.com.cge.appinvoice.accpay.dto.SupplierDTO;
import ve.com.cge.appinvoice.accpay.model.Supplier;
import ve.com.cge.appinvoice.accpay.repository.ISupplierRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * SupplierService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Service
public class SupplierService {

    private final ISupplierRepository supplierRepository;
    
    public SupplierService(ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    public List<Supplier> findSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList;
    }
       
    public SupplierDTO findSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null) {
            return new SupplierDTO(supplier.getId(), supplier.getDescription());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertSupplier(SupplierDTO request) {
        Supplier supplier = new Supplier(request.getId(),request.getDescription());
        supplierRepository.save(supplier);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateSupplier(SupplierDTO request, Long id) {
        Supplier supplier = new Supplier(id, request.getDescription());
        supplierRepository.save(supplier);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   
  
}
