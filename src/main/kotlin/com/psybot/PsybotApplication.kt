package com.psybot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
//@EnableAspectJAutoProxy
class PsybotApplication

fun main(args: Array<String>) {
	runApplication<PsybotApplication>(*args)
}