package com.psybot.model.util

import com.psybot.utils.datasource.AuditCommons

abstract class GenericEntity: AuditCommons() {
    abstract var id: Long?
    abstract fun toResponse():GenericResponse
}

abstract class GenericResponse










































