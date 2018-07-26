package com.bhaskar.security;

import com.bhaskar.Service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "*")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();


        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                //make session less
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        // Import accounts
       /* auth.inMemoryAuthentication()
                .withUser("test")
                .password("12345")
                .roles("ADMIN");*/

//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder)  ;
    }

}
