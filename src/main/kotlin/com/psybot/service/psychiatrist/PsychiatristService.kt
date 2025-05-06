package com.psybot.service.psychiatrist

import com.psybot.model.patient.PatientResponse
import com.psybot.model.psychiatrist.PsychiatristEntity
import com.psybot.model.psychiatrist.PsychiatristRequest
import com.psybot.model.psychiatrist.PsychiatristResponse
import com.psybot.repository.psychiatrist.PsychiatristRepository
import com.psybot.repository.redis.CacheRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.logging.Level

@Service
class PsychiatristService(
        private val psychiatristRepository: PsychiatristRepository,
        private val cache: CacheRepository<PsychiatristEntity, PsychiatristResponse>
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun find() =
            psychiatristRepository.findAll()
                    .map {
                        it.toResponse()
                    }
                    .log("find", Level.FINE, SignalType.ON_NEXT, SignalType.ON_ERROR)

    fun findByIdWithCache(id: Long): Mono<PsychiatristResponse> {
        return cache.getResponseValue(id.toString(), PsychiatristEntity::class.java) {
            findById(id)
        }
    }
    fun findById(id: Long) =
            psychiatristRepository.findById(id)
                    .log("findId", Level.SEVERE, SignalType.ON_ERROR)
                    .mapNotNull {
                        logger.debug("found: {}", it.toString())
                        cache.putEntityOnCache(it)
                        it.toResponse() as PsychiatristResponse
                    }

    fun create(psychiatristRequest: PsychiatristRequest) =
            psychiatristRepository.save(psychiatristRequest.toEntity())
                    .mapNotNull {
                        cache.putEntityOnCache(it)
                        it.toResponse() as PatientResponse
                    }
                    .log("create", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)

    fun update(psychiatristRequest: PsychiatristRequest, id: Long): Mono<PsychiatristResponse> =
            findById(id)
                    .filter { it != null }
                    .switchIfEmpty { Mono.error(RuntimeException("Psychiatrist not found")) }
                    .flatMap {
                        psychiatristRepository.save(psychiatristRequest.toEntity(id))
                                .map { it.toResponse() as PsychiatristResponse }
                                .log("update", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)
                    }
}

