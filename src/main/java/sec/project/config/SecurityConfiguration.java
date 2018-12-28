package sec.project.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/free").permitAll() //Allows ordinary users to access certain pages
                .antMatchers("/access").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/news").permitAll()
                .antMatchers("/news/*").permitAll()
                .antMatchers("/news/*/image").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/tyylit.css").permitAll()
                .antMatchers("tyylit.css").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
                        hsr1.sendRedirect("/news");
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
                        hsr1.sendRedirect("/news");
                    }
                })
                .permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/news");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // initializes two users for the service
                .withUser("jour").password("nalist").roles("USER").authorities("USER");
        auth.inMemoryAuthentication()
                .withUser("ad").password("min").roles("ADMIN").authorities("USER", "ADMIN");
    }

}
