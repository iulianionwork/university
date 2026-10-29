package university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.formLogin( form-> {
            form.usernameParameter("email");
            form.defaultSuccessUrl("/home", true);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(autz -> {
                    autz.requestMatchers("/api/admin/**", "/admin/**").hasRole("ADMIN");
                    autz.requestMatchers("/api/professor/**", "/professor/**").hasRole("PROFESSOR");
                    autz.requestMatchers("/api/student/**", "/student/**").hasRole("STUDENT");
                    autz.requestMatchers("/login","/css/**","/js/**").permitAll();
                    autz.anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
