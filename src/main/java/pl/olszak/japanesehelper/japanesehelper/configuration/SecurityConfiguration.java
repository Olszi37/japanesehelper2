package pl.olszak.japanesehelper.japanesehelper.configuration;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.olszak.japanesehelper.japanesehelper.security.jwt.JWTConfigurer;
import pl.olszak.japanesehelper.japanesehelper.security.jwt.TokenProvider;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =  true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private AuthenticationManagerBuilder authenticationManagerBuilder;

    private UserDetailsService userDetailsService;

    private TokenProvider tokenProvider;

    @Autowired
    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService,
                                 TokenProvider tokenProvider){
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @PostConstruct
    public void init() {
        try{
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e){
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/jhelper/authorize").permitAll()
            .antMatchers(HttpMethod.POST, "/jhelper/user").permitAll()
        .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter(){
        return new JWTConfigurer(tokenProvider);
    }
}
