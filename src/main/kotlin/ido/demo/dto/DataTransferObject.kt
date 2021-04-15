package ido.demo.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

enum class DTOBindType {
    Get,
    Bind,
    MapToList,
    MapToSet,
    Map,
}

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class DTOBind(
    val to: String = "",
    val type: DTOBindType = DTOBindType.Get,
    val mapName: String = "",
)

typealias SubEntityMap<T> = (T, Any) -> Any

abstract class DTO<T> {
    private val emptyMapFactories by lazy { mutableMapOf<String, SubEntityMap<T>>() }

    @JsonIgnore
    open fun getMapFactories(): MutableMap<String, SubEntityMap<T>> = emptyMapFactories

    @ExperimentalStdlibApi
    fun bindTo(entityFactory: () -> T): T {
        val entity = entityFactory()!!
        val settableProperties = entity::class.memberProperties
            .filterIsInstance<KMutableProperty1<T, Any>>()
            .map { it.name to it }
            .toMap()
        this::class.memberProperties
            .filterIsInstance<KProperty1<DTO<T>, Any>>()
            .filter { it.hasAnnotation<DTOBind>() }
            .forEach {
                val annotation = it.findAnnotation<DTOBind>()!!
                val fieldName = if (annotation.to == "") it.name else annotation.to
                val field = settableProperties[fieldName] ?: return@forEach
                val valueToSet: Any = when (annotation.type) {
                    DTOBindType.Get -> it.get(this)
                    DTOBindType.Bind -> (it.get(this) as DTO<Any>).bindTo { field.get(entity) }
                    DTOBindType.MapToList -> {
                        val mapFactory = getMapFactories()[annotation.mapName] ?: return@forEach
                        val map = { item: Any -> mapFactory(entity, item) }
                        (it.get(this) as Iterable<Any>).map(map).toMutableList()
                    }
                    DTOBindType.MapToSet -> {
                        val mapFactory = getMapFactories()[annotation.mapName] ?: return@forEach
                        val map = { item: Any -> mapFactory(entity, item) }
                        (it.get(this) as Iterable<Any>).map(map).toMutableSet()
                    }
                    DTOBindType.Map -> {
                        val mapFactory = getMapFactories()[annotation.mapName] ?: return@forEach
                        mapFactory(entity, it.get(this))
                    }
                }
                field.set(entity, valueToSet)
            }

        return entity
    }

    companion object {
        @ExperimentalStdlibApi
        inline fun <T, reified U : DTO<T>> createBasicFrom(entity: T): U {
            val dto = U::class.createInstance()

            val gettableProperties = entity!!::class.memberProperties
                .filterIsInstance<KProperty1<T, Any>>()
                .map { it.name to it }
                .toMap()

            U::class.memberProperties
                .filterIsInstance<KMutableProperty1<DTO<T>, Any>>()
                .filter { it.hasAnnotation<DTOBind>() }
                .forEach {
                    val annotation = it.findAnnotation<DTOBind>()!!
                    val fieldName = if (annotation.to == "") it.name else annotation.to
                    val field = gettableProperties[fieldName] ?: return@forEach
                    val valueToSet: Any = when (annotation.type) {
                        DTOBindType.Get -> field.get(entity)
                        DTOBindType.Bind,
                        DTOBindType.MapToList,
                        DTOBindType.MapToSet,
                        DTOBindType.Map -> return@forEach
                    }
                    it.set(dto, valueToSet)
                }

            return dto
        }
    }
}