package com.dphong.likescash.infra.aws

import com.dphong.likescash.IntegrationTest
import com.dphong.likescash.common.properties.AwsProps
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.NoSuchKeyException

@Disabled
@Testcontainers
class S3ServiceTest(
    private val awsProps: AwsProps,
) : IntegrationTest() {

    private lateinit var s3Service: S3Service
    private lateinit var s3Client: S3Client

    @Container
    private val container = LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"))
        .withServices(LocalStackContainer.Service.S3)

    @BeforeEach
    fun setUp() {
        s3Client = S3Client.builder()
            .endpointOverride(container.endpoint)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(container.accessKey, container.secretKey)
                )
            )
            .build()
        s3Service = S3Service(s3Client, awsProps)
        s3Service.createBucket()
    }

    @Test
    fun `파일을 s3에 업로드한다`() {
        // given
        val fileName = "test.txt"
        // when
        val url = s3Service.upload("test".byteInputStream(), mapOf("testKey" to "testValue"), fileName)
        // then
        assertThat(url).contains(fileName)
    }

    @Test
    fun `s3에서 파일을 다운로드한다`() {
        // given
        val fileName = "test.txt"
        s3Service.upload("test".byteInputStream(), mapOf("testKey" to "testValue"), fileName)
        // when
        val bytes = s3Service.download(fileName)
        // then
        assertThat(bytes).isEqualTo("test".toByteArray())
    }

    @Test
    fun `s3에서 파일을 삭제한다`() {
        // given
        val fileName = "test.txt"
        s3Service.upload("test".byteInputStream(), mapOf("testKey" to "testValue"), fileName)
        // when
        s3Service.delete(fileName)
        // then
        assertThatThrownBy { s3Service.download(fileName) }
            .isInstanceOf(NoSuchKeyException::class.java)
    }

    @AfterEach
    fun tearDown() {
        s3Client.listObjectsV2 { it.bucket(awsProps.s3.bucket) }
            .contents()
            .forEach { s3Object ->
                s3Client.deleteObject {
                    it.bucket(awsProps.s3.bucket).key(s3Object.key())
                }
            }
        s3Service.deleteBucket()
    }

}
