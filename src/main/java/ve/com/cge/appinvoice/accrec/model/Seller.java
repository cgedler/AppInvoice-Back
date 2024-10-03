
package ve.com.cge.appinvoice.accrec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sellers 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "sellers")
public class Seller {
    
    @Id
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private List<Billing> billing;

    public Seller() {
    }

    public Seller(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
