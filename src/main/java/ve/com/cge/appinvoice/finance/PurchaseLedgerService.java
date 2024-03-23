
package ve.com.cge.appinvoice.finance;

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
