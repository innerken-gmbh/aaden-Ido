package ido.demo.service

import ido.demo.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    val projectRepository: ProjectRepository,
) {
}