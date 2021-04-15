package ido.demo.controller

import ido.demo.model.Member
import ido.demo.repository.MemberRepository
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
@RequestMapping("/member")
class MemberController(
    val memberRepository: MemberRepository,
) {
    @ResponseBody
    @GetMapping("/getList")
    fun getList(): List<Member>{
        return memberRepository.findAll()
    }

}