package com.dphong.likescash

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<LikesCashApplication>().with(TestcontainersConfiguration::class).run(*args)
}
