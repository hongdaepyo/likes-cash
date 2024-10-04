package com.dphong.likescash.api.file

import com.dphong.likescash.common.Names
import com.dphong.likescash.common.response.DataResult
import com.dphong.likescash.infra.aws.S3Service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("/v1/files")
@RestController
class FileController(
    private val s3Service: S3Service
) {

    @PostMapping
    fun upload(@RequestPart("file") file: MultipartFile): ResponseEntity<DataResult<String>> =
        s3Service.upload(
            file.inputStream,
            mapOf(
                Names.ORIGINAL_FILE_NAME to file.originalFilename.orEmpty(),
                Names.CONTENT_TYPE to file.contentType.orEmpty(),
            ),
            UUID.randomUUID().toString()
        ).let { url ->
            DataResult(url).toResponseEntity()
        }
}
