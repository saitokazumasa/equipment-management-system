package jp.ac.morijyobi.equipmentmanagementsystem.configuration;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/password_reset").permitAll()
                        .requestMatchers("/checkout/**").authenticated()
                        .requestMatchers("/equipment/list/**").authenticated()
                        .requestMatchers("/equipment/register/**").hasAnyRole(
                                AccountCategory.EQUIPMENT_MANAGER.toString(),
                                AccountCategory.SYSTEM_MANAGER.toString())
                        .requestMatchers("/account/**").hasRole(AccountCategory.SYSTEM_MANAGER.toString())
                        .requestMatchers("/student/**").hasRole(AccountCategory.SYSTEM_MANAGER.toString())
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
