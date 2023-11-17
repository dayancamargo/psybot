package com.psybot.repository.security

import com.psybot.service.security.AuthenticationManager
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Function


@Component
class SecurityContextRepository (val authenticationManager: AuthenticationManager) : ServerSecurityContextRepository {

    override fun save(swe: ServerWebExchange?, sc: SecurityContext?): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext?> {
        return Mono.justOrEmpty<String>(swe.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
            .filter { authHeader: String -> authHeader.startsWith("Bearer ") }
            .flatMap<SecurityContext?>(Function<String, Mono<out SecurityContext?>> { authHeader: String ->
                val authToken = authHeader.substring(7)
                val auth: Authentication = UsernamePasswordAuthenticationToken(authToken, authToken)
                authenticationManager.authenticate(auth)
                    .map<SecurityContextImpl?>(Function<Authentication, SecurityContextImpl?> { authentication: Authentication? ->
                        SecurityContextImpl(
                            authentication
                        )
                    })
            })
    }
}