package com.airbnb.bnb1.config;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

//@Configuration
//public class SecurityConfig {
//    private JWTFilter jwtFilter;
//
//    public SecurityConfig(JWTFilter jwtFilter) {
//        this.jwtFilter = jwtFilter;
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(
//            HttpSecurity    http
//    ) throws Exception {
//
//        //h(cd)2 - This below code helps to access the forbedden urls. Without below code urls will not be
//        // able to interact with third party place(software). Urls will work only with in the application. if
//        // urls request is coming from some other site than that will not work. Like postman here is some other
//        // site. So the urls will not work from the postman. So to allow the postman to allow the use the urls
//        // we have to use below code so that it will no longer be forbidden to access the urls.
//        //h(cd)2
//        http.csrf().disable().cors().disable();
//
//        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        // haap-(all the url are authorised to access that means access of all the urls are allowed).It is
//        // allowed to access because we are in the development phase so we have to login and take the access again
//        // and again, that is why it is allowed to access all the urls by using the below code. though below code
//        // spring boot will understand that we should keep all the urls open.
//        // haap
//        // http.authorizeHttpRequests().anyRequest().permitAll();
//
//        http.authorizeHttpRequests()
//                .requestMatchers("api/v1/auth/**")
//                .permitAll()
//                .anyRequest().authenticated();
//        return http.build();
//    }
//}
// 401-  unauthorized
// 403 - forbidden



@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity    http
    ) throws Exception {
        // h(cd)2 - This below code helps to access the forbidden urls. Without below code urls will not be
        // able to interact with third party place(software). Urls will work only with in the application. if
        // urls request is coming from some other site than that will not work. Like postman here is some other
        // site. So the urls will not work from the postman. So to allow the postman to use the urls
        // we have to use below code so that it will no longer be forbidden to access the urls.
        //h(cd)2
        http.csrf().disable().cors().disable();
        // haap-(all the url are authorised to access that means access of all the urls are allowed).It is
        // allowed to access because we are in the development phase so we have to login and take the access again
        // and again, that is why it is allowed to access all the urls by using the below code. Through below code
        // spring boot will understand that we should keep all the urls open.
        // haap
        //    http.authorizeHttpRequests().anyRequest().permitAll();

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        http.authorizeHttpRequests().anyRequest().permitAll();
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/createuser","/api/v1/auth/creatpropertyowner","/api/v1/auth/login","/api/v1/country/create")
//                .permitAll()
//                .requestMatchers("/api/v1/property/addProperty").hasAnyRole("OWNER","ADMIN","MANAGER")
//                .requestMatchers("/api/v1/auth/creatpropertymanager").hasRole("ADMIN")
//                .anyRequest().authenticated();


//        http.authorizeHttpRequests()
//                .requestMatchers("api/v1/auth/**")
//                .permitAll()
//                .requestMatchers("/api/v1/dummy/getMessage")
//                .hasRole("ADMIN")
//                .anyRequest().authenticated();

        return http.build();
    }
}
// 401-  unauthorized
// 403 - forbidden
