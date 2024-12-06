package com.lamulaapp.controller

import com.lamulaapp.controller.dto.AttachmentDto
import com.lamulaapp.controller.utils.validateCreateAttachment
import com.lamulaapp.controller.utils.validateUpdateAttachment
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.AttachmentService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class AttachmentController(
    private val attachmentService: AttachmentService
) {

    @PostMapping("/attachments")
    @Operation(
        summary = "Create a new attachment",
        description = "This endpoint allows you to create a new attachment in the system."
    )
    fun createAttachment(@RequestBody attachmentDto: AttachmentDto): ResponseEntity<AttachmentDto> {
        val validation = validateCreateAttachment(attachmentDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(attachmentService.createAttachment(attachmentDto), HttpStatus.CREATED)
    }

    @GetMapping("/attachments")
    @Operation(
        summary = "List all attachments",
        description = "This endpoint allows listing all attachments from the database."
    )
    fun getAttachments(): ResponseEntity<List<AttachmentDto>> {
        return ResponseEntity(attachmentService.getAttachments(), HttpStatus.OK)
    }

    @GetMapping("/attachments/{id}")
    @Operation(
        summary = "List an attachment by ID",
        description = "This endpoint searches the database for the attachment and lists it by id."
    )
    fun getAttachmentById(@PathVariable("id") id: UUID): ResponseEntity<AttachmentDto> {
        return ResponseEntity(attachmentService.getAttachmentById(id), HttpStatus.OK)
    }

    @PutMapping("/attachments/{id}")
    @Operation(
        summary = "Update the attachments",
        description = "This endpoint searches the database for the attachment by ID and updates it."
    )
    fun updateAttachment(@PathVariable("id") id: UUID, @RequestBody attachmentDto: AttachmentDto): ResponseEntity<AttachmentDto> {
        val validation = validateUpdateAttachment(attachmentDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(attachmentService.updateAttachment(id, attachmentDto), HttpStatus.OK)
    }

    @DeleteMapping("/attachments/{id}")
    @Operation(
        summary = "Delete the attachment",
        description = "This endpoint searches the database for the attachment by ID and deletes it."
    )
    fun deleteAttachment(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(attachmentService.deleteAttachment(id), HttpStatus.NO_CONTENT)
    }
}