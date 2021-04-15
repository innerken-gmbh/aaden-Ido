package ido.demo.repository

import ido.demo.model.Member
import ido.demo.model.ProjectFollower
import ido.demo.model.ProjectManager

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}

interface ProjectManagerRepository : JpaRepository<ProjectManager, Long> {

}

interface ProjectFollowerRepository : JpaRepository<ProjectFollower, Long> {

}