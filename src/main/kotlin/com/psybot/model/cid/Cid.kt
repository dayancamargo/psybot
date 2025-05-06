package com.psybot.model.cid

import com.psybot.domain.cid.CidEntity
import com.psybot.model.util.GenericResponse
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class CidRequest(
        val id: Long?,
        @field:NotBlank(message = "{field.validation.not.empty}")
        val code: String,
        @field:NotBlank(message = "{field.validation.not.empty}")
        val description: String
) {
    fun toEntity() =
            CidEntity(
                    id = this.id,
                    code = this.code,
                    description = this.description
            )

    fun toEntity(id: Long) =
            CidEntity(
                    id = id,
                    code = this.code,
                    description = this.description
            )

}

data class CidResponse(
        val id: Long?,
        val code: String,
        val description: String,
        val createAt: LocalDateTime?,
        val updateAt: LocalDateTime?
) : GenericResponse()
