package tn.workbot.coco_marketplace.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tn.workbot.coco_marketplace.services.auth.ApplicationUserDetailsService;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    private static  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/v3/api-docs/**",
                        "/webjars/**")
                .permitAll()
                // .antMatchers("/**/deleteUser").access("hasRole('ADMINISTRTOR')")
                //  .antMatchers("/**/selectUserById").access("hasRole('ADMINISTRTOR')")
                //  .antMatchers("/**/selectUserAll").access("hasRole('ADMINISTRTOR')")
                // .antMatchers("/**/**").access("hasRole('ADMINISTRTOR')")
                // .antMatchers("/store").access("hasRole('SELLER')")
                // .antMatchers("/PromotionCode").access("hasRole('SELLER')")
                // .antMatchers("/productCategory").access("hasRole('SELLER')")
                //.antMatchers("/Pickup/**").access("hasRole('SELLER')")
                // .antMatchers("/product").access("hasRole('SELLER')")
                // .antMatchers("/Privilege/**").access("hasRole('MODERATOR')")
                // .antMatchers(" /productQuantity/**").access("hasRole('BUYER')")
                // .antMatchers("/claims/**").access("hasRole('BUYER')")
                // .antMatchers("/Review/**").access("hasRole('BUYER')")
                // .antMatchers("/order/**").access("hasRole('BUYER')")
                // .antMatchers("/orderStats/**").access("hasRole('BUYER')")
                // .antMatchers("/Loyalty/**").access("hasRole('BUYER')")

                // .antMatchers("/AgencyBranch/**").access("hasRole('DELIVERYAGENCY')")
                // .antMatchers("/AgencyDeliveryMan/**").access("hasRole('DELIVERYAGENCY')")
                // .antMatchers("/SupplierRequest").access("hasRole('SELLER')")
                // .antMatchers("/**/changePass").permitAll()
                // .antMatchers("/**/resetPassword").permitAll()
                // .antMatchers("/**/checkEmail").permitAll()
                //    .antMatchers("/**/addUser").permitAll()
                //   .antMatchers("/**/confirm-account").permitAll()
                //  .antMatchers("/**/affectRole").permitAll()
                // .antMatchers("/**/verify").permitAll()
                // .antMatchers("/**/verifyy").permitAll()*/

                .anyRequest().authenticated()
                .and().sessionManagement(
                        session -> session

                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                .invalidSessionUrl("/logout?expired")
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(true));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.logout(logout ->logout.deleteCookies("JESSIONID").invalidateHttpSession(true));

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//hachage

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}