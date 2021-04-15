package ido.demo.controller


import ido.demo.model.Task
import ido.demo.repository.TaskRepository
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/task")
class TaskController(
    val taskRepository: TaskRepository,
) {
    @ResponseBody
    @GetMapping("/getList")
    fun getList(): MutableList<Task> {
        return taskRepository.findAll()
    }
}