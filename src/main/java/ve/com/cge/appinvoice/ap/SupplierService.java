
package ve.com.cge.appinvoice.ap;

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
       
    public SupplierDTO findSupplierById(Integer id) {
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
    public UserResponse updateSupplier(SupplierDTO request, Integer id) {
        Supplier supplier = new Supplier(id, request.getDescription());
        supplierRepository.save(supplier);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   
  
}
