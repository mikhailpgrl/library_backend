package com.mikhail.library.entity

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findOneByUsername(username: String): UserEntity?
}
