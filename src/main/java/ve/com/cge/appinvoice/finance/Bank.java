
package ve.com.cge.appinvoice.finance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ve.com.cge.appinvoice.ap.Shopping;
import ve.com.cge.appinvoice.ar.Billing;

/**
 * Bank 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "bank")
public class Bank {
    
    @Id
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<Billing> billing;

    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<Shopping> shopping;

    @Column(name = "number", nullable = false)
    private Integer number;

    public Bank() {
    }

    public Bank(Integer id, String description, Integer number) {
        this.id = id;
        this.description = description;
        this.number = number;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
}
