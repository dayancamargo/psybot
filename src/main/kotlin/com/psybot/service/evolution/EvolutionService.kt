package com.psybot.service.evolution

import com.psybot.model.evolution.EvolutionRequest
import com.psybot.model.evolution.EvolutionResponse
import com.psybot.repository.evolution.EvolutionRepository
import com.psybot.service.patient.PatientService
import com.psybot.service.psychiatrist.PsychiatristService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.logging.Level

@Service
class EvolutionService(
    private val evolutionRepository: EvolutionRepository,
    private val patientService: PatientService,
    private val psychiatristService: PsychiatristService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun find() =
        evolutionRepository.findAll()
            .map {
                it.toResponse()
            }
            .log("find", Level.FINE, SignalType.ON_NEXT, SignalType.ON_ERROR)


    fun findById(id: Long): Mono<EvolutionResponse> =
        evolutionRepository.findById(id)
            .log("findId", Level.SEVERE, SignalType.ON_ERROR)
            .mapNotNull {
                logger.debug("found: {}", it.toString())
                it.toResponse() as EvolutionResponse
            }

    fun create(evolutionRequest: EvolutionRequest): Mono<EvolutionResponse> =
        evolutionRepository.save(evolutionRequest.toEntity())
            .mapNotNull {
                it.toResponse() as EvolutionResponse
            }
            .log("create", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)

    fun update(evolutionRequest: EvolutionRequest, id: Long): Mono<EvolutionResponse?> =
        findById(id)
            .filter { it != null }
            .switchIfEmpty { Mono.error(RuntimeException("Evolution not found")) }
            .flatMap {
                evolutionRepository.save(evolutionRequest.toEntity(id))
                    .map { it.toResponse() as EvolutionResponse }
                    .log("update", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)
            }
}

