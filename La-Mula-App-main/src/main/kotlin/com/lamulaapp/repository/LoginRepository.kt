package com.lamulaapp.repository

import com.lamulaapp.controller.dto.LoginResponseDto
import com.lamulaapp.domain.Login
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LoginRepository: JpaRepository<Login, UUID> {
    @Query(value = """
        select
            coalesce(use.id_user, com.id_company) as idEntity,
            coalesce(use.name, com.name) as name,
            log.username,
            log.role as roleName
        from logins log
            left join users use ON log.id_login = use.id_login
            left join companies com ON log.id_login = com.id_login
        where log.username = :username
        and log.password = :password
    """, nativeQuery = true)
    fun findLoginResponseByUsernameAndPassword(username: String, password: String): Optional<LoginResponseDto>
}