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

package ve.com.cge.appinvoice.accpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ve.com.cge.appinvoice.finance.model.Bank;
import ve.com.cge.appinvoice.finance.model.Taxes;

/**
 * Shopping entity 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "AccountsPayable.Shopping")
public class Shopping implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "description")
    private String description;
    
    @OneToMany(fetch=FetchType.EAGER,  mappedBy = "shopping", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<ShoppingDetails> shoppingDetails = new ArrayList<ShoppingDetails>();
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    
    @ManyToOne
    @JoinColumn(name = "taxes_id")
    private Taxes taxes;
    
    @Column(name = "date")
    private Timestamp date;
    
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    
    @Column(name = "amount_tax")
    private BigDecimal amount_tax;
    
    @Column(name = "total")
    private BigDecimal total;

    public Shopping() {
    }

    public Shopping(String description, List<ShoppingDetails> shoppingDetails, Supplier supplier, Bank bank, Taxes taxes, Timestamp date, BigDecimal subtotal, BigDecimal amount_tax, BigDecimal total) {
        this.description = description;
        this.shoppingDetails = shoppingDetails;
        this.supplier = supplier;
        this.bank = bank;
        this.taxes = taxes;
        this.date = date;
        this.subtotal = subtotal;
        this.amount_tax = amount_tax;
        this.total = total;
    }

    public Shopping(Long id, String description, List<ShoppingDetails> shoppingDetails, Supplier supplier, Bank bank, Taxes taxes, Timestamp date, BigDecimal subtotal, BigDecimal amount_tax, BigDecimal total) {
        this.id = id;
        this.description = description;
        this.shoppingDetails = shoppingDetails;
        this.supplier = supplier;
        this.bank = bank;
        this.taxes = taxes;
        this.date = date;
        this.subtotal = subtotal;
        this.amount_tax = amount_tax;
        this.total = total;
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

    public List<ShoppingDetails> getShoppingDetails() {
        return shoppingDetails;
    }

    public void setShoppingDetails(List<ShoppingDetails> shoppingDetails) {
        this.shoppingDetails = shoppingDetails;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @JsonProperty("subtotal")
    public BigDecimal getSubTotal() {
        return subtotal;
    }

    @JsonProperty("subtotal")
    public void setSubTotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @JsonProperty("amount_tax")
    public BigDecimal getAmountTax() {
        return amount_tax;
    }

    @JsonProperty("amount_tax")
    public void setAmountTax(BigDecimal amount_tax) {
        this.amount_tax = amount_tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Shopping{" + "id=" + id + ", description=" + description + ", shoppingDetails=" + shoppingDetails + ", supplier=" + supplier + ", bank=" + bank + ", taxes=" + taxes + ", date=" + date + ", subtotal=" + subtotal + ", amount_tax=" + amount_tax + ", total=" + total + '}';
    }

}
