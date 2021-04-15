package ido.demo.repository

import ido.demo.model.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository:JpaRepository<Project,Long> {
}