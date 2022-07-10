package com.hcmus.wiberback.security;

import com.hcmus.wiberback.entity.enums.Role;
import com.hcmus.wiberback.security.filter.CustomAuthenticationFilter;
import com.hcmus.wiberback.security.filter.CustomAuthorizationFilter;
import com.hcmus.wiberback.util.JwtUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtUtil jwtUtil;

  public WebSecurityConfiguration(UserDetailsService userDetailsService,
      BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
    this.userDetailsService = userDetailsService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager(),
        jwtUtil);
    authenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");

    http.csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests().antMatchers("/api/v1/auth/login, /api/v1/auth/refresh-token")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/**/auth/register")
        .permitAll();

    http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/customer").hasAnyAuthority(Role.CUSTOMER.toString());
    http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/v1/customer").hasAnyAuthority(Role.CUSTOMER.toString());
    http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/api/v1/customer").hasAnyAuthority(Role.CUSTOMER.toString());

    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(authenticationFilter)
        .addFilterBefore(new CustomAuthorizationFilter(jwtUtil),
            UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
