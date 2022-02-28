package io.iamcyw.ams.domain.job.strategy

expect open class AlarmSourcePO() {
    constructor(id: Long)

    constructor(id: Long, name: String)

    open var id: Long?

    open var name: String
}