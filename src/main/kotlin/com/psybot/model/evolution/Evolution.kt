package com.psybot.model.evolution

import com.psybot.model.util.GenericResponse
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class EvolutionRequest(
        val id: Long?,
        @field:NotBlank(message = "{field.validation.not.empty}")
        val description: String,
        @field:NotBlank(message = "{field.validation.not.empty}")
        val type: String,
        @field:NotNull(message = "{field.validation.not.empty}")
        val idPatient: Long,
        @field:NotNull(message = "{field.validation.not.empty}")
        val idPsychiatrist: Long
) {
    fun toEntity() =
            EvolutionEntity(
                    id = this.id,
                    description = this.description,
                    type = this.type,
                    idPatient = idPatient,
                    idPsychiatrist = idPsychiatrist
            )

    fun toEntity(id: Long) =
            EvolutionEntity(
                    id = id,
                    description = this.description,
                    type = this.type,
                    idPatient = idPatient,
                    idPsychiatrist = idPsychiatrist
            )
}

// todo put joins :)
data class EvolutionResponse(
        val id: Long?,
        val description: String,
        val type: String,
        val createAt: LocalDate?,
        val updateAt: LocalDate?
) : GenericResponse()

