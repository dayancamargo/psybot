package com.psybot.repository.redis

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.psybot.model.util.GenericEntity
import com.psybot.model.util.GenericResponse
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.Duration
import java.util.*


@Component
class CacheRepository<E:GenericEntity,  GR : GenericResponse>(
        private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
        @Value("\${spring.data.redis.ttl}")
        private val cacheTtl: Duration
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val objectMapper = jacksonObjectMapper()

    init {
        objectMapper.registerModule(JavaTimeModule())
    }

    private fun read(key: String) = reactiveRedisTemplate.opsForValue().get(key)
            .doOnError {
                logger.error("Error reading redis", it)
            }

    private fun write(key: String, value: String) = reactiveRedisTemplate.opsForValue().set(key, value, cacheTtl)
            .doOnError {
                logger.error("Error writing redis", it)
            }
            .doOnSuccess {
                if (logger.isDebugEnabled)
                    logger.debug("Wrote: $key")
            }

    fun getResponseValue(key: String, clazz: Class<E>, default: (id: Long) -> Mono<GR>): Mono<GR> {
        return read(key)
                .map {
                    logger.debug("Retrieve object {} of {} ",it,  clazz.simpleName)
                    val v = objectMapper.readValue(it, clazz)
                    v.toResponse() as GR
                }.switchIfEmpty {
                    default(key.toLong())
                }
    }

    fun putEntityOnCache(entity: E) {
        if (Objects.nonNull(entity)) {
            write(entity.id.toString(), ReflectionToStringBuilder.toString(entity, ToStringStyle.JSON_STYLE)).subscribe()
        }
    }
}
