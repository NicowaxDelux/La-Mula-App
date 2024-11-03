package com.lamulaapp.controller

import com.lamulaapp.controller.dto.AttachmentDto
import com.lamulaapp.controller.utils.validateCreateAttachment
import com.lamulaapp.controller.utils.validateUpdateAttachment
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.AttachmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class AttachmentController(
    private val attachmentService: AttachmentService
) {

    @PostMapping("/attachments")
    fun createAttachment(@RequestBody attachmentDto: AttachmentDto): ResponseEntity<AttachmentDto> {
        val validation = validateCreateAttachment(attachmentDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(attachmentService.createAttachment(attachmentDto), HttpStatus.CREATED)
    }

    @GetMapping("/attachments")
    fun getAttachments(): ResponseEntity<List<AttachmentDto>> {
        return ResponseEntity(attachmentService.getAttachments(), HttpStatus.OK)
    }

    @GetMapping("/attachments/{id}")
    fun getAttachmentById(@PathVariable("id") id: UUID): ResponseEntity<AttachmentDto> {
        return ResponseEntity(attachmentService.getAttachmentById(id), HttpStatus.OK)
    }

    @PutMapping("/attachments/{id}")
    fun updateAttachment(@PathVariable("id") id: UUID, @RequestBody attachmentDto: AttachmentDto): ResponseEntity<AttachmentDto> {
        val validation = validateUpdateAttachment(attachmentDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(attachmentService.updateAttachment(id, attachmentDto), HttpStatus.OK)
    }

    @DeleteMapping("/attachments/{id}")
    fun deleteAttachment(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(attachmentService.deleteAttachment(id), HttpStatus.NO_CONTENT)
    }
}