package com.psybot.domain.cid

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cid")
data class CidEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long
)