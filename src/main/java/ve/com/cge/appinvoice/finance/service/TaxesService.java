
package ve.com.cge.appinvoice.finance.service;

import ve.com.cge.appinvoice.finance.dto.TaxesDTO;
import ve.com.cge.appinvoice.finance.model.Taxes;
import ve.com.cge.appinvoice.finance.repository.ITaxesRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * TaxesService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class TaxesService {

    private final ITaxesRepository taxesRepository;

    public TaxesService(ITaxesRepository taxesRepository) {
        this.taxesRepository = taxesRepository;
    }
    
    public List<Taxes> findTaxess() {
        List<Taxes> taxesList = taxesRepository.findAll();
        return taxesList;
    }
       
    public TaxesDTO findTaxesById(Integer id) {
        Taxes taxes = taxesRepository.findById(id).orElse(null);
        if (taxes != null) {
            return new TaxesDTO(taxes.getId(), taxes.getDescription(), taxes.getTax());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertTaxes(TaxesDTO request) {
        Taxes taxes = new Taxes(request.getId(),request.getDescription(), request.getTax());
        taxesRepository.save(taxes);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateTaxes(TaxesDTO request, Integer id) {
        Taxes taxes = new Taxes(id, request.getDescription(), request.getTax());
        taxesRepository.save(taxes);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteTaxes(Integer id) {
        taxesRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   

}
