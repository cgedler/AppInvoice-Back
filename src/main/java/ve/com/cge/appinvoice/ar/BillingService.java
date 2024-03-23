
package ve.com.cge.appinvoice.ar;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * BillingService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class BillingService {
    
    private final IBillingRepository billingRepository;

    public BillingService(IBillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }
    
    public List<Billing> findBillings() {
        List<Billing> billingList = billingRepository.findAll();
        return billingList;
    }
       
    public BillingDTO findBillingById(Integer id) {
        Billing billing = billingRepository.findById(id).orElse(null);
        if (billing != null) {
            return new BillingDTO(
                    billing.getId(),
                    billing.getDescription(),
                    billing.getBillingDetails(),
                    billing.getCustomer(),
                    billing.getSeller(),
                    billing.getBank(),
                    billing.getTaxes(),
                    billing.getDate(),
                    billing.getSubTotal(),
                    billing.getAmountTax(),
                    billing.getTotal());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertBilling(BillingDTO request) {
        Billing billing = new Billing(
                request.getDescription(),
                request.getBillingDetails(),
                request.getCustomer(),
                request.getSeller(),
                request.getBank(),
                request.getTaxes(),
                request.getDate(),
                request.getSubTotal(),
                request.getAmountTax(),
                request.getTotal());
        billingRepository.save(billing);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateBilling(BillingDTO request, Integer id) {
        Billing billing = new Billing(
                id,
                request.getDescription(),
                request.getBillingDetails(),
                request.getCustomer(),
                request.getSeller(),
                request.getBank(),
                request.getTaxes(),
                request.getDate(),
                request.getSubTotal(),
                request.getAmountTax(),
                request.getTotal());
        billingRepository.save(billing);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteBilling(Integer id) {
        billingRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }

}
