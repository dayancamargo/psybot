package com.psybot.controller.evolution

import com.psybot.service.evolution.EvolutionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/evolution")
class EvolutionController(
    private val evolutionService: EvolutionService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun findAll() = evolutionService.find()

//    @PostMapping
//    fun create(@Valid @RequestBody evolutionRequest: EvolutionRequest) =
//        evolutionService.create(evolutionRequest)
}
