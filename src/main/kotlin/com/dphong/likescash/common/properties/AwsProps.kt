package com.dphong.likescash.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws")
data class AwsProps(
    val s3: S3Props
)

data class S3Props(
    val bucket: String,
    val subdirectory: String
)
