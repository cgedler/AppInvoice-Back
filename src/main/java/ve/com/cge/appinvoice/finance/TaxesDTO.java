
package ve.com.cge.appinvoice.finance;

/**
 * TaxesDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public class TaxesDTO {

    private Integer id;
    private String description;
    private double tax;

    public TaxesDTO() {
    }

    public TaxesDTO(Integer id, String description, double tax) {
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
