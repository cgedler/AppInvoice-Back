
package ve.com.cge.appinvoice.ap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ve.com.cge.appinvoice.items.Item;

/**
 * ShoppingDetails 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "shoppingdetails")
public class ShoppingDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "shopping_id")
    private Shopping shopping;
    
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
        
    @Column(name = "amount", nullable = false)
    private double amount;

    public ShoppingDetails() {
    }

    public ShoppingDetails(String description, Shopping shopping, Item item, Integer quantity, double amount) {
        this.description = description;
        this.shopping = shopping;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
    }

    public ShoppingDetails(Integer id, String description, Shopping shopping, Item item, Integer quantity, double amount) {
        this.id = id;
        this.description = description;
        this.shopping = shopping;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
