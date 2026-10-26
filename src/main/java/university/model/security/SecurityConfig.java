package university.model.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.formLogin( form-> {
            form.loginPage("/login");
            form.usernameParameter("email");
            form.defaultSuccessUrl("/home", true);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(autz -> {
                    autz.requestMatchers("/api/admin/**").hasRole("ADMIN");
                    autz.requestMatchers("/api/professor/**").hasRole("PROFESSOR");
                    autz.requestMatchers("/api/student/**").hasRole("STUDENT");
                    autz.requestMatchers("/login","/css/**","/js/**").permitAll();
                    autz.anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
