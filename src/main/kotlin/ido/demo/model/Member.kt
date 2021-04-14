package ido.demo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*


enum class Position(val value: String) {
    Manager("manager"),
    Follower("follower"),
    Assistant("assistant"),
}

@Entity
class Member : ModelWithId() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null

    @Column(nullable = false)
    lateinit var position: Position

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = false)
    @JsonIgnore
    lateinit var password: String

    @Column(nullable = true, unique = true)
    lateinit var email: String

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    lateinit var createdAt: Date

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    lateinit var updatedAt: Date

}

@Entity
class ProjectManager :Member(){
    @JsonBackReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    var manageList = mutableListOf<Project>()
}
@Entity
class ProjectFollower :Member(){
    @JsonBackReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    var projectList = mutableListOf<Project>()
}