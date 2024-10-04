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

import ve.com.cge.appinvoice.accrec.model.Customer;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.accrec.dto.CustomerDTO;
import ve.com.cge.appinvoice.accrec.repository.ICustomerRepository;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * CustomerService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class CustomerService {
    
    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public List<Customer> findCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }
       
    public CustomerDTO findCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return new CustomerDTO(customer.getId(), customer.getDescription());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertCustomer(CustomerDTO request) {
        Customer customer = new Customer(request.getId(),request.getDescription());
        customerRepository.save(customer);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateCustomer(CustomerDTO request, Integer id) {
        Customer customer = new Customer(id, request.getDescription());
        customerRepository.save(customer);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }

}
