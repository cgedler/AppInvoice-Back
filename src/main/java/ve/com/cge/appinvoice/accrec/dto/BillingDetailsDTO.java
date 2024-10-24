/*
 * Copyright (C) 2024 cge
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ve.com.cge.appinvoice.accrec.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

/**
 * BillingDetailsDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingDetailsDTO {
    
    private Long id;
    private String description;
    private Long billingId;
    private Long itemId;
    private Integer quantity;
    private BigDecimal amount;

    public BillingDetailsDTO() {
    }

    public BillingDetailsDTO(Long id, String description, Long billingId, Long itemId, Integer quantity, BigDecimal amount) {
        this.id = id;
        this.description = description;
        this.billingId = billingId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.amount = amount;
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

    public Long getShoppingId() {
        return billingId;
    }

    public void setShoppingId(Long billingId) {
        this.billingId = billingId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
        return "BillingDetailsDTO{" + "id=" + id + ", description=" + description + ", billingId=" + billingId + ", itemId=" + itemId + ", quantity=" + quantity + ", amount=" + amount + '}';
    }
    
}
