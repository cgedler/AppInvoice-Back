
package ve.com.cge.appinvoice.accpay.dto;

/**
 * ShoppingDetailsDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
public class ShoppingDetailsDTO {
    
    private Integer id;
    private String description;
    private Integer shoppingId;
    private Integer itemId;
    private Integer quantity;
    private double amount;

    public ShoppingDetailsDTO() {
    }

    public ShoppingDetailsDTO(Integer id, String description, Integer shoppingId, Integer itemId, Integer quantity, double amount) {
        this.id = id;
        this.description = description;
        this.shoppingId = shoppingId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.amount = amount;
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

    public Integer getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Integer shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
