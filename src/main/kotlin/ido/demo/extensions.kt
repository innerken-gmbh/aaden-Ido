package ido.demo

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import ido.demo.exception.NoSuchEntityException
import ido.demo.model.ModelWithId
import java.util.*

inline fun <reified T : Any> T?.orElseNotFound(): T {
    return this ?: throw NoSuchEntityException(T::class.simpleName ?: "Entity")
}

fun <T> Optional<T>.orNull(): T? = this.orElse(null)

fun <T : Any> fetchItemId(item: T): Any {
    return (item as ModelWithId).id!!
}

open class MutableCollectionSerializer<T : Any>(t: Class<MutableCollection<T>>? = null) :
    StdSerializer<MutableCollection<T>>(t) {

    fun toSingleItem(item: T): Any {
        return fetchItemId(item)
    }

    override fun serialize(
        p0: MutableCollection<T>?,
        p1: JsonGenerator?,
        p2: SerializerProvider?
    ) {
        p1?.writeObject(p0?.map { toSingleItem(it) }?.toMutableList())
    }
}

open class NullableEntityIdSerializer<T : Any>(t: Class<T>?) :
    StdSerializer<T>(t) {
    constructor() : this(null)

    override fun serialize(p0: T?, p1: JsonGenerator?, p2: SerializerProvider?) {
        p1?.writeObject((p0 as ModelWithId?)?.id)
    }
}


