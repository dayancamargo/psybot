package com.psybot.service.evolution

import com.psybot.model.evolution.EvolutionEntity
import com.psybot.model.evolution.EvolutionRequest
import com.psybot.repository.evolution.EvolutionRepository
import com.psybot.service.patient.PatientService
import com.psybot.service.psychiatrist.PsychiatristService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EvolutionService(
    private val evolutionRepository: EvolutionRepository,
    private val patientService: PatientService,
    private val psychiatristService: PsychiatristService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun find() =
        evolutionRepository.findAll()

    fun create(evolutionRequest: EvolutionRequest): Mono<EvolutionEntity> {
        return Mono.zip(
            patientService.findById(evolutionRequest.idPatient),
            psychiatristService.findById(evolutionRequest.idPsychiatrist)
        ).flatMap { evolutionRepository.save(evolutionRequest.toEntity()) }
    }
}

