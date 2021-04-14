package ido.demo.model

import javax.persistence.*

@Entity
class State : ModelWithId() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null

    @Column(nullable = false)
    lateinit var name: String

    @OneToOne(optional = false)
    lateinit var member: Member

    var revertable = true

}