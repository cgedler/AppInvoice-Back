
package ve.com.cge.appinvoice.accpay.dto;

/**
 * SupplierDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
public class SupplierDTO {
    
    private Integer id;
    private String description;

    public SupplierDTO() {
    }

    public SupplierDTO(Integer id, String description) {
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
