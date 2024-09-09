package com.dphong.likescash

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val BASE_PACKAGE = "com.dphong.likescash"

@SpringBootApplication
class LikesCashApplication

fun main(args: Array<String>) {
	runApplication<LikesCashApplication>(*args)
}
