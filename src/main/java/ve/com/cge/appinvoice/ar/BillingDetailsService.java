
package ve.com.cge.appinvoice.ar;

import org.springframework.stereotype.Service;

/**
 * BillingDetailsService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class BillingDetailsService {

    private final IBillingDetailsRepository billingDetailsRepository;

    public BillingDetailsService(IBillingDetailsRepository billingDetailsRepository) {
        this.billingDetailsRepository = billingDetailsRepository;
    }
    
    
}
