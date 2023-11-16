package com.psybot.model.patient

import com.psybot.utils.datasource.AuditCommons
import jakarta.persistence.Entity
import org.hibernate.envers.AuditOverride
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Entity
@Table(name = "patient")
@AuditOverride(forClass = AuditCommons::class)
data class PatientEntity (
    @Id
    @Column("id")
    val id: Long? = null ,
    @Column("name")
    val name: String? = null,
    @Column("phone_number")
    val phoneNumber: String? = null,
    @Column("email")
    val email: String? = null

) :AuditCommons()










































