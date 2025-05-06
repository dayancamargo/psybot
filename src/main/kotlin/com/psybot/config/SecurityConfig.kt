package com.psybot.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer


//@Configuration
//@EnableWebFluxSecurity
class SecurityConfig(
//    val authenticationManager: AuthenticationManager,
//    val securityContextRepository: SecurityContextRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
//
////    @Bean
//    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
//        http
//            .csrf { it.disable() }
//            .exceptionHandling { handle ->
//                handle
//                    .authenticationEntryPoint { swe, e ->
//                        logger.error("Uri: {} Error: {}", swe.request.uri, e.message)
//                        Mono.fromRunnable { swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED) }
//                    }
//                    .accessDeniedHandler { swe, e ->
//                        logger.error("Uri: {} Error: {}", swe.request.uri, e.message)
//                        Mono.fromRunnable { swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN) }
//                    }
//            }
//            .authorizeExchange { authorize: AuthorizeExchangeSpec ->
//                authorize
//                    .pathMatchers( "/error", "/login").permitAll()
//                    .pathMatchers(HttpMethod.POST, "/api/user", "/login").permitAll()
//                    .pathMatchers("/psychiatrist").hasRole("ADMIN")
//                    .anyExchange().authenticated()
//            }
//            .authenticationManager(authenticationManager)
////            .securityContextRepository(securityContextRepository)
//        return http.build()
//    }
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
@Configuration
class WebConfig : WebFluxConfigurer {
// config cors
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://127.0.0.1", "http://localhost", "http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    }
}