package com.lamulaapp.service

import com.lamulaapp.controller.dto.AttachmentDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.repository.AttachmentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AttachmentService(
    private val attachmentRepository: AttachmentRepository
) {

    fun createAttachment(attachmentDto: AttachmentDto): AttachmentDto {
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
            null
        }
    }

    fun updateAttachment(id: UUID, attachmentDto: AttachmentDto): AttachmentDto? {
        val response = attachmentRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != attachmentDto.idAttachment) {
            return null
        }

        return attachmentRepository.save(attachmentDto.toEntity()).toDto()
    }

    fun deleteAttachment(id: UUID) {
        attachmentRepository.deleteById(id)
    }
}
