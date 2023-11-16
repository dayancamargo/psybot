package com.psybot.model.psychiatrist

import com.psybot.utils.datasource.AuditCommons
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column
import org.hibernate.envers.AuditOverride
import org.springframework.data.annotation.Id

@Table(name = "psychiatrist")
@AuditOverride(forClass = AuditCommons::class)
data class PsychiatristEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String? = null,
    @Column("phone_number")
    val phoneNumber: String? = null

) :AuditCommons()