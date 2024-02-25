
package ve.com.cge.appinvoice.config.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JwtAuthenticationFilter : first filter
 * used to create custom filters
 * The reason is to ensure that the filter is executed only once per HTTP request,
 * even if there are multiple filters within the filter chain.
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 24, 2024
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request); // Get token from the request
        final String username;
        
        if (token==null) {
            filterChain.doFilter(request, response);
            return;
        }
        
        username = jwtService.getUsernameFromToken(token);
        
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, 
                        null, 
                        userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        filterChain.doFilter(request, response); // Call filter to continue
    }
    
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        
        return null;
    }

}
