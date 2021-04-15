package ido.demo.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
class State : ModelWithId() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null

    @Column(nullable = false)
    lateinit var name: String

    @OneToOne(optional = false)
    lateinit var stateManager: Member

    var revertable = true

    @JsonManagedReference
    @ManyToOne
    var nestStates: State? = null
}