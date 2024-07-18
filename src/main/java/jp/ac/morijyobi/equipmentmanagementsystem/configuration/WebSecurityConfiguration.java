package jp.ac.morijyobi.equipmentmanagementsystem.configuration;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(a -> a
                        .requestMatchers(PathRequest
                                .toStaticResources()
                                .atCommonLocations()
                        ).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/password_reset").permitAll()
                        .requestMatchers("/checkout/**").authenticated()
                        .requestMatchers("/return/**").authenticated()
                        .anyRequest().denyAll()
                ).formLogin(a -> a
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/checkout/application")
                        .failureUrl("/login?error")
                        // note: メールアドレスを "username" として扱う
                        .usernameParameter("mail")
                        .permitAll()
                ).logout(a -> a
                        .logoutSuccessUrl("/login")
                        .permitAll()
                ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
