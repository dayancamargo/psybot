package com.psybot.repository.evolution

import com.psybot.model.evolution.EvolutionEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface EvolutionRepository : R2dbcRepository<EvolutionEntity, Long>