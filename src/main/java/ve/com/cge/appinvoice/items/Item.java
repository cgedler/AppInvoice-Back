
package ve.com.cge.appinvoice.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ve.com.cge.appinvoice.ap.ShoppingDetails;
import ve.com.cge.appinvoice.ar.BillingDetails;

/**
 * Item 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@Entity
@Table(name = "items")
public class Item {

    @Id
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
        
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ItemStock stock;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ItemPrice price;
    
    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<ShoppingDetails> shoppingDetails;
     
    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<BillingDetails> billingDetails;

    public Item() {
    }

    public Item(Integer id, String description, Category category, ItemStock stock, ItemPrice price) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.stock= stock;
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
