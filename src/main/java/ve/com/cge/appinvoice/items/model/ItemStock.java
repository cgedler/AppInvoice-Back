
package ve.com.cge.appinvoice.items.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * ItemStock 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@Entity
@Table(name = "stock")
public class ItemStock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @OneToOne(mappedBy = "stock") //, cascade=CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @JsonIgnore
    private Item item;

    public ItemStock() {
    }

    public ItemStock(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ItemStock(Integer id, Integer quantity, Item item) {
        this.id = id;
        this.quantity = quantity;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
}