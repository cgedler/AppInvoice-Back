
package ve.com.cge.appinvoice.items.dto;

/**
 * CategoryDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
public class CategoryDTO {

    private Integer id;
    private String description;
    
    public CategoryDTO() {
        
    }

    public CategoryDTO(String description) {
        this.description = description;
    }

    public CategoryDTO(Integer id, String description) {
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
