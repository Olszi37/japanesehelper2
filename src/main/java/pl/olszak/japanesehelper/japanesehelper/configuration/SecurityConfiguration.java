package pl.olszak.japanesehelper.japanesehelper.configuration;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
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

    private CorsFilter corsFilter;

    @Autowired
    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService,
                                 TokenProvider tokenProvider, CorsFilter corsFilter){
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
    }

    @PostConstruct
    public void init() {
        try{
            authenticationManagerBuilder.userDetailsService(userDetailsService);
        } catch (Exception e){
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }


//    @Override
//    public void configure(WebSecurity web){
//        web.ignoring()
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
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
