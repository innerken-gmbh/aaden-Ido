package ido.demo.model

abstract class ModelWithId : ModelInterface {
    abstract var id: Long?
}

abstract class ModelWithUuid : ModelInterface {
    abstract var uuid: String?
}