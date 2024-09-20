package com.dphong.likescash.common.extension

import java.time.Instant
import java.time.temporal.ChronoUnit

fun Instant.minusDays(days: Long): Instant = this.minus(days, ChronoUnit.DAYS)
fun Instant.plusDays(days: Long): Instant = this.plus(days, ChronoUnit.DAYS)
