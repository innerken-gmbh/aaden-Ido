package ido.demo.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
class Project :ModelWithId(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id:Long? = null


    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL],mappedBy = "Project")
    var followers = mutableListOf<ProjectFollower>()

    @JsonManagedReference
    @ManyToOne(optional = false)
    lateinit var manager : ProjectManager

    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL],mappedBy = "Project")
    var tasks = mutableListOf<Task>()

    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL],mappedBy = "Project")
    var states = mutableListOf<State>()
}