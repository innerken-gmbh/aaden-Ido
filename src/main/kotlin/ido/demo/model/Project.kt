package ido.demo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
class Project : ModelWithId() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null

    @JsonBackReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "Project")
    val jobList = mutableListOf<MandP>()

    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "Project")
    var tasks = mutableListOf<Task>()

    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "Project")
    var states = mutableListOf<State>()

    @GeneratedValue()
}