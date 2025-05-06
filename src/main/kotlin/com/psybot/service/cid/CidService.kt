package com.psybot.service.cid

import com.psybot.model.cid.CidRequest
import com.psybot.model.cid.CidResponse
import com.psybot.repository.cid.CidRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.logging.Level

@Service
class CidService(
        private val cidRepository: CidRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun find() =
            cidRepository.findAll()
                    .map {
                        it.toResponse()
                    }
                    .log("find", Level.FINE, SignalType.ON_NEXT, SignalType.ON_ERROR)


    fun findById(id: Long): Mono<CidResponse> =
            cidRepository.findById(id)
                    .log("findId", Level.SEVERE, SignalType.ON_ERROR)
                    .mapNotNull {
                        logger.debug("found: {}", it.toString())
                        it.toResponse() as CidResponse
                    }

    fun create(CidRequest: CidRequest): Mono<CidResponse> =
            cidRepository.save(CidRequest.toEntity())
                    .mapNotNull {
                        it.toResponse() as CidResponse
                    }
                    .log("create", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)

    fun update(CidRequest: CidRequest, id: Long): Mono<CidResponse?> =
            findById(id)
                    .filter { it != null }
                    .switchIfEmpty { Mono.error(RuntimeException("Cid not found")) }
                    .flatMap {
                        cidRepository.save(CidRequest.toEntity(id))
                                .map { it.toResponse() as CidResponse }
                                .log("update", Level.INFO, SignalType.ON_NEXT, SignalType.ON_ERROR)
                    }

}
