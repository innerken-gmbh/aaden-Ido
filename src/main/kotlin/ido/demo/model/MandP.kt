package ido.demo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

enum class Position(val value: String) {
    Manager("manager"),
    Follower("follower"),
    Assistant("assistant"),
}

@Entity
class MandP : ModelWithId() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null

    @JsonBackReference
    @OneToOne
    lateinit var member: Member

    @JsonBackReference
    @OneToOne
    lateinit var project: Project

    @Column(nullable = false)
    lateinit var position: Position

}