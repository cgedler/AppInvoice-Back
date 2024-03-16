
package ve.com.cge.appinvoice.audit;

import org.springframework.stereotype.Service;

/**
 * AuditService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 13, 2024
 */
@Service
public class AuditService {
    
    private final IAuditRepository auditRepository;

    public AuditService(IAuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
    
    public void register(Audit transactionData) {
        auditRepository.save(transactionData);
    }

}
