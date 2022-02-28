package io.iamcyw.ams.domain.job.strategy

@kotlinx.serialization.Serializable
actual open class AlarmSourcePO actual constructor() {
    actual open var id: Long? = 0
    actual open var name: String = ""

    actual constructor(id: Long) : this() {
        this.id = id
    }

    actual constructor(id: Long, name: String) : this() {
        this.id = id
        this.name = name
    }
}