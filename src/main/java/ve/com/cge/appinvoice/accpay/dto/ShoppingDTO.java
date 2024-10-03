
package ve.com.cge.appinvoice.accpay.dto;

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
 
    private Integer id;
    private String description;
    private List<ShoppingDetails> shoppingDetails;
    private Supplier supplier;
    private Bank bank;
    private Taxes taxes;
    private Timestamp date;
    private double subTotal;
    private double amountTax;
    private double total;

    public ShoppingDTO() {
    }

    public ShoppingDTO(Integer id, String description, List<ShoppingDetails> shoppingDetails, Supplier supplier, Bank bank, Taxes taxes, Timestamp date, double subTotal, double amountTax, double total) {
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(double amountTax) {
        this.amountTax = amountTax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
