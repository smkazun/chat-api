package org.chatapp.infrastructure.config;

import org.chatapp.infrastructure.security.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private JpaUserDetailsService userDetailsService;



    @Bean
    public UserDetailsService userDetailsService(){


        /*manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build());*/

        //TODO:

        return userDetailsService;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**")
            .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("USER"))
            .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        //TODO:
        http
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .csrf().disable()
                .logout()
                    .logoutSuccessUrl("/");

        return http.build();
    }

    /*
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

     */

}
