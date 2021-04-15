package ido.demo.controller

import ido.demo.model.Member
import ido.demo.model.Project
import ido.demo.repository.ProjectRepository
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
@RequestMapping("/project")
class ProjectController(
    val projectRepository: ProjectRepository,
) {

    @ResponseBody
    @GetMapping("/getList")
    fun getList(): MutableList<Project> {
        return projectRepository.findAll()
    }
}