
package ve.com.cge.appinvoice.configuration.security.jwt;


import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * AuthenticationFilter 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 15, 2024
 */
public class AuthenticationFilter {
//extends UsernamePasswordAuthenticationFilter {

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        
//        LoginRequest authCredentials = new LoginRequest();
//        try 
//        {
//            authCredentials = new ObjectMapper().readValue(request.getReader(), LoginRequest.class);
//        } catch (IOException ex) {
//            // 
//        }
//        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
//                authCredentials.getUsername(),
//                authCredentials.getPassword(),
//                Collections.emptyList()// ---- Roles
//        );
//        return getAuthenticationManager().authenticate(usernamePAT);
//    }
//    
//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request, 
//            HttpServletResponse response, 
//            FilterChain chain, 
//            Authentication authResult) throws IOException, ServletException {
//        
//        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
//        String token = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername());
//        response.addHeader("Authorization", "Bearer " + token);
//        response.getWriter().flush();
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
    
}
