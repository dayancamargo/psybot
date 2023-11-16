package com.psybot.controller

import com.google.cloud.language.v1beta2.AnalyzeSentimentResponse
import com.psybot.service.google.BardService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ai")
class BardController(val bardService: BardService) {

    @GetMapping("/sentiment")
    fun getSentiment(@RequestParam("text") text: String?): AnalyzeSentimentResponse {
        return bardService.process(text)
    }
}