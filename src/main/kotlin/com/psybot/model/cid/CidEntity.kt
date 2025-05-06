package com.psybot.domain.cid

import com.psybot.model.util.GenericEntity
import com.psybot.model.util.GenericResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.relational.core.mapping.Column

@Entity
@Table(name = "cid")
data class CidEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,
    @Column("name")
    val code: String? = null,
    @Column("name")
    val description: String? = null

): GenericEntity() {
    override fun toResponse(): GenericResponse {
        TODO("Not yet implemented")
    }
}