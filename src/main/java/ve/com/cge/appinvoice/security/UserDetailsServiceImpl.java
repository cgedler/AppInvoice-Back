
package ve.com.cge.appinvoice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.model.User;
import ve.com.cge.appinvoice.repository.UserRepository;


@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
        
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The user with email " + email + " no exist"));
        return new UserDetailsImpl(user);
    }
        
        

    
}
