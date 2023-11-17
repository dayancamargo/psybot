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
    fun userDetailsService(): MapReactiveUserDetailsService {
        // @formatter:off
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        val admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN", "USER")
            .build()
        // @formatter:on
        return MapReactiveUserDetailsService(user, admin)
    }

    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .csrf().disable()
            .exceptionHandling { handle ->
                handle
                    .authenticationEntryPoint { swe, e ->
                        logger.info("Exchange: {} Error: {}", swe, e)
                        Mono.fromRunnable{ swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED) }
                    }
                    .accessDeniedHandler { swe, e ->
                        logger.info("Exchange: {} Error: {}", swe, e)
                        Mono.fromRunnable{ swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN) }
                    }
            }
            .authorizeExchange { authorize: AuthorizeExchangeSpec ->
                authorize
                    .pathMatchers("/api/auth", "api/auth/refresh", "/error", "/login").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/user", "/login").permitAll()
                    .pathMatchers("/api/user**", "/psychiatrist").hasRole("ADMIN")
                    .anyExchange().permitAll()
            }
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
//            .httpBasic(withDefaults())
        return http.build()
    }

//
//    @Bean
//    open fun webHttpSecurity(http: ServerHttpSecurity): SecurityWebFilterChain {
//        return http {
//            authorizeExchange {
//                authorize(anyExchange, authenticated)
//            }
//            httpBasic { }
//        }
//    }
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        return http
//            .csrf { it.disable() }
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
//            .build()
//    }

//    private fun userDetailsService(): UserDetailsService {
//        return InMemoryUserDetailsManager().apply {
//            createUser(
//                User.withUsername("admin")
//                    .password("password")
//                    .roles("USER", "ADMIN")
//                    .build()
//            )
//        }
//    }
}