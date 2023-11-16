package com.psybot.model.evolution

import com.psybot.utils.datasource.AuditCommons
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.hibernate.envers.AuditOverride
import org.springframework.data.annotation.Id

@Table(name = "evolution")
@AuditOverride(forClass = AuditCommons::class)
data class EvolutionEntity (
    @Id
    val id: Long?,
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
) :AuditCommons()