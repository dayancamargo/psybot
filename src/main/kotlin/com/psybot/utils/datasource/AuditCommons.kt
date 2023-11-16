package com.psybot.utils.datasource

import jakarta.persistence.Embeddable
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Embeddable
@MappedSuperclass
abstract class AuditCommons : Serializable {
    @CreatedDate
    @Column("created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @LastModifiedDate
    @Column("updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now()
}
