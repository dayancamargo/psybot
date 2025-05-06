package com.psybot.controller.psychiatrist

import com.psybot.model.psychiatrist.PsychiatristRequest
import com.psybot.service.psychiatrist.PsychiatristService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/psychiatrist")
class PsychiatristController(
    private val psychiatristService: PsychiatristService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping()
    fun findAll() =
        psychiatristService.find()

    @PostMapping()
    fun create( @Valid @RequestBody psychiatristRequest: PsychiatristRequest) =
        psychiatristService.create(psychiatristRequest).log()


    @PutMapping("/{id}")
    fun update(@PathVariable("id") id:Long,
               @Valid @RequestBody psychiatristRequest: PsychiatristRequest
    ) =
        psychiatristService.update(psychiatristRequest, id)

}
