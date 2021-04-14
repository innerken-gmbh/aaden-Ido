package ido.demo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
class Task : ModelWithId() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null

    @JsonBackReference
    @ManyToOne(optional = false)
    lateinit var project: Project

    @JsonBackReference
    @ManyToOne(optional = false)
    lateinit var member: Member

    @Temporal(TemporalType.TIMESTAMP)
    lateinit var deadLine: Date

    @JsonBackReference
    @ManyToOne(optional = false)
    lateinit var state: State

    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "Task")
    var transferLogList = mutableListOf<TransferLog>()

}

@Entity
class TransferLog : ModelWithId() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
    var step: Long? = null

    @OneToOne(optional = false)
    lateinit var member: Member

    @OneToOne(optional = false)
    lateinit var state: State

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    lateinit var createdAt: Date
}