
package ve.com.cge.appinvoice.accpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * Shopping 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "shopping")
public class Shopping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "shopping")
    @JsonIgnore
    private List<ShoppingDetails> shoppingDetails;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    
    @ManyToOne
    @JoinColumn(name = "taxes_id")
    private Taxes taxes;
    
    @Column(name = "date", nullable = false)
    private Timestamp date;
    
    @Column(name = "subtotal", nullable = false)
    private double subTotal;
    
    @Column(name = "amount_tax", nullable = false)
    private double amountTax;
    
    @Column(name = "total", nullable = false)
    private double total;

    public Shopping() {
    }

    public Shopping(String description, List<ShoppingDetails> shoppingDetails, Supplier supplier, Bank bank, Taxes taxes, Timestamp date, double subTotal, double amountTax, double total) {
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

    public Shopping(Integer id, String description, List<ShoppingDetails> shoppingDetails, Supplier supplier, Bank bank, Taxes taxes, Timestamp date, double subTotal, double amountTax, double total) {
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