package ido.demo.controller

import ido.demo.model.Member
import ido.demo.model.State
import ido.demo.repository.StateRepository
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/state")
class StateController
    (
    val stateRepository: StateRepository,
) {
    @ResponseBody
    @GetMapping("/getList")
    fun getList(): MutableList<State> {
        return stateRepository.findAll()
    }
}