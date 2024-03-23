
package ve.com.cge.appinvoice.ap;

import java.sql.Timestamp;

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
    private Integer supplierId;
    private Integer bankId;
    private Integer taxesId;
    private Timestamp date;
    private double subTotal;
    private double amountTax;
    private double total;

    public ShoppingDTO() {
    }

    public ShoppingDTO(Integer id, String description, Integer supplierId, Integer bankId, Integer taxesId, Timestamp date, double subTotal, double amountTax, double total) {
        this.id = id;
        this.description = description;
        this.supplierId = supplierId;
        this.bankId = bankId;
        this.taxesId = taxesId;
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

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getTaxesId() {
        return taxesId;
    }

    public void setTaxesId(Integer taxesId) {
        this.taxesId = taxesId;
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
