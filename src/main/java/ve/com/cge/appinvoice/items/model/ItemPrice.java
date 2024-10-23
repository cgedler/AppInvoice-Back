/* 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ve.com.cge.appinvoice.items.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * ItemPrice entity
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@Entity
@Table(name = "Inventory.Price")
public class ItemPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @JsonIgnore
    private Item item;
    
    public ItemPrice() {
    }

    public ItemPrice(BigDecimal price) {
        this.price = price;
    }

    public ItemPrice(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public ItemPrice(Long id, BigDecimal price, Item item) {
        this.id = id;
        this.price = price;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemPrice{" + "id=" + id + ", price=" + price + ", item=" + item + '}';
    }

}
