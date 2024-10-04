package com.dphong.likescash.api.file

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.infra.aws.S3Service
import com.dphong.likescash.mock.FakeAuthentication
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.multipart

@BaseWebMvcTest([FileController::class])
class FileControllerTest(
    private val mockMvc: MockMvc,
    @MockBean private val s3Service: S3Service
) {

    @Test
    fun `파일을 업로드한다`() {
        // given
        SecurityContextHolder.getContext().authentication = FakeAuthentication.MEMBER.authentication
        val file = MockMultipartFile(
            "file",
            "testFile.txt",
            MediaType.MULTIPART_FORM_DATA_VALUE,
            "testContent".toByteArray()
        )
        given(s3Service.upload(any(), anyMap(), anyString()))
            .willReturn("https://testBucket.s3.amazonaws.com/testFile.txt")

        // when
        // then
        mockMvc.multipart("/v1/files") { file(file) }
            .andExpect {
                status { isOk() }
                jsonPath("$.data") { value("https://testBucket.s3.amazonaws.com/testFile.txt") }
            }
    }
}
