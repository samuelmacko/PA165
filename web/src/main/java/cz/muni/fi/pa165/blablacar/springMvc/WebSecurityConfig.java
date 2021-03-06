package cz.muni.fi.pa165.blablacar.springMvc;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/pa165/*").hasAnyRole();
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN");
    }
}
