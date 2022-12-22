package org.chatapp.infrastructure.config;

import org.chatapp.infrastructure.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


     //application.yaml file exists
    /*
    @Bean
    DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }
    */



    @Bean
    UserDetailsManager users(DataSource dataSource){
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.createUser(user);

        return manager;
    }


    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers("/h2-console/**").permitAll();

                //.requestMatchers("/api/**").hasRole("USER")
            //.securityMatcher("/api/**")
            //.httpBasic(withDefaults());

        return http.build();

    }

   /* @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        //TODO:
        http
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .csrf().disable() //TODO: temporarily disabled
                .logout()
                    .logoutSuccessUrl("/");

        return http.build();
    }

    */


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //TODO: might not need, since this is the default
    }


}
