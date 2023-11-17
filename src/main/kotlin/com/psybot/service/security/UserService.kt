package com.psybot.service.security

import jakarta.annotation.PostConstruct
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*


@Service
class UserService {
    private var data: MutableMap<String, User>? = null

    @PostConstruct
    fun init() {
        data = HashMap<String, User>()

        //username:passwowrd -> user:user
        data!!["user"] = User(

            "user", "batata", Arrays.asList(
                SimpleGrantedAuthority("USER")
            )
        )

        //username:passwowrd -> admin:admin
        data!!["admin"] =
            User(
                "admin", "batata", Arrays.asList(
                    SimpleGrantedAuthority("USER"),
                    SimpleGrantedAuthority("ADMIN")
                )
            )
    }

    fun findByUsername(username: String): Mono<User> {
        return Mono.justOrEmpty<User>(data!![username])
    }
}
