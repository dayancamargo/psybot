package com.psybot.model.evolution

import com.psybot.model.util.GenericEntity
import com.psybot.model.util.GenericResponse
import com.psybot.utils.datasource.AuditCommons
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.hibernate.envers.AuditOverride
import org.springframework.data.annotation.Id

@Table(name = "evolution")
@AuditOverride(forClass = AuditCommons::class)
data class EvolutionEntity(
        @Id
        override var id: Long?,
        val description: String?,
        val type: String?,
        @Column("id_patient")
        val idPatient: Long?,
        @Column("id_psychiatrist")
        val idPsychiatrist: Long?
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_patient")
//    val patient: PatientEntity?,
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_psychiatrist")
//    val psychiatrist: PsychiatristEntity?,
) : GenericEntity() {
    override fun toResponse(): GenericResponse {
        return EvolutionResponse(
                id = this.id,
                description = this.description!!,
                type = this.type!!,
                createAt = this.createdAt?.toLocalDate(),
                updateAt = this.updatedAt?.toLocalDate(),
        )
    }
}