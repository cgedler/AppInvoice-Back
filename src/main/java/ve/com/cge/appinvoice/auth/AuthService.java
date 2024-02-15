
package ve.com.cge.appinvoice.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ve.com.cge.appinvoice.jwt.JwtService;
import ve.com.cge.appinvoice.user.Role;
import ve.com.cge.appinvoice.user.User;
import ve.com.cge.appinvoice.user.UserRepository;


@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("The user no exist"));
        String token = jwtService.getToken(user);
        AuthResponse authResponse = new AuthResponse(token);
        return authResponse;
    }
    
    public AuthResponse register(RegisterRequest request) {
        User user = new User(
                request.getUsername(),
                passwordEncoder.encode( request.getPassword()), 
                request.getFirstname(), 
                request.getLastname(), 
                request.getCountry(), 
                Role.USER);
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse(jwtService.getToken(user));
        return authResponse;
    }
}
