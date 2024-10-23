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

package ve.com.cge.appinvoice.finance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * TaxesDTO 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxesDTO {

    private Long id;
    private String description;
    private double tax;

    public TaxesDTO() {
    }

    public TaxesDTO(Long id, String description, double tax) {
        this.id = id;
        this.description = description;
        this.tax = tax;
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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "TaxesDTO{" + "id=" + id + ", description=" + description + ", tax=" + tax + '}';
    }
    
}
