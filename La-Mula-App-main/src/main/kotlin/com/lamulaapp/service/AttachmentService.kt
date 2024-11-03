package com.lamulaapp.service

import com.lamulaapp.controller.dto.AttachmentDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.AttachmentRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AttachmentService(
    private val attachmentRepository: AttachmentRepository
) {

    fun createAttachment(attachmentDto: AttachmentDto): AttachmentDto {
        val responsePKFound = attachmentDto.idAttachment?.let { attachmentRepository.findById(it) }

        if (responsePKFound != null && responsePKFound.isPresent) {
            throw DuplicateKeyException("This ID already exists for the attachment to be created!")
        }

        val response = attachmentRepository.save(attachmentDto.toEntity())
        return response.toDto()
    }

    fun getAttachments(): List<AttachmentDto> {
        return attachmentRepository.findAll().map { it.toDto() }
    }

    fun getAttachmentById(id: UUID): AttachmentDto? {
        val response = attachmentRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDto()
        } else {
            throw EntityNotFoundException("There is no attachment with the given ID!")
        }
    }

    fun updateAttachment(id: UUID, attachmentDto: AttachmentDto): AttachmentDto? {
        val response = attachmentRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no attachment with the given ID!")
        }

        if (id != attachmentDto.idAttachment) {
            throw KeysAreDifferentException("The keys should be the same as the attachment with the given ID!")
        }

        val entity = attachmentDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = attachmentDto.updatedBy)

        return attachmentRepository.save(entity).toDto()
    }

    fun deleteAttachment(id: UUID) {
        val response = attachmentRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no attachment with the given ID!")
        }

        attachmentRepository.deleteById(id)
    }
}
