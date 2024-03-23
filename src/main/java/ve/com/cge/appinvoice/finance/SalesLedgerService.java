
package ve.com.cge.appinvoice.finance;

import org.springframework.stereotype.Service;

/**
 * SalesLedgerService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class SalesLedgerService {
    
    private final ISalesLedgerRepository salesLedgerRepository;

    public SalesLedgerService(ISalesLedgerRepository salesLedgerRepository) {
        this.salesLedgerRepository = salesLedgerRepository;
    }
    
    public void register(SalesLedger transactionData) {
        salesLedgerRepository.save(transactionData);
    }

}
