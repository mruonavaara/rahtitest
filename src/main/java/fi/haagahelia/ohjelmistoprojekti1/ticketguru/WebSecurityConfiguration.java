package fi.haagahelia.ohjelmistoprojekti1.ticketguru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller.DetailsService;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.User;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
    DetailsService detailsService;

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//http.headers().frameOptions().sameOrigin(); // h2-console needs this
        http.authorizeRequests()
        		//.antMatchers("/").permitAll()
        		.anyRequest().authenticated()
                .and().cors()
                .and().httpBasic()
                .and().csrf().disable();
                /*.headers().disable()*/;
    }
    
}
