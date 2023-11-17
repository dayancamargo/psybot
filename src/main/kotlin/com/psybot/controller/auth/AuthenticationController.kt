package com.psybot.controller.auth

import com.psybot.model.security.AuthRequest
import com.psybot.model.security.AuthResponse
import com.psybot.service.security.UserService
import com.psybot.utils.security.JwtTokenUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class AuthenticationController(val  userService: UserService, val jwtTokenUtil: JwtTokenUtil) {

    @PostMapping("/login")
    fun login(@RequestBody ar: AuthRequest): Mono<ResponseEntity<AuthResponse>> {
        return userService.findByUsername(ar.username)
            .filter { ar.password.equals(it.password) }
            .map { userDetails -> ResponseEntity.ok(AuthResponse(jwtTokenUtil.generateToken(userDetails))) }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
    }

    fun getEncode() = BCryptPasswordEncoder(12)
}