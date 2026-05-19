package com.munir.crud_pessoa.config;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.munir.crud_pessoa.dtos.response.ExceptionResponseDTO;
import com.munir.crud_pessoa.security.filters.JwtAuthenticationFilter;
import com.munir.crud_pessoa.security.services.JwtService;
import com.munir.crud_pessoa.utils.MessagesLoader;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {

        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }
    
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper mapper) {
    	
    	return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
								throws IOException, ServletException {

				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		        
		        LocalDateTime timestamp = LocalDateTime.now();
				String mensagem = MessagesLoader.loadMessage("message.token_autenticacao_invalido");
				String nomeException = authException.getClass().getSimpleName();
				
		        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(timestamp, mensagem, nomeException);

		        response.getWriter().write(mapper.writeValueAsString(responseDTO));
			}
    		
    	};
		
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter, AuthenticationEntryPoint authenticationEntryPoint) throws Exception {

        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}