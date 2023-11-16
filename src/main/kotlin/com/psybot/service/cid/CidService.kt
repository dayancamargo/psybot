package com.psybot.service.cid

import com.psybot.repository.cid.CidRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CidService(
    private val cidRepository: CidRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

}
