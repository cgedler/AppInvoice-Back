
package ve.com.cge.appinvoice.finance.service;

import ve.com.cge.appinvoice.finance.model.PurchaseLedger;
import ve.com.cge.appinvoice.finance.repository.IPurchaseLedgerRepository;
import org.springframework.stereotype.Service;

/**
 * PurchaseLedgerService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class PurchaseLedgerService {
    
    private final IPurchaseLedgerRepository purchaseLedgerRepository; 

    public PurchaseLedgerService(IPurchaseLedgerRepository purchaseLedgerRepository) {
        this.purchaseLedgerRepository = purchaseLedgerRepository;
    }
    
    public void register(PurchaseLedger transactionData) {
        purchaseLedgerRepository.save(transactionData);
    }

}
