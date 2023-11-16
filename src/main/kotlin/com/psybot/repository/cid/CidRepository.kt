package com.psybot.repository.cid

import com.psybot.domain.cid.CidEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface CidRepository : R2dbcRepository<CidEntity, Long>