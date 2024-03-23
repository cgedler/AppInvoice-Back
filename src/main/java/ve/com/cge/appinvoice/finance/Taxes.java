
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
 * Taxes 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "taxes")
public class Taxes {
    
    @Id
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "taxes")
    @JsonIgnore
    private List<Billing> billing;

    @OneToMany(mappedBy = "taxes")
    @JsonIgnore
    private List<Shopping> shopping;

    @Column(name = "tax", nullable = false)
    private double tax;

    public Taxes() {
    }

    public Taxes(Integer id, String description, double tax) {
        this.id = id;
        this.description = description;
        this.tax = tax;
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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

}
