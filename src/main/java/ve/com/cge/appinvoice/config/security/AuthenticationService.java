
package ve.com.cge.appinvoice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.config.security.dto.AuthenticationResponse;
import ve.com.cge.appinvoice.config.security.dto.LoginRequest;
import ve.com.cge.appinvoice.config.security.dto.RegisterRequest;
import ve.com.cge.appinvoice.config.security.jwt.JwtService;
import ve.com.cge.appinvoice.config.user.IUserRepository;
import ve.com.cge.appinvoice.config.user.Role;
import ve.com.cge.appinvoice.config.user.User;

/**
 * AuthenticationService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 24, 2024
 */
@Service
public class AuthenticationService {
    
    @Autowired
    private final IUserRepository userRepository;
    
    @Autowired
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private final JwtService jwtService;
    
    @Autowired
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationService(IUserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
 
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("The user no exist"));
        String token = jwtService.getToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);
        return authenticationResponse;
    }
    
    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = new User(
                request.getUsername(),
                request.getName(),
                passwordEncoder.encode( request.getPassword()), 
                Role.USER);
        userRepository.save(newUser);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtService.getToken(newUser));
        return authenticationResponse;
    }
}
