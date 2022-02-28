package io.iamcyw.ams.domain.job.strategy

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "ALARM_SOURCE")
@kotlinx.serialization.Serializable
actual open class AlarmSourcePO actual constructor() : PanacheEntityBase {

    companion object : PanacheCompanion<AlarmSourcePO> {
        fun findByName(name: String) = find("name", name).singleResult()

    }

    @field:Id
    @field:GeneratedValue
    actual open var id: Long? = null

    @get:Column(name = "name")
    actual open var name: String = ""

    actual constructor(id: Long) : this() {
        this.id = id
    }

    actual constructor(id: Long, name: String) : this() {
        this.id = id
        this.name = name
    }


}