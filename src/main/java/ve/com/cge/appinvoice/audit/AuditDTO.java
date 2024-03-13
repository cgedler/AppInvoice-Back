
package ve.com.cge.appinvoice.audit;

import java.sql.Timestamp;

/**
 * AuditDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 13, 2024
 */
public class AuditDTO {
    
    private Integer id;
    private String username;
    private String transaction;
    private TransactionType transactionType;
    private Timestamp creation;

    public AuditDTO() {
    }

    public AuditDTO(Integer id, String username, String transaction, TransactionType transactionType, Timestamp creation) {
        this.id = id;
        this.username = username;
        this.transaction = transaction;
        this.transactionType = transactionType;
        this.creation = creation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Timestamp getCreation() {
        return creation;
    }

    public void setCreation(Timestamp creation) {
        this.creation = creation;
    }
    
}
