package com.example.apigreating.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public  void ConfigureGlobal(AuthenticationManagerBuilder auth)
    {
        try
        {
            auth.inMemoryAuthentication()
                    .withUser("user").password("{noop}vanvu2000").roles("USER")
                    .and()
                    .withUser("admin").password("{noop}vanvu2000").roles("ADMIN");
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/users").hasRole("USER")
                .antMatchers("/api/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

}
