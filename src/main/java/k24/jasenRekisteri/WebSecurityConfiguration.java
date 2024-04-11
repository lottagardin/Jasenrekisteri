
package k24.jasenRekisteri;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true) 
public class WebSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests( authorize -> authorize
		.requestMatchers("/", "/etusivu", "/lomake", "/lisatty", "/virheita", "/lisaaJasen").permitAll()
		.anyRequest().authenticated()
		)
		.formLogin( formlogin -> formlogin
		.defaultSuccessUrl("/jasenlista", true)
		.permitAll()
		)
		.logout( logout -> logout
		.permitAll()
		);
		return http.build();
		}
}