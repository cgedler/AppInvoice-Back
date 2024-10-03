
package ve.com.cge.appinvoice.accrec.dto;

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
public class BillingDTO {
 
    private Integer id;
    private String description;
    private List<BillingDetails> billingDetails;
    private Customer customer;
    private Seller seller;
    private Bank bank;
    private Taxes taxes;
    private Timestamp date;
    private double subTotal;
    private double amountTax;
    private double total;

    public BillingDTO() {
    }

    public BillingDTO(Integer id, String description, List<BillingDetails> billingDetails, Customer customer, Seller seller, Bank bank, Taxes taxes, Timestamp date, double subTotal, double amountTax, double total) {
        this.id = id;
        this.description = description;
        this.billingDetails = billingDetails;
        this.customer = customer;
        this.seller = seller;
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
