package org.chatapp.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {


    @Bean
    DataSource dataSource(){
        //In-Memory Db for development purposes only
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .setName("testdb")
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    JdbcUserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
        return detailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().authenticated())
            .securityMatcher("/h2-console/**")
            .securityMatcher("/login/**")
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(headers -> headers.frameOptions().sameOrigin())
            .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
