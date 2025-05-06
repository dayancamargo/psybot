package com.psybot.controller.auth

import com.psybot.service.security.UserService
import com.psybot.utils.security.JwtTokenUtil


//@RestController
class AuthenticationController(val  userService: UserService, val jwtTokenUtil: JwtTokenUtil) {

//    @PostMapping("/login")
//    fun login(@RequestBody ar: AuthRequest): Mono<ResponseEntity<AuthResponse>> {
//        return userService.findByUsername(ar.username)
//            .filter { ar.password.equals(it.password) }
//            .map { userDetails -> ResponseEntity.ok(AuthResponse(jwtTokenUtil.generateToken(userDetails))) }
//            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
//    }
//
//    fun getEncode() = BCryptPasswordEncoder(12)
}