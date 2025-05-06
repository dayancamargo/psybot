package com.psybot.model.patient

import com.psybot.model.util.GenericResponse
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class PatientRequest(
        val id: Long?,
        @field:NotBlank(message = "{field.validation.not.empty}")
        val name: String,
        @field:NotBlank(message = "{field.validation.not.empty}")
        @field:Size(min = 11, max = 11, message = "{field.validation.exact.size}")
        @field:Pattern(regexp = "^[0-9]*\$", message = "{field.validation.only.numbers}")
        val phoneNumber: String,
        @field:Email(message = "{field.validation.not.empty}")
        val email: String,
) {
    fun toEntity() =
            PatientEntity(
                    id = this.id,
                    name = this.name,
                    phoneNumber = this.phoneNumber,
                    email = this.email
            )

    fun toEntity(id: Long) =
            PatientEntity(
                    id = id,
                    name = this.name,
                    phoneNumber = this.phoneNumber,
                    email = this.email
            )
}

// todo put joins :)
data class PatientResponse(
        val id: Long?,
        val name: String,
        val phoneNumber: String,
        val email: String?,
        val createAt: LocalDateTime?,
        val updateAt: LocalDateTime?
) : GenericResponse()
