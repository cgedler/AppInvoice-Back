
package ve.com.cge.appinvoice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ve.com.cge.appinvoice.config.security.jwt.JwtAuthenticationFilter;

/**
 * SecurityConfiguration : configure the security filters
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 15, 2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         
        return http
                .csrf().disable()
                .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .and()
                .authorizeHttpRequests().antMatchers(HttpMethod.GET).permitAll()
                .and()
                .authorizeHttpRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
                .authorizeHttpRequests().antMatchers("/hello/user/**").hasAuthority("USER")
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                //.and().formLogin()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
   
}