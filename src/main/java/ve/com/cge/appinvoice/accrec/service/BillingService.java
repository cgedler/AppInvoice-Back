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

package ve.com.cge.appinvoice.accrec.service;

import java.util.ArrayList;
import ve.com.cge.appinvoice.accrec.repository.IBillingRepository;
import ve.com.cge.appinvoice.accrec.model.Billing;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.accrec.dto.BillingDTO;
import ve.com.cge.appinvoice.accrec.model.BillingDetails;
import ve.com.cge.appinvoice.accrec.repository.IBillingDetailsRepository;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * BillingService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class BillingService {
    
    private static Logger logger = LoggerFactory.getLogger(BillingService.class);
    
    private final IBillingRepository billingRepository;
    private final IBillingDetailsRepository billingDetailsRepository;

    public BillingService(IBillingRepository billingRepository, IBillingDetailsRepository billingDetailsRepository) {
        this.billingRepository = billingRepository;
        this.billingDetailsRepository = billingDetailsRepository;
    }
    
    public List<Billing> findBillings() {
        List<Billing> billingList = billingRepository.findAll();
        return billingList;
    }
       
    public BillingDTO findBillingById(Long id) {
        Billing billing = billingRepository.findById(id).orElse(null);
        if (billing != null) {
            return new BillingDTO(
                    billing.getId(),
                    billing.getDescription(),
                    billing.getBillingDetails(),
                    billing.getCustomer(),
                    billing.getSeller(),
                    billing.getBank(),
                    billing.getTaxes(),
                    billing.getDate(),
                    billing.getSubTotal(),
                    billing.getAmountTax(),
                    billing.getTotal());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertBilling(BillingDTO request) {
        List<BillingDetails> billingDetails = new ArrayList<BillingDetails>();         
        Billing billing = new Billing(
                request.getDescription(),
                billingDetails,
                request.getCustomer(),
                request.getSeller(),
                request.getBank(),
                request.getTaxes(),
                request.getDate(),
                request.getSubTotal(),
                request.getAmountTax(),
                request.getTotal());
        billingRepository.save(billing);
        for(BillingDetails s : request.getBillingDetails()) {
            s.setBilling(billing);
            billingDetailsRepository.save(s);
        }
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateBilling(BillingDTO request, Long id) {
        Billing billing = new Billing(
                id,
                request.getDescription(),
                request.getBillingDetails(),
                request.getCustomer(),
                request.getSeller(),
                request.getBank(),
                request.getTaxes(),
                request.getDate(),
                request.getSubTotal(),
                request.getAmountTax(),
                request.getTotal());
        billingRepository.save(billing);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteBilling(Long id) {
        billingRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }

}
