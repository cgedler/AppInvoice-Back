
package ve.com.cge.appinvoice.items;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private Category categoryId;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private ItemStock stockId;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private ItemPrice priceId;
    
    
    
    
}
