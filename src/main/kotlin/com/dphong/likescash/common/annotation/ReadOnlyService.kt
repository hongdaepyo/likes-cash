package com.dphong.likescash.common.annotation

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Transactional(readOnly = true)
@Service
annotation class ReadOnlyService
