package com.psybot.repository.psychiatrist

import com.psybot.model.psychiatrist.PsychiatristEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface PsychiatristRepository : R2dbcRepository<PsychiatristEntity, Long>

