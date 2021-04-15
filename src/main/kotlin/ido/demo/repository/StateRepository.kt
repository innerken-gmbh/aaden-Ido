package ido.demo.repository

import ido.demo.model.State
import org.springframework.data.jpa.repository.JpaRepository

interface StateRepository : JpaRepository<State, Long> {
}