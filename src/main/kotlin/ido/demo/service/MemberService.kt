package ido.demo.service

import ido.demo.model.MandP
import ido.demo.model.Member
import ido.demo.model.Position
import ido.demo.model.Project
import ido.demo.orElseNotFound
import ido.demo.orNull
import ido.demo.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberRepository: MemberRepository,
) {
    fun findByName(name: String): Member = memberRepository.findByName(name).orNull().orElseNotFound()

    fun findProject(member: Member): MutableList<MandP> = member.jobList

    fun findManageJob(member: Member): List<Project> =
        member.jobList.filter { it.position == Position.Manager }.map { it.project }

    fun findFollowJob(member: Member): List<Project> =
        member.jobList.filter { it.position == Position.Follower }.map { it.project }


}