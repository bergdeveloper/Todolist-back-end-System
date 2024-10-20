package io.github.bergdeveloper.todolist_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private FiltroDeSeguranca filtro;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/usuarios/listar-usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "usuarios/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/logar").permitAll()


                        .requestMatchers(HttpMethod.POST, "/usuarios/tarefas").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/usuarios/tarefas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/tarefas/lista").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/tarefas/lista").hasRole("USER")
                        .anyRequest().authenticated())
                .addFilterBefore(filtro, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
