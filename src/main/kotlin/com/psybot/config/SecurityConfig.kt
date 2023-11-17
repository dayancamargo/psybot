package com.psybot.config

import com.psybot.repository.security.SecurityContextRepository
import com.psybot.service.security.AuthenticationManager
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono


@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    val authenticationManager: AuthenticationManager,
    val securityContextRepository: SecurityContextRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .csrf { it.disable() }
            .exceptionHandling { handle ->
                handle
                    .authenticationEntryPoint { swe, e ->
                        logger.error("Uri: {} Error: {}", swe.request.uri, e.message)
                        Mono.fromRunnable { swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED) }
                    }
                    .accessDeniedHandler { swe, e ->
                        logger.error("Uri: {} Error: {}", swe.request.uri, e.message)
                        Mono.fromRunnable { swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN) }
                    }
            }
            .authorizeExchange { authorize: AuthorizeExchangeSpec ->
                authorize
                    .pathMatchers( "/error", "/login").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/user", "/login").permitAll()
                    .pathMatchers("/psychiatrist").hasRole("ADMIN")
                    .anyExchange().authenticated()
            }
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
        return http.build()
    }
}
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//            .authorizeHttpRequests {
//                it
//                    .requestMatchers("/api/auth", "api/auth/refresh", "/error")
//                    .permitAll()
//                    .requestMatchers(HttpMethod.POST, "/api/user")
//                    .permitAll()
//                    .requestMatchers("/api/user**")
//                    .hasRole("ADMIN")
//                    .anyRequest()
//                    .authenticated()
//            }
//            .sessionManagement {
//                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            }
////            .userDetailsService(userDetailsService())
////            .userDetailsService(ReactiveUserDetailsService)
