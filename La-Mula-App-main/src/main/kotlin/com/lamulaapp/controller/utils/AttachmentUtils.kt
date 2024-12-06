package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.AttachmentDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateAttachment(attachmentDto: AttachmentDto): ValidationResult<AttachmentDto> {
    val validateAttachment = Validation {
        AttachmentDto::name required {}
        AttachmentDto::content required {}
        AttachmentDto::contentType required {}
        AttachmentDto::createdAt required {}
        AttachmentDto::createdBy required {}
    }

    return validateAttachment(attachmentDto)
}

fun validateUpdateAttachment(attachmentDto: AttachmentDto): ValidationResult<AttachmentDto> {
    val validateAttachment = Validation {
        AttachmentDto::idAttachment required {}
        AttachmentDto::name required {}
        AttachmentDto::content required {}
        AttachmentDto::contentType required {}
        AttachmentDto::createdAt required {}
        AttachmentDto::createdBy required {}
        AttachmentDto::updatedAt required {}
        AttachmentDto::updatedBy required {}
    }

    return validateAttachment(attachmentDto)
}