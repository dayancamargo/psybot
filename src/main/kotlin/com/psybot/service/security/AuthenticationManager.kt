package com.psybot.service.security

import com.psybot.utils.security.JwtTokenUtil

//@Component
class AuthenticationManager(val jwtUtil: JwtTokenUtil) {//: ReactiveAuthenticationManager {

//    override fun authenticate(authentication: Authentication): Mono<Authentication> {
//
//        val authToken: String = authentication.getCredentials().toString()
//        val username: String = jwtUtil.getUsernameFromToken(authToken)
//
//        return Mono.just(jwtUtil.validateToken(authToken))
//            .filter { valid -> valid }
//            .switchIfEmpty(Mono.empty())
//            .map { valid ->
//                val claims: Claims = jwtUtil.getAllClaimsFromToken(authToken)
//                UsernamePasswordAuthenticationToken(
//                    username,
//                    null,
//                    claims.get("role", Collection::class.java).map { SimpleGrantedAuthority(it.toString()) }
//                )
//            }
//    }
}