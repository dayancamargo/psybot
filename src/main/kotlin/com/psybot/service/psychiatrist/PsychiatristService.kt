package com.psybot.service.psychiatrist

import com.psybot.model.psychiatrist.PsychiatristRequest
import com.psybot.model.psychiatrist.toResponse
import com.psybot.repository.psychiatrist.PsychiatristRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PsychiatristService(
    private val psychiatristRepository: PsychiatristRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun find() =
        psychiatristRepository.findAll()

    fun findById(id:Long) =
        psychiatristRepository.findById(id)

    fun create(psychiatristRequest: PsychiatristRequest) = psychiatristRepository.save(psychiatristRequest.toEntity()).map { it.toResponse() }

    fun update(psychiatristRequest: PsychiatristRequest, id:Long) =
        psychiatristRepository.save(psychiatristRequest.toEntity(id)).map { it.toResponse() }

}

