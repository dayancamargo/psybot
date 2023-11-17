package com.psybot.utils.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenUtil {

    @Value("\${token.jwt.secret}")
    private lateinit var secret: String

    @Value("\${token.jwt.expiration}")
    private lateinit var expirationTime: String
    private lateinit var key: Key

    @PostConstruct
    fun init() {
        key = Keys.hmacShaKeyFor(secret!!.toByteArray())
    }

    fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun getUsernameFromToken(token: String?): String {
        return getAllClaimsFromToken(token).subject
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getAllClaimsFromToken(token).expiration
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration: Date = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(user: UserDetails): String {
        val claims: MutableMap<String, Any?> = HashMap()
        claims["role"] = user.authorities
        return doGenerateToken(claims, user.getUsername())
    }

    private fun doGenerateToken(claims: Map<String, Any?>, username: String): String {
        val expirationTimeLong = expirationTime!!.toLong() //in second
        val createdDate = Date()
        val expirationDate = Date(createdDate.getTime() + expirationTimeLong * 1000)
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return !isTokenExpired(token)
    }
}