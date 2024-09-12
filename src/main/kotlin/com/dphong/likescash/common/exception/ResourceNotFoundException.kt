package com.dphong.likescash.common.exception

class ResourceNotFoundException(message: String) : RuntimeException(message) {

    constructor(source: String, identifier: Any) : this("$source not found: $identifier")
}
