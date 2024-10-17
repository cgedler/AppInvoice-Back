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

package ve.com.cge.appinvoice.finance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ve.com.cge.appinvoice.accpay.model.Shopping;
import ve.com.cge.appinvoice.accrec.model.Billing;

/**
 * Bank entity
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Entity
@Table(name = "Finance.Bank")
public class Bank implements Serializable {
    
    @Id
    private Long id;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<Billing> billing;

    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<Shopping> shopping;

    @Column(name = "number", nullable = false)
    private String number;

    public Bank() {
    }

    public Bank(Long id, String description, String number) {
        this.id = id;
        this.description = description;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
}
