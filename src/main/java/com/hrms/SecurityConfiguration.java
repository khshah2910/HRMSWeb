package com.hrms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        // Users in memory.
//        auth.inMemoryAuthentication().withUser("user1").password("12345").roles("EMPLOYEES");
//        auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("HR");
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

       /* http.authorizeRequests().and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/home").failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
*/

       /* http
                .authorizeRequests()
                .antMatchers("/login*").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/homepage.html")
                .failureUrl("/login.html?error=true")
                .and()
                .logout().logoutSuccessUrl("/login.html");*/

        http.csrf().disable().authorizeRequests().antMatchers("/api/**").permitAll();

        /*.antMatchers("/home/register/").permitAll()
                .antMatchers("/home/register/team").permitAll()
                .antMatchers("/home/teams").permitAll()
        .antMatchers("/home/*").permitAll();*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}