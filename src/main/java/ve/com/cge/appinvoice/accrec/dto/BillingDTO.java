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

package ve.com.cge.appinvoice.accrec.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import ve.com.cge.appinvoice.accrec.model.BillingDetails;
import ve.com.cge.appinvoice.accrec.model.Customer;
import ve.com.cge.appinvoice.accrec.model.Seller;
import java.sql.Timestamp;
import java.util.List;
import ve.com.cge.appinvoice.finance.model.Bank;
import ve.com.cge.appinvoice.finance.model.Taxes;

/**
 * BillingDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingDTO {
 
    private Long id;
    private String description;
    private List<BillingDetails> billingDetails;
    private Customer customer;
    private Seller seller;
    private Bank bank;
    private Taxes taxes;
    private Timestamp date;
    private BigDecimal subtotal;
    private BigDecimal amount_tax;
    private BigDecimal total;

    public BillingDTO() {
    }

    public BillingDTO(Long id, String description, List<BillingDetails> billingDetails, Customer customer, Seller seller, Bank bank, Taxes taxes, Timestamp date, BigDecimal subtotal, BigDecimal amount_tax, BigDecimal total) {
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

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getSubTotal() {
        return subtotal;
    }

    public void setSubTotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getAmountTax() {
        return amount_tax;
    }

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
        return "BillingDTO{" + "id=" + id + ", description=" + description + ", billingDetails=" + billingDetails + ", customer=" + customer + ", seller=" + seller + ", bank=" + bank + ", taxes=" + taxes + ", date=" + date + ", subtotal=" + subtotal + ", amount_tax=" + amount_tax + ", total=" + total + '}';
    }

}
