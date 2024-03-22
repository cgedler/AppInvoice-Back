
package ve.com.cge.appinvoice.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Category 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@Entity
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = {"description"})})
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
      
    @OneToOne(mappedBy="category")
    @JoinColumn(name="item_id")
    @JsonIgnore
    private Item item;
    
    public Category() {
        
    }

    public Category(String description) {
        this.description = description;
    }

    public Category(Integer id, String description) {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
}
