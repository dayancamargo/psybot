package com.psybot.model.patient

import com.psybot.model.util.GenericEntity
import com.psybot.model.util.GenericResponse
import com.psybot.utils.datasource.AuditCommons
import org.hibernate.envers.AuditOverride
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "patient")
@AuditOverride(forClass = AuditCommons::class)
data class PatientEntity(

        @Id
        @Column("id")
        override var id: Long? = null,
        @Column("name")
        val name: String? = null,
        @Column("phone_number")
        val phoneNumber: String? = null,
        @Column("email")
        val email: String? = null

) : GenericEntity() {
    override fun toResponse(): GenericResponse {
        return PatientResponse(
                id = this.id,
                name = this.name!!,
                phoneNumber = this.phoneNumber!!,
                email = this.email,
                createAt = this.createdAt,
                updateAt = this.updatedAt,
        )
    }
}










































