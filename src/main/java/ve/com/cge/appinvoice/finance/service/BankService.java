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

import ve.com.cge.appinvoice.finance.dto.BankDTO;
import ve.com.cge.appinvoice.finance.model.Bank;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;
import ve.com.cge.appinvoice.finance.repository.IBankRepository;

/**
 * BankService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class BankService {

    private final IBankRepository bankRepository;

    public BankService(IBankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    
    public List<Bank> findBanks() {
        List<Bank> bankList = bankRepository.findAll();
        return bankList;
    }
       
    public BankDTO findBankById(Integer id) {
        Bank bank = bankRepository.findById(id).orElse(null);
        if (bank != null) {
            return new BankDTO(bank.getId(), bank.getDescription(), bank.getNumber());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertBank(BankDTO request) {
        Bank bank = new Bank(request.getId(),request.getDescription(), request.getNumber());
        bankRepository.save(bank);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateBank(BankDTO request, Integer id) {
        Bank bank = new Bank(id, request.getDescription(), request.getNumber());
        bankRepository.save(bank);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteBank(Integer id) {
        bankRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }   
    
}
