package com.psybot.service.security

import com.psybot.utils.security.JwtTokenUtil
import io.jsonwebtoken.Claims
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationManager(val jwtUtil: JwtTokenUtil) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {

        val authToken: String = authentication.getCredentials().toString()
        val username: String = jwtUtil.getUsernameFromToken(authToken)

        return Mono.just(jwtUtil.validateToken(authToken))
            .filter { valid -> valid }
            .switchIfEmpty(Mono.empty())
            .map { valid ->
                val claims: Claims = jwtUtil.getAllClaimsFromToken(authToken)
                UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    claims.get("role", Collection::class.java).map { SimpleGrantedAuthority(it.toString()) }
                )
            }
    }
}