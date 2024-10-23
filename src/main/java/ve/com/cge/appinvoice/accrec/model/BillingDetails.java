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

package ve.com.cge.appinvoice.accrec.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ve.com.cge.appinvoice.items.model.Item;

/**
 * BillingDetails entity
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "AccountsReceivable.BillingDetails")
public class BillingDetails implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "billing_id")
    private Billing billing;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public BillingDetails() {
    }

    public BillingDetails(String description, Billing billing, Item item, Integer quantity, BigDecimal amount) {
        this.description = description;
        this.billing = billing;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
    }

    public BillingDetails(Long id, String description, Billing billing, Item item, Integer quantity, BigDecimal amount) {
        this.id = id;
        this.description = description;
        this.billing = billing;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BillingDetails{" + "id=" + id + ", description=" + description + ", billing=" + billing + ", item=" + item + ", quantity=" + quantity + ", amount=" + amount + '}';
    }

}
