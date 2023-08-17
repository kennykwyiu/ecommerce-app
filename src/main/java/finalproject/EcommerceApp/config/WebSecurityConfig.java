package finalproject.EcommerceApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests(authorize ->
//                authorize.anyRequest().authenticated()
                        authorize.requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasAuthority("ADMIN")
                                // replace @PreAuthorize("hasAuthority('ADMIN')")
                                .anyRequest().authenticated()
        );

        http.oauth2ResourceServer().jwt();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer configure() throws Exception {
        return web -> web.ignoring().requestMatchers(toH2Console());
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt ->
                Optional.ofNullable(jwt.getClaimAsStringList("custom_claims"))
                        .stream()
                        .flatMap(Collection::stream)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        return converter;
    }


}
