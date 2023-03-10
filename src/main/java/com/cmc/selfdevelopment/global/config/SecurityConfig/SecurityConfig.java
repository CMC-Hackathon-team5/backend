package com.cmc.selfdevelopment.global.config.SecurityConfig;


import com.cmc.selfdevelopment.domain.user.repository.UserRepository;
import com.cmc.selfdevelopment.global.config.SecurityConfig.jwt.JwtAuthenticationCheckFilter;
import com.cmc.selfdevelopment.global.config.SecurityConfig.jwt.JwtAuthenticationEntryPoint;
import com.cmc.selfdevelopment.global.config.SecurityConfig.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] POST_PERMITTED_URLS = {
            "/api/user/signup", "/api/user/login"
    };

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http, JwtAuthenticationCheckFilter jwtAuthenticationCheckFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint    ) throws Exception {
        http
                .cors()//CORS ?????? ??????(Front , Back ????????? ???????????? ???????????? ??????)
                .and()
                .csrf().disable() // //????????? ??????????????? ???????????? ?????? ????????? ?????? ???????????? csrf ???????????? ????????? ????????? ??????.
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //???????????????????????? ????????????????????? ???????????? ??????????????? ?????? ->JWT ????????????????????? ?????? ???????????? ??????
                .and()
                .formLogin().disable()  // ????????????  View??? ???????????? ???????????? disable
                .httpBasic().disable() // JWT ?????? ????????? ??????????????? httpBasic??? ????????? ???????????? ??????X

                .addFilterAfter(jwtAuthenticationCheckFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .authorizeRequests(antz -> antz
                        .antMatchers(HttpMethod.POST, POST_PERMITTED_URLS).permitAll()
                        .antMatchers("/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    // CORS ?????? ??????
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public AuthenticationManager authenticationManager(ApplicationContext context) throws Exception {
        AuthenticationManagerFactoryBean authenticationManagerFactoryBean = new AuthenticationManagerFactoryBean();
        authenticationManagerFactoryBean.setBeanFactory(context);
        return authenticationManagerFactoryBean.getObject();
    }
    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        return new JwtAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public JwtAuthenticationCheckFilter jwtAuthenticationCheckFilter(JwtTokenProvider jwtTokenProvider) {
        return new JwtAuthenticationCheckFilter(jwtTokenProvider);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/swagger-ui/**");
    }
}
