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

package ve.com.cge.appinvoice.accpay.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import ve.com.cge.appinvoice.accpay.model.ShoppingDetails;
import ve.com.cge.appinvoice.accpay.model.Supplier;
import ve.com.cge.appinvoice.finance.model.Bank;
import ve.com.cge.appinvoice.finance.model.Taxes;

/**
 * ShoppingDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
public class ShoppingDTO {
 
    private Long id;
    private String description;
    private List<ShoppingDetails> shoppingDetails;
    private Supplier supplier;
    private Bank bank;
    private Taxes taxes;
    private Timestamp date;
    private BigDecimal subTotal;
    private BigDecimal amountTax;
    private BigDecimal total;

    public ShoppingDTO() {
    }

    public ShoppingDTO(Long id, String description, List<ShoppingDetails> shoppingDetails, Supplier supplier, Bank bank, Taxes taxes, Timestamp date, BigDecimal subTotal, BigDecimal amountTax, BigDecimal total) {
        this.id = id;
        this.description = description;
        this.shoppingDetails = shoppingDetails;
        this.supplier = supplier;
        this.bank = bank;
        this.taxes = taxes;
        this.date = date;
        this.subTotal = subTotal;
        this.amountTax = amountTax;
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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(BigDecimal amountTax) {
        this.amountTax = amountTax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
