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

package ve.com.cge.appinvoice.finance.service;

import ve.com.cge.appinvoice.finance.dto.TaxesDTO;
import ve.com.cge.appinvoice.finance.model.Taxes;
import ve.com.cge.appinvoice.finance.repository.ITaxesRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * TaxesService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class TaxesService {

    private final ITaxesRepository taxesRepository;

    public TaxesService(ITaxesRepository taxesRepository) {
        this.taxesRepository = taxesRepository;
    }
    
    public List<Taxes> findTaxess() {
        List<Taxes> taxesList = taxesRepository.findAll();
        return taxesList;
    }
       
    public TaxesDTO findTaxesById(Integer id) {
        Taxes taxes = taxesRepository.findById(id).orElse(null);
        if (taxes != null) {
            return new TaxesDTO(taxes.getId(), taxes.getDescription(), taxes.getTax());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertTaxes(TaxesDTO request) {
        Taxes taxes = new Taxes(request.getId(),request.getDescription(), request.getTax());
        taxesRepository.save(taxes);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateTaxes(TaxesDTO request, Integer id) {
        Taxes taxes = new Taxes(id, request.getDescription(), request.getTax());
        taxesRepository.save(taxes);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteTaxes(Integer id) {
        taxesRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   

}
