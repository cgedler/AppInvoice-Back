
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
    private String description;
    private TransactionType transactionType;
    private Timestamp creation;

    public AuditDTO() {
    }

    public AuditDTO(String username, String description, TransactionType transactionType, Timestamp creation) {
        this.username = username;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
