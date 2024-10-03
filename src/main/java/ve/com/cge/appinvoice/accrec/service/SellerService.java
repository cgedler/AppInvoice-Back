
package ve.com.cge.appinvoice.accrec.service;

import ve.com.cge.appinvoice.accrec.model.Seller;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.accrec.repository.ISellerRepository;
import ve.com.cge.appinvoice.accrec.dto.SellerDTO;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * SellerService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class SellerService {
    
    private final ISellerRepository sellerRepository;

    public SellerService(ISellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    
    public List<Seller> findSellers() {
        List<Seller> sellerList = sellerRepository.findAll();
        return sellerList;
    }
       
    public SellerDTO findSellerById(Integer id) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller != null) {
            return new SellerDTO(seller.getId(), seller.getDescription());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertSeller(SellerDTO request) {
        Seller seller = new Seller(request.getId(),request.getDescription());
        sellerRepository.save(seller);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateSeller(SellerDTO request, Integer id) {
        Seller seller = new Seller(id, request.getDescription());
        sellerRepository.save(seller);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteSeller(Integer id) {
        sellerRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }

}
