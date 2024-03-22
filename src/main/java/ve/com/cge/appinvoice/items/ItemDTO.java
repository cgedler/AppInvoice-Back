
package ve.com.cge.appinvoice.items;

/**
 * ItemDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 21, 2024
 */
public class ItemDTO {
    
    private Integer id;
    private String description;
    private Category category;
    private ItemStock stock;
    private ItemPrice price; 

    public ItemDTO() {
    }

    public ItemDTO(Integer id, String description, Category category, ItemStock stock, ItemPrice price) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.price = price;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ItemStock getStock() {
        return stock;
    }

    public void setStock(ItemStock stock) {
        this.stock = stock;
    }

    public ItemPrice getPrice() {
        return price;
    }

    public void setPrice(ItemPrice price) {
        this.price = price;
    }

}
