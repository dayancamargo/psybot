package com.psybot.controller.patient

import com.psybot.model.patient.PatientRequest
import com.psybot.service.patient.PatientService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/patient")
class PatientController(
    private val patientService: PatientService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun findAll() =
        patientService.find()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id:Long) =
        patientService.findById(id)

    @PostMapping
    fun create(@RequestBody patientRequest: PatientRequest) =
        patientService.create(patientRequest)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id:Long,
               @RequestBody patientRequest: PatientRequest
    ) =
        patientService.update(patientRequest, id)

}
