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
 * Billing entity
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "AccountsReceivable.Billing")
public class Billing implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(fetch=FetchType.EAGER,  mappedBy = "billing", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BillingDetails> billingDetails = new ArrayList<BillingDetails>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    
    @ManyToOne
    @JoinColumn(name = "taxes_id")
    private Taxes taxes;
    
    @Column(name = "date", nullable = false)
    private Timestamp date;
    
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;
    
    @Column(name = "amount_tax", nullable = false)
    private BigDecimal amount_tax;
    
    @Column(name = "total", nullable = false)
    private BigDecimal total;

    public Billing() {
    }

    public Billing(String description, List<BillingDetails> billingDetails, Customer customer, Seller seller, Bank bank, Taxes taxes, Timestamp date, BigDecimal subtotal, BigDecimal amount_tax, BigDecimal total) {
        this.description = description;
        this.billingDetails = billingDetails;
        this.customer = customer;
        this.seller = seller;
        this.bank = bank;
        this.taxes = taxes;
        this.date = date;
        this.subtotal = subtotal;
        this.amount_tax = amount_tax;
        this.total = total;
    }

    public Billing(Long id, String description, List<BillingDetails> billingDetails, Customer customer, Seller seller, Bank bank, Taxes taxes, Timestamp date, BigDecimal subtotal, BigDecimal amount_tax, BigDecimal total) {
        this.id = id;
        this.description = description;
        this.billingDetails = billingDetails;
        this.customer = customer;
        this.seller = seller;
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

    public List<BillingDetails> getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(List<BillingDetails> billingDetails) {
        this.billingDetails = billingDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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
        return "Billing{" + "id=" + id + ", description=" + description + ", billingDetails=" + billingDetails + ", customer=" + customer + ", seller=" + seller + ", bank=" + bank + ", taxes=" + taxes + ", date=" + date + ", subtotal=" + subtotal + ", amount_tax=" + amount_tax + ", total=" + total + '}';
    }

}
