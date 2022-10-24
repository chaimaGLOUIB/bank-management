package com.example;
import javax.sql.DataSource ;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.authentication.AuthenticationProvider;
	import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.core.session.SessionRegistry;
	import org.springframework.security.core.session.SessionRegistryImpl;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	    @Autowired
	    private DataSource dataSource;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    private static final String SQL_ROLE
	            = "select cin as principal,role as role from utilisateur where cin=?";

	    private static final String SQL_LOGIN
	            = "Select cin as principal,password as credentials , true from utilisateur where cin=?";
	            


	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(daoAuthenticationProvider());
	    }

	    @Bean
	    public AuthenticationProvider daoAuthenticationProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setPasswordEncoder(passwordEncoder1());
	        provider.setUserDetailsService(userDetailsService());
	        return provider;
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder1() {
	        return new BCryptPasswordEncoder();
	    }
	    

	    @Bean
	    @Override
	    public  UserDetailsService userDetailsService() {
	        JdbcDaoImpl userDetails = new JdbcDaoImpl();
	        userDetails.setDataSource(dataSource);
	        userDetails.setUsersByUsernameQuery(SQL_LOGIN);
	        userDetails.setAuthoritiesByUsernameQuery(SQL_ROLE);
	        userDetails.setRolePrefix("ROLE_");;

	        return userDetails;
	    }

	    @Bean
	    public SessionRegistry sessionRegistry() {
	        return new SessionRegistryImpl();
	    }


	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable();

	        http
	                .authorizeRequests()
	                .antMatchers("/js/**").permitAll()
	                .antMatchers("/fonts/**").permitAll()
	                .antMatchers("/images/**").permitAll()


	                .antMatchers("/css/**").permitAll()
	                .antMatchers("/resources/**").permitAll()
	                .antMatchers("/forgot_password/**").permitAll()
	                .antMatchers("/reset_password/**").permitAll()

	     



	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	                .logout()
	                .permitAll();
	    }

				
}
