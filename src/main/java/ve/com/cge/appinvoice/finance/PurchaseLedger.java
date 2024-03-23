
package ve.com.cge.appinvoice.finance;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PurchaseLedger 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "purchaseledger")
public class PurchaseLedger {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "shopping_id", nullable = false)
    private Integer shoppingId;  
    
    @Column(name = "date", nullable = false)
    private Timestamp date;
    
    @Column(name = "subtotal", nullable = false)
    private double subTotal;
    
    @Column(name = "amount_tax", nullable = false)
    private double amountTax;
    
    @Column(name = "total", nullable = false)
    private double total;

    public PurchaseLedger() {
    }

    public PurchaseLedger(Integer shoppingId, Timestamp date, double subTotal, double amountTax, double total) {
        this.shoppingId = shoppingId;
        this.date = date;
        this.subTotal = subTotal;
        this.amountTax = amountTax;
        this.total = total;
    }

    public PurchaseLedger(Integer id, Integer shoppingId, Timestamp date, double subTotal, double amountTax, double total) {
        this.id = id;
        this.shoppingId = shoppingId;
        this.date = date;
        this.subTotal = subTotal;
        this.amountTax = amountTax;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public Integer getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Integer shoppingId) {
        this.shoppingId = shoppingId;
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
