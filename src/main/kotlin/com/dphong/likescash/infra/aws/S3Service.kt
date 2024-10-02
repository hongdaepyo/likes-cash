package com.dphong.likescash.infra.aws

import com.dphong.likescash.common.properties.AwsProps
import com.dphong.likescash.common.properties.S3Props
import org.springframework.stereotype.Component
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetUrlRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.InputStream
import kotlin.io.path.Path

@Component
class S3Service(
    private val s3Client: S3Client,
    private val awsProps: AwsProps
) {

    val s3Props: S3Props
        get() = awsProps.s3

    fun upload(data: InputStream, metadata: Map<String, String>? = null, fileName: String): String? {
        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket(s3Props.bucket)
                .key(getKey(fileName))
                .metadata(metadata)
                .build(),
            RequestBody.fromInputStream(data, data.available().toLong())
        )
        return getUrl(fileName)
    }

    fun getUrl(fileName: String): String {
        return s3Client.utilities()
            .getUrl(
                GetUrlRequest.builder()
                    .bucket(s3Props.bucket)
                    .key(getKey(fileName))
                    .build()
            )
            .toString()
    }

    fun download(fileName: String): ByteArray {
        val response = s3Client.getObject { builder ->
            builder.bucket(s3Props.bucket).key(getKey(fileName))
        }

        return response.readAllBytes()
    }

    fun delete(fileName: String): Boolean? {
        val response = s3Client.deleteObject { builder ->
            builder.bucket(s3Props.bucket).key(getKey(fileName))
        }
        return response.deleteMarker()
    }

    fun createBucket(bucketName: String = s3Props.bucket): String? =
        s3Client.createBucket { builder -> builder.bucket(bucketName) }.location()

    fun deleteBucket(bucketName: String = s3Props.bucket) {
        s3Client.deleteBucket { builder -> builder.bucket(bucketName) }
    }

    private fun getKey(fileName: String): String = Path(s3Props.subdirectory, fileName).toString()
}
