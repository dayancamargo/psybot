package com.psybot.controller.patient

import com.psybot.model.patient.PatientRequest
import com.psybot.service.patient.PatientService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("patient")
class PatientController(
    private val patientService: PatientService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun findAll() = patientService.find()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id:Long) = patientService.findByIdWithCache(id)

    @PostMapping
    fun create(@Valid @RequestBody patientRequest: PatientRequest) = patientService.create(patientRequest).log()

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id:Long,
               @Valid @RequestBody patientRequest: PatientRequest) = patientService.update(patientRequest, id)

}
