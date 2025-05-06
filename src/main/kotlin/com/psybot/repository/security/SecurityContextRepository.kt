package com.psybot.repository.security

import com.psybot.service.security.AuthenticationManager


//@Component
class SecurityContextRepository (val authenticationManager: AuthenticationManager) { //}: ServerSecurityContextRepository {

//     fun save(swe: ServerWebExchange?, sc: SecurityContext?): Mono<Void> {
//        throw UnsupportedOperationException("Not supported yet.")
//    }
//
//     fun load(swe: ServerWebExchange): Mono<SecurityContext?> {
//        return Mono.justOrEmpty<String>(swe.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
//            .filter { authHeader: String -> authHeader.startsWith("Bearer ") }
//            .flatMap<SecurityContext?>(Function<String, Mono<out SecurityContext?>> { authHeader: String ->
//                val authToken = authHeader.substring(7)
//                val auth: Authentication = UsernamePasswordAuthenticationToken(authToken, authToken)
//                authenticationManager.authenticate(auth)
//                    .map<SecurityContextImpl?>(Function<Authentication, SecurityContextImpl?> { authentication: Authentication? ->
//                        SecurityContextImpl(
//                            authentication
//                        )
//                    })
//            })
//    }
}