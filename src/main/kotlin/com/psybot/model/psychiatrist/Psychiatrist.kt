package com.psybot.model.psychiatrist

import com.psybot.model.util.GenericResponse
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class PsychiatristRequest(
        val id: Long?,
        @field:NotBlank(message = "{field.validation.not.empty}")
        val name: String,
        @field:NotBlank(message = "{field.validation.not.empty}")
        @field:Size(min = 11, max = 11, message = "{field.validation.exact.size}")
        @field:Pattern(regexp = "[\\d]{11}", message = "{field.validation.only.numbers}")
        val phoneNumber: String,
) {
    fun toEntity() =
            PsychiatristEntity(
                    id = this.id,
                    name = this.name,
                    phoneNumber = this.phoneNumber
            )

    fun toEntity(id: Long) =
            PsychiatristEntity(
                    id = id,
                    name = this.name,
                    phoneNumber = this.phoneNumber
            )
}

// todo put joins :)
data class PsychiatristResponse(
        val id: Long,
        val name: String,
        val phoneNumber: String,
        val createAt: LocalDate?,
        val updateAt: LocalDate?
) : GenericResponse()