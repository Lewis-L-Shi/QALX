package uwb.css553.qalx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class is used to customize Spring Security configuration.
 * @author Trang Quang
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configure page access for authenticated users based on user's role
     * @param http HttpSecurity
     * @throws Exception default
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/doctor")
                .hasRole("Doctor")
                .and()
            .authorizeRequests()
                .antMatchers("/researcher")
                .hasRole("Researcher")
                .and()
            .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    /**
     * Create user, password and role.
     * @param auth AuthenticationManagerBuilder
     * @throws Exception default
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("doctor").password("password").roles("Doctor")
                .and()
                .withUser("doctor2").password("password").roles("Doctor")
                .and()
                .withUser("researcher").password("password").roles("Researcher");

    }
}
