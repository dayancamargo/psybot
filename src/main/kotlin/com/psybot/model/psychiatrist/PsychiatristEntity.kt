package com.psybot.model.psychiatrist

import com.psybot.model.util.GenericEntity
import com.psybot.model.util.GenericResponse
import com.psybot.utils.datasource.AuditCommons
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.hibernate.envers.AuditOverride
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "psychiatrist")
@AuditOverride(forClass = AuditCommons::class)
data class PsychiatristEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long? = null,
        val name: String? = null,
        @Column("phone_number")
        val phoneNumber: String? = null

) : GenericEntity() {
    override fun toResponse(): GenericResponse {
        return PsychiatristResponse(
                id = this.id!!,
                name = this.name!!,
                phoneNumber = this.phoneNumber!!,
                createAt = this.createdAt?.toLocalDate(),
                updateAt = this.updatedAt?.toLocalDate(),
        )
    }
}