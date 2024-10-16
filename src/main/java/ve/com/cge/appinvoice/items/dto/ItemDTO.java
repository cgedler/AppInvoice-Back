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

package ve.com.cge.appinvoice.items.dto;

import ve.com.cge.appinvoice.items.model.Category;
import ve.com.cge.appinvoice.items.model.ItemPrice;
import ve.com.cge.appinvoice.items.model.ItemStock;

/**
 * ItemDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 21, 2024
 */
public class ItemDTO {
    
    private Long id;
    private String description;
    private Category category;
    private ItemStock stock;
    private ItemPrice price; 

    public ItemDTO() {
    }

    public ItemDTO(Long id, String description, Category category, ItemStock stock, ItemPrice price) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.price = price;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ItemStock getStock() {
        return stock;
    }

    public void setStock(ItemStock stock) {
        this.stock = stock;
    }

    public ItemPrice getPrice() {
        return price;
    }

    public void setPrice(ItemPrice price) {
        this.price = price;
    }

}
