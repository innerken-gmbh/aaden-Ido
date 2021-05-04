package ido.demo.repository

import ido.demo.model.Member

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface MemberRepository : JpaRepository<Member, Long> {
    fun findByName(name: String): Optional<Member>
}

