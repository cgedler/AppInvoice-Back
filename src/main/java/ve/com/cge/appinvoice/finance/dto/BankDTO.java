
package ve.com.cge.appinvoice.finance.dto;

/**
 * BankDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
public class BankDTO {

    private Integer id;
    private String description;
    private Integer number;

    public BankDTO() {
    }

    public BankDTO(Integer id, String description, Integer number) {
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
