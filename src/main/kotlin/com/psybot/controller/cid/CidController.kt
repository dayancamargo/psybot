package com.psybot.controller.cid

import com.psybot.service.cid.CidService
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//@Tag(name="cid")
//@RestController
//@RequestMapping("/cid")
class CidController(
    private val cidService: CidService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

}
