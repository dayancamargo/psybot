package com.psybot.repository.patient

import com.psybot.model.patient.PatientEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository : R2dbcRepository<PatientEntity, Long>
