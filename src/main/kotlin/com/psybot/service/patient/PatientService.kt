package com.psybot.service.patient

import com.psybot.model.patient.PatientRequest
import com.psybot.model.patient.PatientResponse
import com.psybot.model.patient.toResponse
import com.psybot.repository.patient.PatientRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType
import java.util.logging.Level

@Service
class PatientService(
    private val patientRepository: PatientRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun find() =
        patientRepository.findAll()
            .map {
                it.toResponse()
            }
            .log("find", Level.CONFIG, SignalType.ON_NEXT, SignalType.ON_ERROR)

    fun findById(id: Long) =
        patientRepository.findById(id).map { it.toResponse() }.log("findId", Level.SEVERE, SignalType.ON_ERROR)

    fun create(patientRequest: PatientRequest) =
        patientRepository.save(patientRequest.toEntity()).map { it.toResponse() }.log("create", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)

    fun update(patientRequest: PatientRequest, id: Long): Mono<PatientResponse?> =
        patientRepository.save(patientRequest.toEntity(id)).map { it.toResponse() }.log("update", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)

}

