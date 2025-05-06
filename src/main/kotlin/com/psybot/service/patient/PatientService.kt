package com.psybot.service.patient

import com.psybot.model.patient.PatientEntity
import com.psybot.model.patient.PatientRequest
import com.psybot.model.patient.PatientResponse
import com.psybot.repository.patient.PatientRepository
import com.psybot.repository.redis.CacheRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.logging.Level

@Service
class PatientService(
        private val patientRepository: PatientRepository,
        private val cache: CacheRepository<PatientEntity, PatientResponse>
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun find() =
        patientRepository.findAll()
            .map {
                it.toResponse()
            }
            .doOnComplete {
                logger.info(" findAll completed ")
            }
            .log("find", Level.INFO, SignalType.ON_COMPLETE, SignalType.ON_ERROR)

    fun findByIdWithCache(id: Long): Mono<PatientResponse> {
        return cache.getResponseValue(id.toString(), PatientEntity::class.java) {
            findById(id)
        }
    }

    fun findById(id: Long): Mono<PatientResponse> =
         patientRepository.findById(id)
            .log("findId", Level.SEVERE, SignalType.ON_ERROR)
            .mapNotNull {
                logger.debug("found: {}", it.toString())
                cache.putEntityOnCache(it)
                it.toResponse() as PatientResponse
            }

    fun create(patientRequest: PatientRequest): Mono<PatientResponse> =
            patientRepository.save(patientRequest.toEntity())
                .mapNotNull {
                    cache.putEntityOnCache(it)
                    it.toResponse() as PatientResponse
                }
                .log("create", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)

    fun update(patientRequest: PatientRequest, id: Long): Mono<PatientResponse?> =
            findById(id)
                .filter { it != null }
                .switchIfEmpty{ Mono.error(RuntimeException("Patient not found")) }
                .flatMap {
                    patientRepository.save(patientRequest.toEntity(id))
                        .map { it.toResponse() as PatientResponse }
                        .doOnSuccess { logger.info("Udpdated patient{}", it.id) }
                        .log("update", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)
                }
}