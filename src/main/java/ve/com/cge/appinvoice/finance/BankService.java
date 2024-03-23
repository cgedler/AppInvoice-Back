
package ve.com.cge.appinvoice.finance;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.user.UserResponse;

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
